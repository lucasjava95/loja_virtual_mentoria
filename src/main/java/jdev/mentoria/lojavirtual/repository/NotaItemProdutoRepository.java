package jdev.mentoria.lojavirtual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jdev.mentoria.lojavirtual.model.NotaItemProduto;

@Repository
@Transactional
public interface NotaItemProdutoRepository extends JpaRepository<NotaItemProduto, Long> {

  
   @Query("select n from NotaItemProduto n where n.produto.id = ?1 and n.notaFiscalCompra.id = ?2")
   List<NotaItemProduto> buscaNotaItemProdutoNota(Long idProduto, Long idNotaFiscal);
   
   
   @Query("select n from NotaItemProduto n where n.produto.id = ?1")
   List<NotaItemProduto> buscaNotaItemPorProduto(Long idProduto);

   
   @Query("select n from NotaItemProduto n where n.notaFiscalCompra.id = ?1")
   List<NotaItemProduto> buscaNotaItemPorNotaFiscal(Long idNotaFiscal);
   
   
   @Query("select n from NotaItemProduto n where n.empresa.id = ?1")
   List<NotaItemProduto> buscaNotaItemPorEmpresa(Long idEmpresa);



}
