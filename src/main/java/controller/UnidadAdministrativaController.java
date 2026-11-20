package com.vsiaf.api.controller;

import com.vsiaf.api.model.UnidadAdministrativa;
import com.vsiaf.api.service.UnidadAdministrativaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/unidades")
@Tag(name = "Unidades Administrativas (VSIAF)", description = "Endpoints de la ventana Administrar Unidad del sistema VSIAF")
public class UnidadAdministrativaController {

    @Autowired
    private UnidadAdministrativaService unidadService;

    @GetMapping
    @Operation(summary = "Listar unidades", description = "Muestra la grilla UNIDAD, DESCRIPCION y CIUDAD en VSIAF")
    public ResponseEntity<List<UnidadAdministrativa>> listar() {
        return ResponseEntity.ok(unidadService.listarUnidades());
    }

    @PostMapping
    @Operation(summary = "Crear nueva unidad", description = "Equivale al botón Nuevo + Grabar de Unidad Administrativa")
    public ResponseEntity<UnidadAdministrativa> crear(@RequestBody UnidadAdministrativa unidad) {
        return ResponseEntity.status(HttpStatus.CREATED).body(unidadService.crearUnidad(unidad));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Editar unidad", description = "Equivale al botón Editar + Grabar de Unidad Administrativa")
    public ResponseEntity<UnidadAdministrativa> editar(@PathVariable Long id, @RequestBody UnidadAdministrativa unidad) {
        return ResponseEntity.ok(unidadService.editarUnidad(id, unidad));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar unidad", description = "Equivale al botón Eliminar de Unidad Administrativa")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        unidadService.eliminarUnidad(id);
        return ResponseEntity.noContent().build();
    }
}