package com.vsiaf.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class ResponsableRequest {

    @Schema(example = "1", description = "Código de la oficina (CODOFIC), ej: 1")
    private Integer codOfic;

    @Schema(example = "CARLOS MENDOZA QUISPE", description = "Nombre del responsable (NOMRESP, máx. 35 caracteres)")
    private String nomResp;

    @Schema(example = "AUXILIAR CONTABLE", description = "Cargo en la oficina (CARGO, máx. 35 caracteres)")
    private String cargo;

    @Schema(example = "85412369", description = "Cédula de Identidad (mínimo 8 dígitos numéricos)")
    private String ci;

    @Schema(example = "5", description = "Código de departamento (COD_EXP de cla_depts.dbf: 1=CH, 2=LP, 3=CB, 4=OR, 5=PT, 6=TJ, 7=SC, 8=BN, 9=PD)")
    private Integer codExp;

    @Schema(example = "RESPONSABLE DE PRUEBA", description = "Observación opcional (OBSERV)")
    private String observ;

    public Integer getCodOfic() { return codOfic; }
    public void setCodOfic(Integer codOfic) { this.codOfic = codOfic; }

    public String getNomResp() { return nomResp; }
    public void setNomResp(String nomResp) { this.nomResp = nomResp; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public String getCi() { return ci; }
    public void setCi(String ci) { this.ci = ci; }

    public Integer getCodExp() { return codExp; }
    public void setCodExp(Integer codExp) { this.codExp = codExp; }

    public String getObserv() { return observ; }
    public void setObserv(String observ) { this.observ = observ; }
}