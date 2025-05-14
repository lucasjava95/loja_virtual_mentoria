package jdev.mentoria.lojavirtual.model.dto;

import java.io.Serializable;

/*A presente classe teve de ser criada pois ao se salvar uma CategoriaProduto no BD e, ao se informar o atributo empresa, obtinha-se no response o erro 
 InvalidDefinitionException: Cannot construct instance of jdev.mentoria.lojavirtual.model.Pessoa` (no Creators, like default constructor, exist): 
 abstract types either need to be mapped to concrete types, have custom deserializer, or contain additional type information,
 pois o atributo empresa é do tipo Pessoa e, Pessoa, é uma classe abstrata*/
 


public class CategoriaProdutoDto implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	
	private String nomeDesc;
	
	private Long empresa;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeDesc() {
		return nomeDesc;
	}

	public void setNomeDesc(String nomeDesc) {
		this.nomeDesc = nomeDesc;
	}

	public Long getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Long empresa) {
		this.empresa = empresa;
	}
	
	
	
	





}
