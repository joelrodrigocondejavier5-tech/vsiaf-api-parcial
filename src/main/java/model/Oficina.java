package com.vsiaf.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "oficina")
public class Oficina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 1er cuadrito corto de "Oficina" en VSIAF
    @Column(name = "cod_oficina", nullable = false, unique = true, length = 10)
    private String codOficina;

    // 2do cuadro largo de "Oficina" en VSIAF
    @Column(name = "nombre_oficina", nullable = false, length = 100)
    private String nombreOficina;

    // Cuadro "Observación" en VSIAF
    @Column(name = "observacion", length = 255)
    private String observacion;

    // Modificación propia respecto a VSIAF: código de unidad y estado activo
    @Column(name = "cod_unidad", length = 10)
    private String codUnidad = "01";

    @Column(nullable = false)
    private Boolean activo = true;

    public Oficina() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodOficina() { return codOficina; }
    public void setCodOficina(String codOficina) { this.codOficina = codOficina; }

    public String getNombreOficina() { return nombreOficina; }
    public void setNombreOficina(String nombreOficina) { this.nombreOficina = nombreOficina; }

    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }

    public String getCodUnidad() { return codUnidad; }
    public void setCodUnidad(String codUnidad) { this.codUnidad = codUnidad; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}