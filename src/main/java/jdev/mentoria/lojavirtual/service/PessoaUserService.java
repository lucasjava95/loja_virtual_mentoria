package jdev.mentoria.lojavirtual.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jdev.mentoria.lojavirtual.model.PessoaFisica;
import jdev.mentoria.lojavirtual.model.PessoaJuridica;
import jdev.mentoria.lojavirtual.model.Usuario;
import jdev.mentoria.lojavirtual.repository.PessoaFisicaRepository;
import jdev.mentoria.lojavirtual.repository.PessoaRepository;
import jdev.mentoria.lojavirtual.repository.UsuarioRepository;

@Service
public class PessoaUserService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate; //classe JDBC do Spring
	
	
	
	@Autowired
	private ServiceSendEmail serviceSendEmail;
	
	
	
	
	public PessoaJuridica salvarPessoaJuridica(PessoaJuridica pessoaJuridica) {
		
		
		//pessoaJuridica = pessoaRepository.save(pessoaJuridica);
		
		
		for(int i=0; i < pessoaJuridica.getEnderecos().size(); i++) {
			
			pessoaJuridica.getEnderecos().get(i).setPessoa(pessoaJuridica);
			
			pessoaJuridica.getEnderecos().get(i).setEmpresa(pessoaJuridica);	
			
		}
		
		
		pessoaJuridica = pessoaRepository.save(pessoaJuridica);
		
		
		
		
		Usuario usuarioPj = usuarioRepository.findUserByPessoa(pessoaJuridica.getId(), pessoaJuridica.getEmail());
		
		
		if(usuarioPj == null) {
			
			
			String constraint = usuarioRepository.consultaConstraintAcesso();
			
			if(constraint != null) {
				
			jdbcTemplate.execute("begin; alter table usuarios_acesso drop constraint " + constraint +" ;commit;");
				
				
				
			}
			
			
			usuarioPj = new Usuario();
			
			usuarioPj.setDataAtualSenha(Calendar.getInstance().getTime());
			
			usuarioPj.setEmpresa(pessoaJuridica);
			
			usuarioPj.setPessoa(pessoaJuridica);
			
			usuarioPj.setLogin(pessoaJuridica.getEmail());
			
			
			String senha = "" + Calendar.getInstance().getTimeInMillis();
			
			String senhaCript = new BCryptPasswordEncoder().encode(senha);
			
			usuarioPj.setSenha(senhaCript);
			
			usuarioPj = usuarioRepository.save(usuarioPj);
			
			
			usuarioRepository.insereAcessoUser(usuarioPj.getId());
			
			usuarioRepository.insereAcessoUserPj(usuarioPj.getId(), "ROLE_ADMIN");
			
			
			StringBuilder mensagemHtlm = new StringBuilder();
			
			mensagemHtlm.append("<b>Segue abaixo, seus dados de acesso da Loja Virtual: </b> <br/> <br/>");
			
			mensagemHtlm.append("<b> Login: </b> " + pessoaJuridica.getEmail() + "<br/> <br/>");
			
			mensagemHtlm.append("<b> Senha: </b> " + senha  + "<br/> <br/>");
			
			mensagemHtlm.append("<b> Obrigado! </b>");
			
			
			/*Fazer envio de e-mail do login e senha do usuario*/
			
			
			try {
			
			serviceSendEmail.enviarEmailHtml("Acesso gerado para loja Virtual", mensagemHtlm.toString(), pessoaJuridica.getEmail());
			
			
			}catch(Exception e) {
				
				
				e.printStackTrace();
				
				
			}
				
			
		}
		
		return pessoaJuridica;
		
		
		
		
	}
	
	
	public PessoaFisica salvarPessoaFisica(PessoaFisica pessoaFisica) {
		
		
		
		//pessoaJuridica = pessoaRepository.save(pessoaJuridica);
		
		
				for(int i=0; i < pessoaFisica.getEnderecos().size(); i++) {
					
					pessoaFisica.getEnderecos().get(i).setPessoa(pessoaFisica);
					
				//	pessoaFisica.getEnderecos().get(i).setEmpresa(pessoaFisica);	
					
				}
				
				
				pessoaFisica = pessoaFisicaRepository.save(pessoaFisica);
				
				
				
				
				Usuario usuarioPj = usuarioRepository.findUserByPessoa(pessoaFisica.getId(), pessoaFisica.getEmail());
				
				
				if(usuarioPj == null) {
					
					
					String constraint = usuarioRepository.consultaConstraintAcesso();
					
					if(constraint != null) {
						
					jdbcTemplate.execute("begin; alter table usuarios_acesso drop constraint " + constraint +" ;commit;");
						
						
						
					}
					
					
					usuarioPj = new Usuario();
					
					usuarioPj.setDataAtualSenha(Calendar.getInstance().getTime());
					
					usuarioPj.setEmpresa(pessoaFisica);
					
					usuarioPj.setPessoa(pessoaFisica);
					
					usuarioPj.setLogin(pessoaFisica.getEmail());
					
					
					String senha = "" + Calendar.getInstance().getTimeInMillis();
					
					String senhaCript = new BCryptPasswordEncoder().encode(senha);
					
					usuarioPj.setSenha(senhaCript);
					
					usuarioPj = usuarioRepository.save(usuarioPj);
					
					
					usuarioRepository.insereAcessoUser(usuarioPj.getId());
					
					
					
					StringBuilder mensagemHtlm = new StringBuilder();
					
					mensagemHtlm.append("<b>Segue abaixo, seus dados de acesso da Loja Virtual: </b> <br/> <br/>");
					
					mensagemHtlm.append("<b> Login: </b> " + pessoaFisica.getEmail() + "<br/> <br/>");
					
					mensagemHtlm.append("<b> Senha: </b> " + senha  + "<br/> <br/>");
					
					mensagemHtlm.append("<b> Obrigado! </b>");
					
					
					/*Fazer envio de e-mail do login e senha do usuario*/
					
					
					try {
					
					serviceSendEmail.enviarEmailHtml("Acesso gerado para loja Virtual", mensagemHtlm.toString(), pessoaFisica.getEmail());
					
					
					}catch(Exception e) {
						
						
						e.printStackTrace();
						
						
					}
						
					
				}
				
				return pessoaFisica;
				
		
		
		
		
		
	}
	
	
	
	

}
