package br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model;

import java.util.Objects;

public class PaymentMethod {
    private int codigo;
    private String name;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentMethod that)) return false;
        return getCodigo() == that.getCodigo() && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigo(), getName());
    }
}
