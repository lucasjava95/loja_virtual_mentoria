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

  


}
