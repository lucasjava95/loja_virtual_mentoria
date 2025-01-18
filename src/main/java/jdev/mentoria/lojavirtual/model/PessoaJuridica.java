package jdev.mentoria.lojavirtual.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoa_juridica")
public class PessoaJuridica extends Pessoa {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String cnpj;
	
	
	
	@Column(nullable = false)
	private String inscricEstadual;

	
	private String inscricMunicipal;
	
	
	@Column(nullable = false)
	private String nomeFantasia;
	
	
	@Column(nullable = false)
	private String razaoSocial;

	private String categoria;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricEstadual() {
		return inscricEstadual;
	}

	public void setInscricEstadual(String inscricEstadual) {
		this.inscricEstadual = inscricEstadual;
	}

	public String getInscricMunicipal() {
		return inscricMunicipal;
	}

	public void setInscricMunicipal(String inscricMunicipal) {
		this.inscricMunicipal = inscricMunicipal;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}
