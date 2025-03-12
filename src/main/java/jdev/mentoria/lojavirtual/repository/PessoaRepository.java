package jdev.mentoria.lojavirtual.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jdev.mentoria.lojavirtual.model.PessoaJuridica;


@Repository
public interface PessoaRepository extends JpaRepository<PessoaJuridica, Long> {
	
	
	@Query("select pj from PessoaJuridica pj where pj.cnpj = ?1")
	public PessoaJuridica existeCnpjCadastrado(String cnpj);
	
	
	
	

}
