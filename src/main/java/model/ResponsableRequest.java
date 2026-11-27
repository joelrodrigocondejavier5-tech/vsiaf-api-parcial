package com.vsiaf.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class ResponsableRequest {

    @Schema(example = "1", description = "Código de la oficina (CODOFIC), ej: 1")
    private Integer codOfic;

    @Schema(example = "CARLOS MENDOZA QUISPE", description = "Nombre del responsable (NOMRESP, máx. 35 caracteres)")
    private String nomResp;

    @Schema(example = "AUXILIAR CONTABLE", description = "Cargo en la oficina (CARGO, máx. 35 caracteres)")
    private String cargo;

    @Schema(
            example = "130387J5",
            description = "Cédula de Identidad (CI), carácter de 20. "
                    + "Permite letras y números."
    )
    private String ci;

    @Schema(
            example = "5",
            description = "Código de departamento según cla_depts.dbf: "
                    + "1=CHQ, 2=LPZ, 3=CBB, 4=ORU, 5=PTS, "
                    + "6=TAR, 7=SCZ, 8=BEN, 9=PAN, 10=OTR."
    )
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