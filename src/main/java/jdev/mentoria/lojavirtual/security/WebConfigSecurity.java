package jdev.mentoria.lojavirtual.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.activation.DataSource;
import jdev.mentoria.lojavirtual.service.ImplementacaoUserDetailsService;


@Configuration
@EnableWebSecurity
public class WebConfigSecurity {
	
	
	@Autowired
	private ImplementacaoUserDetailsService userDetailsService;
	
 
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() throws Exception{
    	
    	
    	/*URLs para se ignorar (não autenticar)*/
    	 return (web) -> web.ignoring().requestMatchers(HttpMethod.POST, "/salvarAcesso")
    			 
    			 .requestMatchers(HttpMethod.DELETE, "/deleteAcesso");
	
    }
	
	
	/*
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
	
		http
        .authorizeHttpRequests((authz) -> authz
            .anyRequest().authenticated()
        
        		
        );
      return http.build();  
		
		
		
		
	}*/
	
	
    @Bean
	public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
		
		
	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	
	
	provider.setUserDetailsService(userDetailsService);
	
	provider.setPasswordEncoder(passwordEncoder);
	
	
	return new ProviderManager(provider);
		
		
		
	}

	

}
	
	
	
	
	
