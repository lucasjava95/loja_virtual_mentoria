package jdev.mentoria.lojavirtual.controller;

import java.util.List;

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
import jdev.mentoria.lojavirtual.model.Acesso;
import jdev.mentoria.lojavirtual.repository.AcessoRepository;
import jdev.mentoria.lojavirtual.service.AcessoService;

@RestController
public class AcessoController {
	
	
	@Autowired
	private AcessoService acessoService;
	
	
	@Autowired
	private AcessoRepository acessoRepository;
	
	
	@ResponseBody /*Para se poder dar um retorno da API*/
	@PostMapping(value = "**/salvarAcesso")       
	public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso) throws ExceptionMentoriaJava { /*@RequestBody recebe JSON e converte para Objeto*/
		
		if(acesso.getId() == null) {
		
	    List<Acesso> acessos = acessoRepository.buscarAcessoDesc(acesso.getDescricao().toUpperCase());
	    
	    
	    if(!acessos.isEmpty()) {
	    	
	    	
	    	throw new ExceptionMentoriaJava("Já existe um acesso com a descrição " + acesso.getDescricao());
	    	
	    	
	    }
	    

		}
	    
		Acesso acessoSalvo = acessoService.save(acesso); 
		
		
		return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.OK);
		
	}
	
	
	
	
	@ResponseBody /*Para se poder dar um retorno da API*/
	@PostMapping(value = "**/deleteAcesso")       
	public ResponseEntity<?> deleteAcesso(@RequestBody Acesso acesso) { /*@RequestBody recebe JSON e converte para Objeto*/
		
		
		acessoRepository.deleteById(acesso.getId()); 
		
		
		return new ResponseEntity("Deletado com sucesso", HttpStatus.OK);
		
	}
	
	
	//@Secured({"ROLE_ADMIN"})  Somente ADMIN pode acessar esse end-point
	@ResponseBody
	@DeleteMapping(value = "**/deleteAcessoPorId/{id}")       
	public ResponseEntity<?> deleteAcessoId(@PathVariable("id") Long id) { 
		
		
		acessoRepository.deleteById(id); 
		
		
		return new ResponseEntity("Deletado com sucesso (ID)", HttpStatus.OK);
		
	}
	
	
	
	@ResponseBody
	@GetMapping(value = "**/obterAcesso/{id}")       
	public ResponseEntity<Acesso> obterAcesso(@PathVariable("id") Long id) throws ExceptionMentoriaJava { 
		
		
	    Acesso acesso = acessoRepository.findById(id).orElse(null); //retorna Null caso nao encontrado no BD
	    
	    
	    if(acesso == null) {
	    	
	    	
          throw new ExceptionMentoriaJava("Acesso com o codigo " + id + " não encontrado");
	    	
	    	
	    }
	    
		
		
		return new ResponseEntity<Acesso>(acesso, HttpStatus.OK);
		
	}
	
	
	
	
	
	
	@ResponseBody
	@GetMapping(value = "**/buscarPorDesc/{desc}")       
	public ResponseEntity<List<Acesso>> buscarPorDesc(@PathVariable("desc") String desc) { 
		
		
	    List<Acesso> acessos = acessoRepository.buscarAcessoDesc(desc.toUpperCase()); 
		
		
		return new ResponseEntity<List<Acesso>>(acessos, HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
	
	

}
