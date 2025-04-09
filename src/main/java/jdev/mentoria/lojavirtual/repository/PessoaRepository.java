package jdev.mentoria.lojavirtual.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jdev.mentoria.lojavirtual.model.PessoaFisica;
import jdev.mentoria.lojavirtual.model.PessoaJuridica;


@Repository
public interface PessoaRepository extends JpaRepository<PessoaJuridica, Long> {
	
	
	@Query("select pj from PessoaJuridica pj where pj.cnpj =?1")
	public PessoaJuridica existeCnpjCadastrado(String cnpj);
	
	
	@Query("select pf from PessoaFisica pf where pf.cpf =?1")
	public PessoaFisica existeCpfCadastrado(String cpf);
	
	
	
	@Query("select pj from PessoaJuridica pj where pj.inscricEstadual =?1")
	public PessoaJuridica existeInscrEstadualCadastrado(String inscricEstadual);
	
	
	
	

}
