package jdev.mentoria.lojavirtual.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import jdev.mentoria.lojavirtual.service.ImplementacaoUserDetailsService;


@Configuration
@EnableWebSecurity
public class WebConfigSecurity {
	
	
	

	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() throws Exception{
    	
    	
    	/*URLs para se ignorar (não autenticar)*/
    	 return (web) -> web.ignoring().requestMatchers(HttpMethod.POST, "/salvarAcesso")
    			 
    			 .requestMatchers(HttpMethod.DELETE, "/deleteAcesso");
	
    }
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
		
		
	}
	
	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder, ImplementacaoUserDetailsService userService) throws Exception {
		
		
	  return http.getSharedObject(AuthenticationManagerBuilder.class)
			  
	   .userDetailsService(userService)
	   
	   .passwordEncoder(passwordEncoder)
	   
	   .and()
	   
	   .build();
		
		
		
	}
	
	
   

	

}
	
	
	
	
	
