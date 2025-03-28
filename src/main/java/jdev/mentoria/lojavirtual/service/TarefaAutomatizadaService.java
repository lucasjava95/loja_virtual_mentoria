package jdev.mentoria.lojavirtual.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jdev.mentoria.lojavirtual.model.Usuario;
import jdev.mentoria.lojavirtual.repository.UsuarioRepository;

@Service
public class TarefaAutomatizadaService {
	
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@Autowired
	private ServiceSendEmail serviceSendEmail;


	//@Scheduled(initialDelay = 2000, fixedDelay = 86400000) // processo que demorarará 2 millisegundos para executar, quando o projeto subir. E que rodará
	                                                       // a cada 86400000 millisegundos (24 hrs)
	
	//@Scheduled(cron = "0 0 12 * * *", zone = "America/Sao_Paulo")  //tarefa que rodará todos os dias às 12 hrs, em qualquer dia da semana e do mês
	public void notificarUserTrocaSenha() throws UnsupportedEncodingException, MessagingException, InterruptedException {
		
		
		
		List<Usuario> usuarios = usuarioRepository.usuariosSenhaVencida();
		
		
		for(Usuario usuario : usuarios) {
			
			
			StringBuilder msg = new StringBuilder();
			
			msg.append("Olá, ").append(usuario.getPessoa().getNome()).append("<br/>");
			
			msg.append("Está na hora de trocar sua senha, pois a mesma já tem mais de 10 dias!").append("<br/>");
			
			msg.append("Troque sua senha da Loja Virtual do Jdev Treinamentos");
			
			serviceSendEmail.enviarEmailHtml("Troca de senha",msg.toString(), usuario.getLogin());
			
			
			
			
			Thread.sleep(3000); //demorar 3 segundos até o proximo envio de e-mail para nao sobrecarregar/estourar a memoria do servidor
			
			
			
			
		}
		
		
		
		
	}
	




}
