package com.vsiaf.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "unidad_administrativa")
public class UnidadAdministrativa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Campo "Unidad Administrativa" en VSIAF
    @Column(name = "cod_unidad", nullable = false, unique = true, length = 10)
    private String codUnidad;

    // Campo "Ciudad" en VSIAF
    @Column(name = "ciudad", nullable = false, length = 80)
    private String ciudad;

    // Campo "Descripción" en VSIAF
    @Column(name = "descripcion", nullable = false, length = 150)
    private String descripcion;

    // Modificación propia respecto a VSIAF: estado activo para no borrar historial
    @Column(nullable = false)
    private Boolean activo = true;

    public UnidadAdministrativa() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodUnidad() { return codUnidad; }
    public void setCodUnidad(String codUnidad) { this.codUnidad = codUnidad; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}