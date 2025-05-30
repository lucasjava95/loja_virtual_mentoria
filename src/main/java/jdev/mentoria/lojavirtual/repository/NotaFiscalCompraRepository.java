package jdev.mentoria.lojavirtual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
   
   
   @Transactional
   @Modifying(flushAutomatically = true, clearAutomatically = true) //clear para limpar o cache, pra não ficar nada em cache.
   @Query(nativeQuery = true, value = "delete from nota_item_produto where nota_fiscal_compra_id = ?1")
   void deleteItemNotaFiscalCompra(Long idNotaFiscalCompra);
   
   
   @Query(nativeQuery = true, value = "select count(1) > 0 from nota_fiscal_compra where descricao_obs like %?1%")
   boolean existeNotaComDescricao(String desc);


}
