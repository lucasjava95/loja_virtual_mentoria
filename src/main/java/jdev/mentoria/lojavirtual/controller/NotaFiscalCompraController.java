package jdev.mentoria.lojavirtual.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jdev.mentoria.lojavirtual.ExceptionMentoriaJava;
import jdev.mentoria.lojavirtual.model.NotaFiscalCompra;
import jdev.mentoria.lojavirtual.repository.NotaFiscalCompraRepository;

@RestController
public class NotaFiscalCompraController {


  @Autowired
  private NotaFiscalCompraRepository notaFiscalCompraRepository;
  
  
  
    @ResponseBody 
	@PostMapping(value = "**/salvarNotaFiscalCompra")
	public ResponseEntity<NotaFiscalCompra> salvarNotaFiscalCompra(@RequestBody @Valid NotaFiscalCompra notaFiscalCompra) throws ExceptionMentoriaJava { /*Recebe o JSON e converte pra Objeto*/
		
		if (notaFiscalCompra.getId() == null) {
		 
			if(notaFiscalCompra.getDescricaoObs() != null) {
				
				List<NotaFiscalCompra> notaFiscalCompras =  notaFiscalCompraRepository.buscaNotaDesc((notaFiscalCompra.getDescricaoObs().toUpperCase().trim()));
				
			    if(!notaFiscalCompras.isEmpty()) {
			    	
			    	throw new ExceptionMentoriaJava("Já existe nota de compra com a descrição " + notaFiscalCompra.getDescricaoObs());
			    		    	
			    }
			
			
			}
				
		}
		
		if(notaFiscalCompra.getPessoa() == null || notaFiscalCompra.getPessoa().getId() <= 0) {
			
			throw new ExceptionMentoriaJava("Informe a pessoa jurídica responsável.");
			
	    }
		
		
        if(notaFiscalCompra.getEmpresa() == null || notaFiscalCompra.getEmpresa().getId() <= 0) {
			
			throw new ExceptionMentoriaJava("Informe a empresa responsável.");
			
	    }
        
        
        if(notaFiscalCompra.getContaPagar() == null || notaFiscalCompra.getContaPagar().getId() <= 0) {
        	
        	throw new ExceptionMentoriaJava("a conta a pagar da nota deve ser informada.");
        	
        }
		
		
		
		NotaFiscalCompra notaFiscalCompraSalva = notaFiscalCompraRepository.save(notaFiscalCompra);
		
		return new ResponseEntity<NotaFiscalCompra>(notaFiscalCompraSalva, HttpStatus.OK);
	}
    
  
  
  
    @ResponseBody
	@GetMapping(value = "**/buscarNotaCompraPorDesc/{desc}")
	public ResponseEntity<List<NotaFiscalCompra>> buscarNotaCompraPorDesc(@PathVariable("desc") String desc) { 
		
		List<NotaFiscalCompra> notaFiscalCompras = notaFiscalCompraRepository.buscaNotaDesc(desc.toUpperCase().trim());
		
		return new ResponseEntity<List<NotaFiscalCompra>>(notaFiscalCompras,HttpStatus.OK);
	
    
    }
    
    
    
    @ResponseBody
	@GetMapping(value = "**/obterNotaCompra/{id}")
	public ResponseEntity<NotaFiscalCompra> obterNotaCompra(@PathVariable("id") Long id) throws ExceptionMentoriaJava { 
		
    	NotaFiscalCompra notaFiscalCompra = notaFiscalCompraRepository.findById(id).orElse(null);
		
		if (notaFiscalCompra == null) {
			throw new ExceptionMentoriaJava("Não encontrou Nota Fiscal de Compra com código: " + id);
		}
		
		return new ResponseEntity<NotaFiscalCompra>(notaFiscalCompra,HttpStatus.OK);
	}
    
    
    
    @ResponseBody
	@DeleteMapping(value = "**/deleteNotaCompraPorId/{id}")
	public ResponseEntity<String> deleteNotaCompraPorId(@PathVariable("id") Long id) { 
		
		notaFiscalCompraRepository.deleteItemNotaFiscalCompra(id); //deleta os filhos (nota_item_produto)
		
		notaFiscalCompraRepository.deleteById(id); //deleta o pai (NotaFiscalCompra)
		
		return new ResponseEntity<String>("Nota Fiscal de Compra Removida",HttpStatus.OK);
	
    
    }
	



}
