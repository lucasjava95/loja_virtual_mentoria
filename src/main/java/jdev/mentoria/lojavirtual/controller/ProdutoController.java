package jdev.mentoria.lojavirtual.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.xml.bind.DatatypeConverter;

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
import jdev.mentoria.lojavirtual.model.Produto;
import jdev.mentoria.lojavirtual.repository.ProdutoRepository;
import jdev.mentoria.lojavirtual.service.ServiceSendEmail;

//@CrossOrigin(origins = "https://www.jdevtreinamento.com.br")
@Controller
@RestController
public class ProdutoController {
	
	
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	@Autowired
	private ServiceSendEmail serviceSendEmail;
	
	
	@ResponseBody /*Poder dar um retorno da API*/
	@PostMapping(value = "**/salvarProduto") /*Mapeando a url para receber JSON*/
	public ResponseEntity<Produto> salvarProduto(@RequestBody @Valid Produto produto) throws ExceptionMentoriaJava, MessagingException, IOException { /*Recebe o JSON e converte pra Objeto*/
		
		
		if(produto.getTipoUnidade() == null || produto.getTipoUnidade().trim().isEmpty()) {
			
			throw new ExceptionMentoriaJava("Tipo de unidade deve ser informada.");
			
			
		}
		
		if(produto.getNome().length() < 10) {
			
			throw new ExceptionMentoriaJava("Nome do produto deve ter pelo menos 10 letras.");
			
			
		}
		
		
		
	     
		if(produto.getEmpresa() == null || produto.getEmpresa().getId() <= 0 ) {
			
			throw new ExceptionMentoriaJava("Empresa responsável deve ser informada.");
			
		}
		

		if (produto.getId() == null) {
		  List<Produto> produtos = produtoRepository.buscaProdutoNome(produto.getNome().toUpperCase(), produto.getEmpresa().getId());
		  
		  if (!produtos.isEmpty()) {
			  throw new ExceptionMentoriaJava("Já existe Produto com o nome: " + produto.getNome());
		  }
		}
		
		
		
		if(produto.getMarcaProduto() == null || produto.getMarcaProduto().getId() <= 0) {
			
			throw new ExceptionMentoriaJava("Marca do produto deve ser informada.");
			
		}
		
		if(produto.getQtdEstoque() < 1) {
			
			throw new ExceptionMentoriaJava("Produto deve ter no mínimo uma unidade.");
			
		}
		
		
		if(produto.getImagens() == null || produto.getImagens().isEmpty() || produto.getImagens().size() == 0 ) {
			
			
			throw new ExceptionMentoriaJava("Deve ser informado imagens para o produto.");
				
		}
		
		
		if(produto.getImagens().size() < 3) {
			
			
			throw new ExceptionMentoriaJava("Produto deve ter no mínimo 3 imagens.");
			
		}
		
		
		if(produto.getImagens().size() > 6) {
			
			
			throw new ExceptionMentoriaJava("Produto deve ter no máximo 6 imagens.");
			
		}
		
		
		if(produto.getId() == null) {
			
			
			for(int x = 0; x < produto.getImagens().size(); x++) {
				
				produto.getImagens().get(x).setProduto(produto);
				
				produto.getImagens().get(x).setEmpresa(produto.getEmpresa());
				
				
				String base64Image = "";
				
			 if(produto.getImagens().get(x).getImagemOriginal().contains("data:image")) {
					
					
			base64Image = produto.getImagens().get(x).getImagemOriginal().split(",")[1]; /*Pega o conteudo pós virgula -> data:image/jpeg;base64, /9j/4AAQSkZJRgAB*/
								
			}else {
				
				base64Image = produto.getImagens().get(x).getImagemOriginal(); /*Pega a imagem original direto, sem precisar quebrar em um array (condição acima)*/
				
			}
			 
			 
		    byte[] imagemBytes = DatatypeConverter.parseBase64Binary(base64Image);
		    
		    
		    BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imagemBytes));
		    
		    
		    
		    if(bufferedImage != null) {
		    	
		    	
		    	int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();
		    	
		    	int largura = Integer.parseInt("800");
		    	
		    	int altura = Integer.parseInt("600");
		    	
		    	
		    	BufferedImage resizedImagem = new BufferedImage(largura, altura, type);
		    	
		    	Graphics2D g = resizedImagem.createGraphics();
		    	
		    	g.drawImage(bufferedImage, 0, 0, largura, altura, null);
		    	
		    	g.dispose(); //gera a imagem redimensionada (800x600)
		    	
		    	
		    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    	
		    	ImageIO.write(resizedImagem, "png", baos);
		    	
		    	
		    	String miniImgBase64 = "data:image/png;base64," + DatatypeConverter.printBase64Binary(baos.toByteArray());
		    	
		    	produto.getImagens().get(x).setImagemMiniatura(miniImgBase64);
		    	
		    	
		    	/*4 linhas seguintes para descarregar os buffers de memoria*/
		    	
		    	bufferedImage.flush();
		    	
		    	resizedImagem.flush();
		    	
		    	baos.flush();
		    	
		    	baos.close();
		    	
		    	
		    	
		    	
		    	
		    }
				
		
			}
				
		}
		
		
		
			
		Produto produtoSalvo = produtoRepository.save(produto);
		
		if(produto.getAlertaQtdeEstoque() && produto.getQtdEstoque() <= 1) {
			
			
			StringBuilder html = new StringBuilder();
			
			html.append("<h2>")
			.append("Produto: " + produto.getNome())
			.append(" com estoque baixo: " + produto.getQtdEstoque());
			
			html.append("<p> ID Produto: ").append(produto.getId()).append("</p>");
			
			
			if(produto.getEmpresa().getEmail() != null) {
				
				serviceSendEmail.enviarEmailHtml("Estoque baixo", html.toString(), produto.getEmpresa().getEmail());
				
			
			
			}
				
		}
		
		
		
		return new ResponseEntity<Produto>(produtoSalvo, HttpStatus.OK); 
	
	}
	
	
	
	@ResponseBody /*Poder dar um retorno da API*/
	@PostMapping(value = "**/deleteProduto") /*Mapeando a url para receber JSON*/
	public ResponseEntity<String> deleteProduto(@RequestBody Produto produto) { /*Recebe o JSON e converte pra Objeto*/
		
		produtoRepository.deleteById(produto.getId());
		
		return new ResponseEntity<String>("Produto Removido",HttpStatus.OK);
	}
	

	//@Secured({ "ROLE_GERENTE", "ROLE_ADMIN" })
	@ResponseBody
	@DeleteMapping(value = "**/deleteProdutoPorId/{id}")
	public ResponseEntity<String> deleteProdutoPorId(@PathVariable("id") Long id) { 
		
		produtoRepository.deleteById(id);
		
		return new ResponseEntity<String>("Produto Removido",HttpStatus.OK);
	}
	
	
	
	@ResponseBody
	@GetMapping(value = "**/obterProduto/{id}")
	public ResponseEntity<Produto> obterProduto(@PathVariable("id") Long id) throws ExceptionMentoriaJava { 
		
		Produto produto = produtoRepository.findById(id).orElse(null);
		
		if (produto == null) {
			throw new ExceptionMentoriaJava("Não encontrou Produto com código: " + id);
		}
		
		return new ResponseEntity<Produto>(produto,HttpStatus.OK);
	}
	
	
	
	@ResponseBody
	@GetMapping(value = "**/buscarProdNome/{desc}")
	public ResponseEntity<List<Produto>> buscarProdNome(@PathVariable("desc") String desc) { 
		
		List<Produto> produto = produtoRepository.buscaProdutoNome(desc.toUpperCase());
		
		return new ResponseEntity<List<Produto>>(produto,HttpStatus.OK);
	
	}
	
	
	

}
