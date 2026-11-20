package com.vsiaf.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "responsable")
public class Responsable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Columna "Cod." de la grilla en VSIAF
    @Column(name = "cod_responsable", nullable = false, length = 10)
    private String codResponsable;

    // Columna "Responsable" de la grilla en VSIAF
    @Column(name = "nombre_responsable", nullable = false, length = 120)
    private String nombreResponsable;

    // Columna "Cargo" de la grilla en VSIAF
    @Column(name = "cargo", nullable = false, length = 80)
    private String cargo;

    // Columna "CI" de la grilla en VSIAF
    @Column(name = "ci", nullable = false, length = 20)
    private String ci;

    // Modificación propia respecto a VSIAF: estado activo y relación directa con Oficina
    @Column(nullable = false)
    private Boolean activo = true;

    @ManyToOne
    @JoinColumn(name = "oficina_id", nullable = false)
    private Oficina oficina;

    public Responsable() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodResponsable() { return codResponsable; }
    public void setCodResponsable(String codResponsable) { this.codResponsable = codResponsable; }

    public String getNombreResponsable() { return nombreResponsable; }
    public void setNombreResponsable(String nombreResponsable) { this.nombreResponsable = nombreResponsable; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public String getCi() { return ci; }
    public void setCi(String ci) { this.ci = ci; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }

    public Oficina getOficina() { return oficina; }
    public void setOficina(Oficina oficina) { this.oficina = oficina; }
}