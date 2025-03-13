package jdev.mentoria.lojavirtual.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jdev.mentoria.lojavirtual.model.PessoaJuridica;
import jdev.mentoria.lojavirtual.model.Usuario;
import jdev.mentoria.lojavirtual.repository.PessoaRepository;
import jdev.mentoria.lojavirtual.repository.UsuarioRepository;

@Service
public class PessoaUserService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate; //classe JDBC do Spring
	
	
	
	
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
			
			
			usuarioRepository.insereAcessoUserPj(usuarioPj.getId());
			
			
			/*Fazer envio de e-mail do login e senha do usuario*/
			
			
			
			
			
			
			
			
		}
		
		return pessoaJuridica;
		
		
		
		
	}
	
	
	

}
