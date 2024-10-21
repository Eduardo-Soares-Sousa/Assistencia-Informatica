package br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model;

public enum Status {
    EMAPROVACAO("Em Aprovação"),
    APROVADA("Aprovada"),
    EMANDAMENTO("Em andamento"),
    FINALIZADA("Finalizada");

    private String description;

    private Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
