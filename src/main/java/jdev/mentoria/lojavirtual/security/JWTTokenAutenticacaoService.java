package jdev.mentoria.lojavirtual.security;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletResponse;


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
	  
	  
	  
	  /*seta para o corpo da resposta (mais usado para o Postman)*/
	  response.getWriter().write("{\"Authorization\": \"" + token + "\"}");

	  	
	}
	
	
	
	
	
	

}
