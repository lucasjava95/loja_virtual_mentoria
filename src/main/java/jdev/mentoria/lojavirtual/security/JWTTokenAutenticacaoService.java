package jdev.mentoria.lojavirtual.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import jdev.mentoria.lojavirtual.ApplicationContextLoad;
import jdev.mentoria.lojavirtual.model.Usuario;
import jdev.mentoria.lojavirtual.repository.UsuarioRepository;


/*Classe que cria e retorna a autenticacão JWT*/
@Service
@Component
public class JWTTokenAutenticacaoService {
	
	
	private static final long EXPIRATION_TIME =  1728000000;
	
	
	/*Chave secreta para usar na autenticacão/criptografia, junto com o JWT*/
	private static final String SECRET = "PASSWORD";
	
	
	private static final String TOKEN_PREFIX = "Bearer";
	
	
	/*variavel para se capturar o retorno de uma requisição*/
	private static final String HEADER_STRING = "Authorization"; 
	
	
	
	/*Gera token e dar resposta para o cliente junto com o JWT*/
	public void addAuthentication(HttpServletResponse response, String username) throws Exception {
		
	  /*Montagem do token*/ 
	  
	  String JWT = Jwts.builder().  /*chama o gerador de token*/
			  
			       setSubject(username) /*adiciona o user*/
			       
			       .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) /*soma o tempo atual com o tempo de expiração*/
			       
			       .signWith(SignatureAlgorithm.HS512, SECRET).compact();
	  
	  
	  
	  /*Exemplo: Bearer seqeAHSIISS*/
	  String token = TOKEN_PREFIX +  " "  + JWT;
	  
	  
	  /*Dar a resposta para a tela e para o cliente (Outra API, navegador, aplicativo, etc), setando para o cabeçalho*/
	  response.addHeader(HEADER_STRING, token);
	  
	  
	  liberacaoCors(response);
	  
	  
	  /*seta para o corpo da resposta (mais usado para o Postman)*/
	  response.getWriter().write("{\"Authorization\": \"" + token + "\"}");

	  	
	}
	
	
	/*Faz a validacao do usuario/token a cada requisição que o usuario faz (salvar, consultar, deletar, etc) e, caso nao validado, retorna Null*/
	
	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String token = request.getHeader(HEADER_STRING);
		
		
		
		try {
		
		
		if(token != null) {
			
			
		  String tokenSemBearer = token.replace(TOKEN_PREFIX, "").trim();
			
			
		  /*obtem o USER*/
		  String user = Jwts.parser().
				  
				        setSigningKey(SECRET)
				        
				        .parseClaimsJws(tokenSemBearer)
				        
				        .getBody().getSubject();
		  
		  
		  if(user != null) {
			  
			  
		  Usuario usuario = ApplicationContextLoad.
				  
				             getApplicationContext().
				  
				             getBean(UsuarioRepository.class).findUserByLogin(user);
		  
		  
		  if(usuario != null) {
			  
			  
			  return new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getSenha(), usuario.getAuthorities());
			  
			  
		  }
		  
			  
			  
		  
		  }
		  	
			
		}
		
		}catch(SignatureException e) {
			
			
			response.getWriter().write("Token inválido");
			
			
		
		}finally {
			
			liberacaoCors(response);
		
		}
		
		
		return null;
		
		
		
	}
	
	
	
	
	
	
	/*liberacao contra erro de Cors no navegador*/
	public void liberacaoCors(HttpServletResponse response) {
		
		if(response.getHeader("Access-Control-Allow-Origin") == null) {
			
			response.addHeader("Access-Control-Allow-Origin", "*");
			
	    }
		
		
       if(response.getHeader("Access-Control-Allow-Headers") == null) {
			
			response.addHeader("Access-Control-Allow-Headers", "*");
			
	    }
       
       
       if(response.getHeader("Access-Control-Request-Headers") == null) {
			
			response.addHeader("Access-Control-Request-Headers", "*");
			
	    }
       
       
       
       if(response.getHeader("Access-Control-Allow-Methods") == null) {
			
		   response.addHeader("Access-Control-Allow-Methods", "*");
			
	    }
       
		
			
	}
	
	
	
	

}
