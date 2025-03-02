package jdev.mentoria.lojavirtual.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
public class WebConfigSecurity {
	


	/*
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() throws Exception{
    	
    	
    	//URLs para se ignorar (não autenticar)
    	 return (web) -> web.ignoring().requestMatchers(HttpMethod.POST, "/salvarAcesso")
    			 
    			 .requestMatchers(HttpMethod.DELETE, "/deleteAcesso");
	 }
   */
   
	
	
	
	
    @Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        
    
    	http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
        .requestMatchers("/").permitAll()
        .requestMatchers("/index").permitAll()
        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
        
        
        .anyRequest()
        .authenticated()
        
        		
        ).formLogin((form) -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login-error")
                .permitAll()
           ).logout( (logout) -> logout
                .logoutSuccessUrl("/index")
           ).exceptionHandling( (ex) -> ex
                .accessDeniedPage("/negado")
        
        
        
        );
        
        
        http.addFilterBefore(new JwtApiAutenticacaoFilter(), UsernamePasswordAuthenticationFilter.class)
        
        .addFilterAfter(new JWTLoginFilter("/login", this.authenticationManager(null)), UsernamePasswordAuthenticationFilter.class);
        
        
        return http.build();

   
    
    
    }

	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
		
		
	}
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		
		return authenticationConfiguration.getAuthenticationManager();

		
		
		
	}
	
	
 

}
	
	
	
	
	
