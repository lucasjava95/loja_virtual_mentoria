package jdev.mentoria.lojavirtual.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;


@Configuration
@EnableWebSecurity
public class WebConfigSecurity {
	
 
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() throws Exception{
    	
    	
    	
    	 return (web) -> web.ignoring().requestMatchers(HttpMethod.POST, "/salvarAcesso")
    			 
    			 .requestMatchers(HttpMethod.DELETE, "deleteAcesso");
		
		
    	
    	
    	
    }

	
	
	

}
	
	
	
	
	
