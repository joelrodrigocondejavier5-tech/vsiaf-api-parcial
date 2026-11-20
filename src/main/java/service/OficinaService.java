package com.vsiaf.api.service;

import com.vsiaf.api.model.Oficina;
import com.vsiaf.api.repository.OficinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OficinaService {

    @Autowired
    private OficinaRepository oficinaRepository;

    // Equivale a recorrer registros (Primero / Anterior / Siguiente / Último) en VSIAF
    public List<Oficina> listarOficinas() {
        return oficinaRepository.findAll();
    }

    // Equivale al botón "Nuevo" + "Guardar" de Oficina en VSIAF
    public Oficina crearOficina(Oficina oficina) {
        if (oficina.getCodOficina() == null || oficina.getCodOficina().isBlank()) {
            throw new RuntimeException("El código de oficina es obligatorio");
        }
        if (oficinaRepository.existsByCodOficina(oficina.getCodOficina())) {
            throw new RuntimeException("Ya existe una oficina con ese código");
        }
        return oficinaRepository.save(oficina);
    }

    // Equivale al botón "Modificar" + "Guardar" de Oficina en VSIAF
    public Oficina modificarOficina(Long id, Oficina datos) {
        Oficina actual = oficinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la oficina con ID: " + id));

        actual.setCodOficina(datos.getCodOficina());
        actual.setNombreOficina(datos.getNombreOficina());
        actual.setObservacion(datos.getObservacion());
        if (datos.getCodUnidad() != null) {
            actual.setCodUnidad(datos.getCodUnidad());
        }
        if (datos.getActivo() != null) {
            actual.setActivo(datos.getActivo());
        }
        return oficinaRepository.save(actual);
    }

    // Equivale al botón "Eliminar" de Oficina en VSIAF
    public void eliminarOficina(Long id) {
        if (!oficinaRepository.existsById(id)) {
            throw new RuntimeException("No existe la oficina con ID: " + id);
        }
        oficinaRepository.deleteById(id);
    }
}