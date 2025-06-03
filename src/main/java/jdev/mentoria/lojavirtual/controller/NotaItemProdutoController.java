package jdev.mentoria.lojavirtual.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jdev.mentoria.lojavirtual.ExceptionMentoriaJava;
import jdev.mentoria.lojavirtual.model.NotaItemProduto;
import jdev.mentoria.lojavirtual.repository.NotaItemProdutoRepository;

@RestController
public class NotaItemProdutoController {


	@Autowired
	private NotaItemProdutoRepository notaItemProdutoRepository;
	
	
	@ResponseBody
	@PostMapping(value = "**/salvarNotaItemProduto")
	public ResponseEntity<NotaItemProduto> salvarNotaItemProduto(@RequestBody @Valid NotaItemProduto notaItemProduto) throws ExceptionMentoriaJava{
		
		
		if(notaItemProduto.getId() == null) {
			
			
			if(notaItemProduto.getProduto() == null || notaItemProduto.getProduto().getId() <= 0 ) {
				
				
				throw new ExceptionMentoriaJava("Produto deve ser informado.");
				
			}
			
			
           if(notaItemProduto.getNotaFiscalCompra() == null || notaItemProduto.getNotaFiscalCompra().getId() <= 0 ) {
				
				
				throw new ExceptionMentoriaJava("Nota Fiscal deve ser informada.");
				
			}
           
           
           if(notaItemProduto.getEmpresa() == null || notaItemProduto.getEmpresa().getId() <= 0 ) {
				
				
				throw new ExceptionMentoriaJava("Empresa responsável deve ser informada.");
				
			}
			
			
			
			List<NotaItemProduto> notaExistente = notaItemProdutoRepository.buscaNotaItemProdutoNota(notaItemProduto.getProduto().getId(), notaItemProduto.getNotaFiscalCompra().getId());
			
			if(!notaExistente.isEmpty()) {
				
				
				throw new ExceptionMentoriaJava("Já existe esse Produto cadastrado para essa Nota Fiscal.");
				
				
			}
	}
		

		
	    NotaItemProduto notaItemSalva = notaItemProdutoRepository.save(notaItemProduto);
	    
	    notaItemSalva = notaItemProdutoRepository.findById(notaItemProduto.getId()).get();
	 
	 
	    return new ResponseEntity<NotaItemProduto>(notaItemSalva, HttpStatus.OK);
			

	}
	
	@ResponseBody
	@DeleteMapping(value = "**/deleteNotaItemPorId/{id}")
	public ResponseEntity<String> deleteNotaItemPorId(@PathVariable("id") Long id) { 
		
		notaItemProdutoRepository.deleteById(id);
		
		return new ResponseEntity<String>("Nota Item Produto Removida",HttpStatus.OK);
	
    
    }
	
	

}
