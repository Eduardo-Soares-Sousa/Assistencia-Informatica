package br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model;

import java.io.Serializable;
import java.util.Objects;

public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long codigo;
	private String nome;
	private String email;
	private String telefone;
	private String cpf;
	private String senha;
	private boolean ativo;
	private Address address;

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
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Cliente cliente)) return false;
		return getCodigo() == cliente.getCodigo() && isAtivo() == cliente.isAtivo() && Objects.equals(getNome(), cliente.getNome()) && Objects.equals(getEmail(), cliente.getEmail()) && Objects.equals(getTelefone(), cliente.getTelefone()) && Objects.equals(getCpf(), cliente.getCpf()) && Objects.equals(getSenha(), cliente.getSenha());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCodigo(), getNome(), getEmail(), getTelefone(), getCpf(), getSenha(), isAtivo());
	}
}
