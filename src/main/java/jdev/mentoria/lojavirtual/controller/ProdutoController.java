package jdev.mentoria.lojavirtual.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jdev.mentoria.lojavirtual.ExceptionMentoriaJava;
import jdev.mentoria.lojavirtual.model.Acesso;
import jdev.mentoria.lojavirtual.model.Produto;
import jdev.mentoria.lojavirtual.repository.AcessoRepository;
import jdev.mentoria.lojavirtual.repository.ProdutoRepository;
import jdev.mentoria.lojavirtual.service.AcessoService;

//@CrossOrigin(origins = "https://www.jdevtreinamento.com.br")
@Controller
@RestController
public class ProdutoController {
	
	@Autowired
	private AcessoService acessoService;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@ResponseBody /*Poder dar um retorno da API*/
	@PostMapping(value = "**/salvarProduto") /*Mapeando a url para receber JSON*/
	public ResponseEntity<Produto> salvarProduto(@RequestBody Produto produto) throws ExceptionMentoriaJava { /*Recebe o JSON e converte pra Objeto*/
		
		if (produto.getId() == null) {
		  List<Produto> produtos = produtoRepository.buscaProdutoNome(produto.getNome().toUpperCase());
		  
		  if (!produtos.isEmpty()) {
			  throw new ExceptionMentoriaJava("Já existe Produto com a descrição: " + produto.getDescricao());
		  }
		}
		
		
		
		
		return new ResponseEntity<Produto>(produto, HttpStatus.OK); 
	
	}
	
	
	
	@ResponseBody /*Poder dar um retorno da API*/
	@PostMapping(value = "**/deleteProduto") /*Mapeando a url para receber JSON*/
	public ResponseEntity<?> deleteAcesso(@RequestBody Produto produto) { /*Recebe o JSON e converte pra Objeto*/
		
		produtoRepository.deleteById(produto.getId());
		
		return new ResponseEntity("Produto Removido",HttpStatus.OK);
	}
	

	//@Secured({ "ROLE_GERENTE", "ROLE_ADMIN" })
	@ResponseBody
	@DeleteMapping(value = "**/deleteProdutoPorId/{id}")
	public ResponseEntity<?> deleteAcessoPorId(@PathVariable("id") Long id) { 
		
		produtoRepository.deleteById(id);
		
		return new ResponseEntity("Produto Removido",HttpStatus.OK);
	}
	
	
	
	@ResponseBody
	@GetMapping(value = "**/obterProduto/{id}")
	public ResponseEntity<Produto> obterAcesso(@PathVariable("id") Long id) throws ExceptionMentoriaJava { 
		
		Produto produto = produtoRepository.findById(id).orElse(null);
		
		if (produto == null) {
			throw new ExceptionMentoriaJava("Não encontrou Produto com código: " + id);
		}
		
		return new ResponseEntity<Produto>(produto,HttpStatus.OK);
	}
	
	
	
	//@ResponseBody
	//@GetMapping(value = "**/buscarPorDesc/{desc}")
	//public ResponseEntity<List<Produto>> buscarPorDesc(@PathVariable("desc") String desc) { 
		
	//	List<Acesso> acesso = acessoRepository.buscarAcessoDesc(desc.toUpperCase());
		
	//	return new ResponseEntity<List<Produto>>(acesso,HttpStatus.OK);
	//}
	
	
	

}
