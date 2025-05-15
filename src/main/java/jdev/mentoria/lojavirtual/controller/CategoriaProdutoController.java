package jdev.mentoria.lojavirtual.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jdev.mentoria.lojavirtual.ExceptionMentoriaJava;
import jdev.mentoria.lojavirtual.model.CategoriaProduto;
import jdev.mentoria.lojavirtual.model.dto.CategoriaProdutoDto;
import jdev.mentoria.lojavirtual.repository.CategoriaProdutoRepository;

@RestController
public class CategoriaProdutoController {
	
	
	@Autowired
	private CategoriaProdutoRepository categoriaProdutoRepository;
	
	@ResponseBody
	@PostMapping(value = "**/salvarCategoriaProduto")
	public ResponseEntity<CategoriaProdutoDto> salvarCategoriaProduto(@RequestBody CategoriaProduto categoriaProduto) throws ExceptionMentoriaJava{
		
		
		if(categoriaProduto.getEmpresa() == null || (categoriaProduto.getEmpresa().getId() == null )) {
			
			
			throw new ExceptionMentoriaJava("Empresa deve ser informada!");
				
		}
		
		
		if(categoriaProdutoRepository.existeCategoria(categoriaProduto.getNomeDesc().toUpperCase())) {
			
			
			throw new ExceptionMentoriaJava("Categoria já existe");
			
			
		}
		
		

		CategoriaProduto categoriaSalva = categoriaProdutoRepository.save(categoriaProduto);
		
		
		CategoriaProdutoDto categoriaProdutoDto = new CategoriaProdutoDto();
		
		categoriaProdutoDto.setId(categoriaSalva.getId());
		
		categoriaProdutoDto.setEmpresa(categoriaSalva.getEmpresa().getId());
		
		categoriaProdutoDto.setNomeDesc(categoriaSalva.getNomeDesc());
		
		
		return new ResponseEntity<CategoriaProdutoDto>(categoriaProdutoDto, HttpStatus.OK);   
		
		
	}
	

}
