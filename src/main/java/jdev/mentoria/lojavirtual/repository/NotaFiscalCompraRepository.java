package jdev.mentoria.lojavirtual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jdev.mentoria.lojavirtual.model.NotaFiscalCompra;

@Repository
@Transactional
public interface NotaFiscalCompraRepository extends JpaRepository<NotaFiscalCompra, Long> {

   
   @Query("select n from NotaFiscalCompra n where upper(trim(n.descricaoObs)) like %?1%")
   List<NotaFiscalCompra> buscaNotaDesc(String desc);


   @Query("select n from NotaFiscalCompra n where n.pessoa.id = ?1")
   List<NotaFiscalCompra> buscaNotaPorPessoa(Long idPessoa);
   
   @Query("select n from NotaFiscalCompra n where n.contaPagar.id = ?1")
   List<NotaFiscalCompra> buscaNotaPorContaPagar(Long idContaPagar);
   
   @Query("select n from NotaFiscalCompra n where n.empresa.id = ?1")
   List<NotaFiscalCompra> buscaNotaPorEmpresa(Long idEmpresa);


}
