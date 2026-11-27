package com.vsiaf.api.controller;

import com.vsiaf.api.model.Responsable;
import com.vsiaf.api.model.ResponsableRequest;
import com.vsiaf.api.service.ResponsableService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/responsables")
@Tag(name = "Responsables (VSIAF - RESP.DBF)", description = "Operaciones de Responsables dentro de Oficinas y Responsables")
public class ResponsableController {

    @Autowired
    private ResponsableService responsableService;

    @GetMapping
    @Operation(summary = "Consultar responsables")
    public List<Responsable> listarTodos() {
        return responsableService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar un responsable específico")
    public Responsable obtenerPorId(@PathVariable Long id) {
        return responsableService.buscarPorId(id);
    }

    @GetMapping("/departamentos")
    @Operation(summary = "Listar departamentos válidos (cla_depts.dbf)")
    public Map<Integer, String> listarDepartamentos() {
        return responsableService.listarDepartamentos();
    }

    @PostMapping
    @Operation(
            summary = "Crear y guardar responsable (Nuevo + Guardar)",
            description = "Genera codResp automático y lo crea en estado INACTIVO (apiEstado = 3)"
    )
    public ResponseEntity<Responsable> crear(@RequestBody ResponsableRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(responsableService.crearResponsable(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar y guardar responsable (Modificar + Guardar)")
    public Responsable modificar(@PathVariable Long id, @RequestBody ResponsableRequest request) {
        return responsableService.modificarResponsable(id, request);
    }

    @PatchMapping("/{id}/activar")
    @Operation(summary = "Activar responsable (Botón Activar)", description = "Cambia apiEstado a 1 (ACTIVO) verificando que la oficina esté activa")
    public Responsable activar(@PathVariable Long id) {
        return responsableService.activarResponsable(id);
    }

    @PatchMapping("/{id}/inactivar")
    @Operation(
            summary = "Inactivar responsable (Botón Inactivar)",
            description = "Cambia apiEstado a 3 (INACTIVO)"
    )
    public Responsable inactivar(@PathVariable Long id) {
        return responsableService.inactivarResponsable(id);
    }
}