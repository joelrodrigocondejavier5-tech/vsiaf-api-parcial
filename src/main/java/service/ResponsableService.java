package com.vsiaf.api.service;

import com.vsiaf.api.model.Oficina;
import com.vsiaf.api.model.Responsable;
import com.vsiaf.api.model.ResponsableRequest;
import com.vsiaf.api.repository.OficinaRepository;
import com.vsiaf.api.repository.ResponsableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResponsableService {

    // Catálogo oficial cla_depts.dbf
    private static final Map<Integer, String> DEPARTAMENTOS = new LinkedHashMap<>() {{
        put(1, "CHUQUISACA");
        put(2, "LA PAZ");
        put(3, "COCHABAMBA");
        put(4, "ORURO");
        put(5, "POTOSI");
        put(6, "TARIJA");
        put(7, "SANTA CRUZ");
        put(8, "BENI");
        put(9, "PANDO");
    }};

    @Autowired
    private ResponsableRepository responsableRepository;

    @Autowired
    private OficinaRepository oficinaRepository;

    public Map<Integer, String> listarDepartamentos() {
        return DEPARTAMENTOS;
    }

    public List<Responsable> listarTodos() {
        return responsableRepository.findAll();
    }

    public Responsable buscarPorId(Long id) {
        return responsableRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe el responsable con ID: " + id));
    }

    // POST /api/responsables -> Botón NUEVO + GUARDAR (nace INACTIVO = 0)
    public Responsable crearResponsable(ResponsableRequest datos) {
        Oficina oficina = buscarOficinaPorCodigo(datos.getCodOfic());

        if (Boolean.FALSE.equals(oficina.getActivo())) {
            throw new IllegalArgumentException("No se puede registrar un responsable porque la oficina está INACTIVA");
        }

        validarCampos(datos);

        if (responsableRepository.existsByCi(datos.getCi().trim())) {
            throw new IllegalArgumentException("Ya existe un responsable registrado con el CI: " + datos.getCi().trim());
        }

        // Genera CODRESP correlativo automático dentro de esa oficina
        int siguienteCodResp = (int) responsableRepository.countByOficinaId(oficina.getId()) + 1;

        Responsable nuevo = new Responsable();
        nuevo.setEntidad("10");
        nuevo.setUnidad(oficina.getCodUnidad() != null ? oficina.getCodUnidad() : "01");
        nuevo.setCodOfic(datos.getCodOfic());
        nuevo.setCodResp(siguienteCodResp);
        nuevo.setNomResp(datos.getNomResp().trim().toUpperCase());
        nuevo.setCargo(datos.getCargo().trim().toUpperCase());
        nuevo.setObserv(datos.getObserv() != null ? datos.getObserv().trim().toUpperCase() : "");
        nuevo.setCi(datos.getCi().trim());
        nuevo.setFeult(LocalDate.now());
        nuevo.setUsuar("admin");
        nuevo.setCodExp(datos.getCodExp());
        nuevo.setApiEstado(0); // Regla VSIAF: nace INACTIVO (0)
        nuevo.setOficina(oficina);

        return responsableRepository.save(nuevo);
    }

    // PUT /api/responsables/{id} -> Botón MODIFICAR + GUARDAR
    public Responsable modificarResponsable(Long id, ResponsableRequest datos) {
        Responsable actual = buscarPorId(id);
        validarCampos(datos);

        String nuevoCi = datos.getCi().trim();
        if (!actual.getCi().equals(nuevoCi) && responsableRepository.existsByCi(nuevoCi)) {
            throw new IllegalArgumentException("Ya existe otro responsable con el CI: " + nuevoCi);
        }

        actual.setNomResp(datos.getNomResp().trim().toUpperCase());
        actual.setCargo(datos.getCargo().trim().toUpperCase());
        actual.setCi(nuevoCi);
        actual.setCodExp(datos.getCodExp());
        if (datos.getObserv() != null) {
            actual.setObserv(datos.getObserv().trim().toUpperCase());
        }
        actual.setFeult(LocalDate.now());

        return responsableRepository.save(actual);
    }

    // PATCH /api/responsables/{id}/activar -> Botón ACTIVAR
    public Responsable activarResponsable(Long id) {
        Responsable actual = buscarPorId(id);

        if (Boolean.FALSE.equals(actual.getOficina().getActivo())) {
            throw new IllegalArgumentException("No se puede activar el responsable porque su oficina está INACTIVA");
        }

        // Mantiene un solo responsable ACTIVO en esa oficina
        List<Responsable> deLaOficina = responsableRepository.findByOficinaId(actual.getOficina().getId());
        for (Responsable r : deLaOficina) {
            if (r.getApiEstado() != null && r.getApiEstado() == 1) {
                r.setApiEstado(0);
                r.setFeult(LocalDate.now());
                responsableRepository.save(r);
            }
        }

        actual.setApiEstado(1);
        actual.setFeult(LocalDate.now());
        return responsableRepository.save(actual);
    }

    // PATCH /api/responsables/{id}/inactivar -> Botón INACTIVAR
    public Responsable inactivarResponsable(Long id) {
        Responsable actual = buscarPorId(id);

        if (Boolean.FALSE.equals(actual.getOficina().getActivo())) {
            throw new IllegalArgumentException("No se puede inactivar el responsable porque su oficina está INACTIVA");
        }

        actual.setApiEstado(0);
        actual.setFeult(LocalDate.now());
        return responsableRepository.save(actual);
    }

    private Oficina buscarOficinaPorCodigo(Integer codOfic) {
        if (codOfic == null || codOfic <= 0) {
            throw new IllegalArgumentException("El código de oficina (codOfic) es obligatorio");
        }
        String sinCero = String.valueOf(codOfic);        // "1"
        String conCero = String.format("%02d", codOfic); // "01"
        return oficinaRepository.findFirstByCodOficina(conCero)
                .or(() -> oficinaRepository.findFirstByCodOficina(sinCero))
                .orElseThrow(() -> new IllegalArgumentException("No existe la oficina con código: " + codOfic));
    }

    private void validarCampos(ResponsableRequest datos) {
        if (datos.getNomResp() == null || datos.getNomResp().isBlank()) {
            throw new IllegalArgumentException("El nombre del responsable (nomResp) es obligatorio");
        }
        if (datos.getNomResp().trim().length() > 35) {
            throw new IllegalArgumentException("El nombre del responsable no puede pasar de 35 caracteres (tamaño de RESP.DBF)");
        }
        if (datos.getCargo() == null || datos.getCargo().isBlank()) {
            throw new IllegalArgumentException("El cargo (cargo) es obligatorio");
        }
        if (datos.getCargo().trim().length() > 35) {
            throw new IllegalArgumentException("El cargo no puede pasar de 35 caracteres (tamaño de RESP.DBF)");
        }
        if (datos.getCi() == null || !datos.getCi().trim().matches("^[0-9]{8,20}$")) {
            throw new IllegalArgumentException("El CI debe ser numérico y tener como mínimo 8 dígitos");
        }
        if (datos.getCodExp() == null || !DEPARTAMENTOS.containsKey(datos.getCodExp())) {
            throw new IllegalArgumentException("Código de departamento (codExp) inválido. Use del 1 al 9 según cla_depts.dbf");
        }
    }
}