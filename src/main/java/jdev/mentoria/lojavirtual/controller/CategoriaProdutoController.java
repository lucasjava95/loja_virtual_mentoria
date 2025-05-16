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
import jdev.mentoria.lojavirtual.model.CategoriaProduto;
import jdev.mentoria.lojavirtual.model.dto.CategoriaProdutoDto;
import jdev.mentoria.lojavirtual.repository.CategoriaProdutoRepository;

@RestController
public class CategoriaProdutoController {
	
	
	@Autowired
	private CategoriaProdutoRepository categoriaProdutoRepository;
	
	
	@ResponseBody
	@GetMapping(value = "**/buscarPorDescCategoria/{desc}")
	public ResponseEntity<List<CategoriaProduto>> buscarPorDesc(@PathVariable("desc") String desc) { 
		
		List<CategoriaProduto> categoria = categoriaProdutoRepository.buscarCategoriaDesc(desc.toUpperCase()) ;
		
		return new ResponseEntity<List<CategoriaProduto>>(categoria,HttpStatus.OK);
	
	}
	
	

	
	@ResponseBody /*Poder dar um retorno da API*/
	@DeleteMapping(value = "**/deleteCategoria") /*Mapeando a url para receber JSON*/
	public ResponseEntity<?> deleteCategoria(@RequestBody CategoriaProduto categoriaProduto) throws ExceptionMentoriaJava { 
		
		
		if(categoriaProdutoRepository.findById(categoriaProduto.getId()).isPresent() == false) {
			
		
			return new ResponseEntity("Categoria de produto não existe",HttpStatus.OK);
	
		}
		
		
		categoriaProdutoRepository.deleteById(categoriaProduto.getId());
		
		return new ResponseEntity("Categoria de produto removida",HttpStatus.OK);
	
	}
	
	

	@ResponseBody
	@PostMapping(value = "**/salvarCategoriaProduto")
	public ResponseEntity<CategoriaProdutoDto> salvarCategoriaProduto(@RequestBody CategoriaProduto categoriaProduto) throws ExceptionMentoriaJava{
		
		
		if(categoriaProduto.getEmpresa() == null || (categoriaProduto.getEmpresa().getId() == null )) {
			
			
			throw new ExceptionMentoriaJava("Empresa deve ser informada!");
				
		}
		
		
		if(categoriaProduto.getId() == null && categoriaProdutoRepository.existeCategoria(categoriaProduto.getNomeDesc().toUpperCase().trim())) {
			
			
			throw new ExceptionMentoriaJava("Categoria de produto já existe. Cadastre uma categoria diferente.");
			
			
		}
		
		

		CategoriaProduto categoriaSalva = categoriaProdutoRepository.save(categoriaProduto);
		
		
		CategoriaProdutoDto categoriaProdutoDto = new CategoriaProdutoDto();
		
		categoriaProdutoDto.setId(categoriaSalva.getId());
		
		categoriaProdutoDto.setEmpresa(categoriaSalva.getEmpresa().getId());
		
		categoriaProdutoDto.setNomeDesc(categoriaSalva.getNomeDesc());
		
		
		return new ResponseEntity<CategoriaProdutoDto>(categoriaProdutoDto, HttpStatus.OK);   
		
		
	}
	

}
