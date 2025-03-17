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
    	
    	properties.put("mail.smtp.ssl.trust", "*");
    	
    	properties.put("mail.smtp.auth", "true");
    	
    	properties.put("mail.smtp.starttls", "false");

    	properties.put("mail.smtp.host", "smtp.gmail.com");

    	properties.put("mail.smtp.port", "465");

    	properties.put("mail.smtp.socketFactory.port", "465");
    	
    	properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");


    	
    	
    	
    	
    	
    	
    }



}
