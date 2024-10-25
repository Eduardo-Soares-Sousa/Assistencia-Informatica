package br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model;

public enum Status {
    EMAPROVACAO(1, "Em aprovação"),
    APROVADA(2, "Aprovada"),
    EMANDAMENTO(3, "Em andamento"),
    FINALIZADA(4, "Finalizada");

    private String description;
    private int code;

    private Status(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static Status fromString(String status) {
        switch (status) {
            case "Em aprovação":
                return EMAPROVACAO;
            case "Aprovada":
                return APROVADA;
            case "Em andamento":
                return EMANDAMENTO;
            case "Finalizada":
                return FINALIZADA;
            default:
                throw new IllegalArgumentException("Status inválido: " + status);
        }
    }

    public static Status fromCode(int code) {
        for (Status status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("No Status for code: " + code);
    }

    @Override
    public String toString() {
        return this.description;
    }
}
