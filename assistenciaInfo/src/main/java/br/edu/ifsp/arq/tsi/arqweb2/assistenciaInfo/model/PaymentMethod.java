package br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model;

public enum PaymentMethod {
    AVISTA(1L, "Á vista"),
    CARTAOCREDITO(2L, "Cartão de crédito"),
    CARTAODEBITO(3L, "Cartão de débito"),
    PIX(4L, "Pix");

    private Long code;
    private String description;

    private PaymentMethod(Long code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public static PaymentMethod fromCode(Long code) {
        for (PaymentMethod method : values()) {
            if (method.getCode().equals(code)) {
                return method;
            }
        }
        throw new IllegalArgumentException("Invalid PaymentMethod code: " + code);
    }
}
