package jdev.mentoria.lojavirtual;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import jdev.mentoria.lojavirtual.controller.PessoaController;
import jdev.mentoria.lojavirtual.model.PessoaFisica;
import jdev.mentoria.lojavirtual.model.PessoaJuridica;
import jdev.mentoria.lojavirtual.repository.PessoaRepository;
import jdev.mentoria.lojavirtual.service.PessoaUserService;
import junit.framework.TestCase;

@Profile("test")
@SpringBootTest(classes = LojaVirtualMentoriaApplication.class)
public class TestePessoaUsuario extends TestCase {


   @Autowired
   private PessoaUserService pessoaUserService;
   
   
   
   @Autowired
   private PessoaRepository pessoaRepository;
   
   
   @Autowired
   private PessoaController pessoaController;
   
   
   
   @Test
   public void testeCadPessoaFisica() throws ExceptionMentoriaJava {
	   
	   
	   PessoaJuridica pessoaJuridica = new PessoaJuridica();
	   
	   pessoaJuridica.setCnpj("204.994.0001/23");
	   
	   pessoaJuridica.setInscricEstadual("929-223-333/88");
	   
	   pessoaJuridica.setNomeFantasia("Jdev Treinamentos");
	   
	   pessoaJuridica.setRazaoSocial("Jdev Treinamentos");
	   
	   pessoaJuridica.setNome("Jdev Treinamentos");
	   
	   pessoaJuridica.setEmail("contato@jdevtreinamentos.com.br");
	   
	   pessoaJuridica.setTelefone("(45)98838-2344");
	   
       pessoaController.salvarPj(pessoaJuridica);	   
	   
	   
	 
	   
	   
	   
	   
   }



}
