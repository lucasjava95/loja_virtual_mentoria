package jdev.mentoria.lojavirtual.service;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ServiceSendEmail {

  
    private String userName = "lucasengcomp13@gmail.com";
    
    private String senha = "ravkibzlxntbwsyf";
    
    
   
    @Async
    public void enviarEmailHtml(String assunto, String mensagem, String emailDestino) throws UnsupportedEncodingException, MessagingException {
    	
    	
    	Properties properties = new Properties();
    	
    	properties.put("mail.smtp.ssl.trust", "*");
    	
    	properties.put("mail.smtp.auth", "true");
    	
    	properties.put("mail.smtp.starttls", "false");

    	properties.put("mail.smtp.host", "smtp.gmail.com");

    	properties.put("mail.smtp.port", "465");

    	properties.put("mail.smtp.socketFactory.port", "465");
    	
    	properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    	
    	
    	Session session = Session.getDefaultInstance(properties, new Authenticator() {
    		
    		
    		
    		@Override
    		protected PasswordAuthentication getPasswordAuthentication() {
    			
    			
    			
    			
    				return new PasswordAuthentication(userName, senha);
    		
    		
    		}
    		
    	});
    	
    	
    	
    	session.setDebug(true); //mostrar Excecao caso haja algum erro que impeça o envio do e-mail.

        
    	Address[] toUser = InternetAddress.parse(emailDestino);
    	
    	
    	Message message = new MimeMessage(session);
    	
    	message.setFrom(new InternetAddress(userName, "Lucas - aluno do Jdev Treinamentos", "UTF-8"));
    	
    	message.setRecipients(Message.RecipientType.TO, toUser);
    	
    	message.setSubject(assunto);
    	
    	message.setContent(mensagem, "text/html; charset=UTF-8");
    	
    	//message.setText(mensagem);
    	
    	
    	Transport.send(message);  
    	
    	
    	
    	
    	
    	
    }



}
