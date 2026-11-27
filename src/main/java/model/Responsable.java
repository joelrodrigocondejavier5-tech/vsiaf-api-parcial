package com.vsiaf.api.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "responsable")
public class Responsable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ENTIDAD (Carácter 4) - Ministerio de Relaciones Exteriores = "10"
    @Column(name = "entidad", length = 4)
    private String entidad = "35";

    // UNIDAD (Carácter 5) - Se toma de la oficina seleccionada
    @Column(name = "unidad", length = 5)
    private String unidad;

    // CODOFIC (Numérico 4) - Código de la oficina en VSIAF
    @Column(name = "cod_ofic")
    private Integer codOfic;

    // CODRESP (Numérico 5) - Correlativo automático (1, 2, 3...)
    @Column(name = "cod_resp")
    private Integer codResp;

    // NOMRESP (Carácter 35)
    @Column(name = "nom_resp", length = 35)
    private String nomResp;

    // CARGO (Carácter 35)
    @Column(name = "cargo", length = 35)
    private String cargo;

    // OBSERV (Memo)
    @Column(name = "observ", columnDefinition = "TEXT")
    private String observ;

    // CI (Carácter 20) - Admite letras y números
    @Column(name = "ci", length = 20)
    private String ci;

    // FEULT (Fecha) - Fecha automática de registro o modificación
    @Column(name = "feult")
    private LocalDate feult;

    // USUAR (Carácter 8) - Usuario que realiza la operación
    @Column(name = "usuar", length = 8)
    private String usuar = "admin";

    // COD_EXP (Numérico 2) - Código según cla_depts.dbf (1 al 10)
    @Column(name = "cod_exp")
    private Integer codExp;

    // API_ESTADO (Numérico 1) - 1 = ACTIVO, 3 = INACTIVO
    @Column(name = "api_estado")
    private Integer apiEstado = 3;

    @ManyToOne
    @JoinColumn(name = "oficina_id", nullable = false)
    private Oficina oficina;

    public Responsable() {
    }

    @Transient
    public String getEstadoDescripcion() {
        if (apiEstado == null) {
            return "SIN ESTADO";
        }

        return switch (apiEstado) {
            case 1 -> "ACTIVO";
            case 3 -> "INACTIVO";
            default -> "DESCONOCIDO";
        };
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEntidad() { return entidad; }
    public void setEntidad(String entidad) { this.entidad = entidad; }

    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }

    public Integer getCodOfic() { return codOfic; }
    public void setCodOfic(Integer codOfic) { this.codOfic = codOfic; }

    public Integer getCodResp() { return codResp; }
    public void setCodResp(Integer codResp) { this.codResp = codResp; }

    public String getNomResp() { return nomResp; }
    public void setNomResp(String nomResp) { this.nomResp = nomResp; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public String getObserv() { return observ; }
    public void setObserv(String observ) { this.observ = observ; }

    public String getCi() { return ci; }
    public void setCi(String ci) { this.ci = ci; }

    public LocalDate getFeult() { return feult; }
    public void setFeult(LocalDate feult) { this.feult = feult; }

    public String getUsuar() { return usuar; }
    public void setUsuar(String usuar) { this.usuar = usuar; }

    public Integer getCodExp() { return codExp; }
    public void setCodExp(Integer codExp) { this.codExp = codExp; }

    public Integer getApiEstado() { return apiEstado; }
    public void setApiEstado(Integer apiEstado) { this.apiEstado = apiEstado; }

    public Oficina getOficina() { return oficina; }
    public void setOficina(Oficina oficina) { this.oficina = oficina; }
}