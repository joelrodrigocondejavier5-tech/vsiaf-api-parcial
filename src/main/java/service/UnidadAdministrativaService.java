package com.vsiaf.api.service;

import com.vsiaf.api.model.UnidadAdministrativa;
import com.vsiaf.api.repository.UnidadAdministrativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadAdministrativaService {

    @Autowired
    private UnidadAdministrativaRepository unidadRepository;

    // Equivale a la tabla/grilla de ADMINISTRACION UNIDAD ADMINISTRATIVA en VSIAF
    public List<UnidadAdministrativa> listarUnidades() {
        return unidadRepository.findAll();
    }

    // Equivale al botón "Nuevo" -> llenar campos -> "Grabar" en VSIAF
    public UnidadAdministrativa crearUnidad(UnidadAdministrativa unidad) {
        if (unidad.getCodUnidad() == null || unidad.getCodUnidad().isBlank()) {
            throw new RuntimeException("El código de unidad es obligatorio");
        }
        if (unidadRepository.existsByCodUnidad(unidad.getCodUnidad())) {
            throw new RuntimeException("Ya existe una unidad con ese código");
        }
        return unidadRepository.save(unidad);
    }

    // Equivale al botón "Editar" -> modificar campos -> "Grabar" en VSIAF
    public UnidadAdministrativa editarUnidad(Long id, UnidadAdministrativa datos) {
        UnidadAdministrativa actual = unidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la unidad con ID: " + id));

        actual.setCodUnidad(datos.getCodUnidad());
        actual.setCiudad(datos.getCiudad());
        actual.setDescripcion(datos.getDescripcion());
        if (datos.getActivo() != null) {
            actual.setActivo(datos.getActivo());
        }
        return unidadRepository.save(actual);
    }

    // Equivale al botón "Eliminar" en VSIAF
    public void eliminarUnidad(Long id) {
        if (!unidadRepository.existsById(id)) {
            throw new RuntimeException("No existe la unidad con ID: " + id);
        }
        unidadRepository.deleteById(id);
    }
}