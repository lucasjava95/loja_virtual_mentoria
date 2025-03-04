package jdev.mentoria.lojavirtual.security;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdev.mentoria.lojavirtual.model.Usuario;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
	
	
	/*Configura o gerenciador de autenticação*/
	public JWTLoginFilter(String url, AuthenticationManager authenticationManager) { /*Obs: a url vem da requisição*/
		
	 	
	 /*obriga a autenticar a url*/
	 super(new AntPathRequestMatcher(url));
	 
	 
	 setAuthenticationManager(authenticationManager);
	 
	 
	
	
	}
	


	
	/*retorna o usuario processado na autenticação*/
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		
	 Usuario user = new ObjectMapper().readValue(request.getInputStream(), Usuario.class); /*pega os dados da requisicao e converte p/ uma classe Usuario*/
		
		
		
		
	/*Retorna o user com login e senha*/	 
	return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getSenha()));
	
	
	
	}
	
	
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		
      	
	  try {
		
		 new JWTTokenAutenticacaoService().addAuthentication(response, authResult.getName()); /*authResult.getName() retorna o login*/
	
	  
	  } catch (Exception e) {
		
		  e.printStackTrace();
	
	  
	  } 
	
	
	}
	
	
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		
		
        if(failed instanceof BadCredentialsException) {
        	
        	response.getWriter().write("Usuario e/ou senha invalidos");
        	
        	
        }else {
        	
        	response.getWriter().write("Falha ao logar : " + failed.getMessage());
        	
        	
        }
		
		
		
		//super.unsuccessfulAuthentication(request, response, failed);
	
	
	
	}
	




}
