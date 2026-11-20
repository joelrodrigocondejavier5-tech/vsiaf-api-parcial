package com.vsiaf.api.controller;

import com.vsiaf.api.model.Oficina;
import com.vsiaf.api.service.OficinaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/oficinas")
@Tag(name = "Oficinas (VSIAF)", description = "Endpoints de la ventana OFICINA del sistema VSIAF")
public class OficinaController {

    @Autowired
    private OficinaService oficinaService;

    @GetMapping
    @Operation(summary = "Listar oficinas", description = "Equivale a recorrer los registros en la ventana Oficina de VSIAF")
    public ResponseEntity<List<Oficina>> listar() {
        return ResponseEntity.ok(oficinaService.listarOficinas());
    }

    @PostMapping
    @Operation(summary = "Guardar nueva oficina", description = "Equivale al botón Nuevo + Guardar de la ventana Oficina")
    public ResponseEntity<Oficina> crear(@RequestBody Oficina oficina) {
        return ResponseEntity.status(HttpStatus.CREATED).body(oficinaService.crearOficina(oficina));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar oficina", description = "Equivale al botón Modificar + Guardar de la ventana Oficina")
    public ResponseEntity<Oficina> modificar(@PathVariable Long id, @RequestBody Oficina oficina) {
        return ResponseEntity.ok(oficinaService.modificarOficina(id, oficina));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar oficina", description = "Equivale al botón Eliminar de la ventana Oficina")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        oficinaService.eliminarOficina(id);
        return ResponseEntity.noContent().build();
    }
}