package com.vsiaf.api.service;

import com.vsiaf.api.model.Oficina;
import com.vsiaf.api.model.Responsable;
import com.vsiaf.api.repository.OficinaRepository;
import com.vsiaf.api.repository.ResponsableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponsableService {

    @Autowired
    private ResponsableRepository responsableRepository;

    @Autowired
    private OficinaRepository oficinaRepository;

    public List<Responsable> listarTodos() {
        return responsableRepository.findAll();
    }

    // Lista los responsables de una oficina específica (grilla inferior en VSIAF)
    public List<Responsable> listarPorOficina(Long oficinaId) {
        return responsableRepository.findByOficinaId(oficinaId);
    }

    // Botón "Nuevo" + "Guardar" de la parte inferior en VSIAF
    public Responsable crearResponsable(Long oficinaId, Responsable responsable) {
        Oficina oficina = oficinaRepository.findById(oficinaId)
                .orElseThrow(() -> new RuntimeException("No existe la oficina con ID: " + oficinaId));

        if (responsable.getNombreResponsable() == null || responsable.getNombreResponsable().isBlank()) {
            throw new RuntimeException("El nombre del responsable es obligatorio");
        }
        responsable.setOficina(oficina);
        return responsableRepository.save(responsable);
    }

    // Botón "Modificar" + "Guardar" de la parte inferior en VSIAF
    public Responsable modificarResponsable(Long id, Responsable datos) {
        Responsable actual = responsableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Responsable no encontrado con ID: " + id));

        actual.setCodResponsable(datos.getCodResponsable());
        actual.setNombreResponsable(datos.getNombreResponsable());
        actual.setCargo(datos.getCargo());
        actual.setCi(datos.getCi());
        if (datos.getActivo() != null) {
            actual.setActivo(datos.getActivo());
        }
        return responsableRepository.save(actual);
    }

    // Botón "Eliminar" de la parte inferior en VSIAF
    public void eliminarResponsable(Long id) {
        if (!responsableRepository.existsById(id)) {
            throw new RuntimeException("No existe el responsable con ID: " + id);
        }
        responsableRepository.deleteById(id);
    }
}