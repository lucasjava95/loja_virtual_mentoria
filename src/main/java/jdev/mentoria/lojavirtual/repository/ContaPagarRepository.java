package jdev.mentoria.lojavirtual.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jdev.mentoria.lojavirtual.model.ContaPagar;


@Repository
@Transactional
public interface ContaPagarRepository extends JpaRepository<ContaPagar, Long> {

	
  @Query("select c from ContaPagar c where upper(trim(c.descricao)) like %?1%")	
  List<ContaPagar> buscaContaDesc(String desc);
  
  
  @Query("select c from ContaPagar c where c.pessoa.id = ?1")	
  List<ContaPagar> buscaContaPorPessoa(Long idPessoa);
  
  
  @Query("select c from ContaPagar c where c.pessoa_fornecedor.id = ?1")	
  List<ContaPagar> buscaContaPorFornecedor(Long idFornecedor);
  
  
  @Query("select c from ContaPagar c where c.empresa.id = ?1")	
  List<ContaPagar> buscaContaPorEmpresa(Long idEmpresa);

  


}
