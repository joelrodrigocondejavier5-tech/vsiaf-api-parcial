package com.vsiaf.api.controller;

import com.vsiaf.api.model.Responsable;
import com.vsiaf.api.service.ResponsableService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/responsables")
@Tag(name = "Responsables (VSIAF)", description = "Grilla inferior de Responsables dentro de la ventana OFICINA")
public class ResponsableController {

    @Autowired
    private ResponsableService responsableService;

    @GetMapping
    @Operation(summary = "Listar todos los responsables")
    public ResponseEntity<List<Responsable>> listarTodos() {
        return ResponseEntity.ok(responsableService.listarTodos());
    }

    @GetMapping("/oficina/{oficinaId}")
    @Operation(summary = "Listar responsables de una oficina", description = "Muestra la grilla Cod, Responsable, Cargo y CI de esa oficina")
    public ResponseEntity<List<Responsable>> listarPorOficina(@PathVariable Long oficinaId) {
        return ResponseEntity.ok(responsableService.listarPorOficina(oficinaId));
    }

    @PostMapping("/oficina/{oficinaId}")
    @Operation(summary = "Agregar responsable a una oficina", description = "Equivale al botón Nuevo + Guardar de la grilla inferior en VSIAF")
    public ResponseEntity<Responsable> crear(@PathVariable Long oficinaId, @RequestBody Responsable responsable) {
        return ResponseEntity.status(HttpStatus.CREATED).body(responsableService.crearResponsable(oficinaId, responsable));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar responsable", description = "Equivale al botón Modificar + Guardar de la grilla inferior en VSIAF")
    public ResponseEntity<Responsable> modificar(@PathVariable Long id, @RequestBody Responsable responsable) {
        return ResponseEntity.ok(responsableService.modificarResponsable(id, responsable));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar responsable", description = "Equivale al botón Eliminar de la grilla inferior en VSIAF")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        responsableService.eliminarResponsable(id);
        return ResponseEntity.noContent().build();
    }
}