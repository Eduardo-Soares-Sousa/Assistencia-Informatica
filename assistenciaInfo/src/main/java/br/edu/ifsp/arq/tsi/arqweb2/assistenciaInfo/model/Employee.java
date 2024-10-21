package br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model;

import java.io.Serializable;
import java.util.Objects;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long codigo;
    private String nome;
    private String email;
    private String senha;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return Objects.equals(getCodigo(), employee.getCodigo()) && Objects.equals(getNome(), employee.getNome()) && Objects.equals(getEmail(), employee.getEmail()) && Objects.equals(getSenha(), employee.getSenha());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigo(), getNome(), getEmail(), getSenha());
    }
}
