package jdev.mentoria.lojavirtual.service;

import java.util.Properties;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ServiceSendEmail {

  
    private String userName = "l05409337@gmail.com";
    
    private String senha = "EngAdm123***";
    
    
   
    @Async
    public void enviarEmailHtml(String assunto, String mensagem, String emailDestino) {
    	
    	
    	Properties properties = new Properties();
    	
    	properties.put(emailDestino, properties);
    	
    	
    	
    	
    	
    }



}
