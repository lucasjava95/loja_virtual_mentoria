package jdev.mentoria.lojavirtual;

import java.util.Calendar;

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
   private PessoaController pessoaController;
   
   
   
   @Test
   public void testeCadPessoaFisica() throws ExceptionMentoriaJava {
	   
	   
	   PessoaJuridica pessoaJuridica = new PessoaJuridica();
	   
	   pessoaJuridica.setCnpj(" " + Calendar.getInstance().getTimeInMillis());
	   
	   pessoaJuridica.setInscricEstadual("929-123-333/85");
	   
	   pessoaJuridica.setNomeFantasia("Bom Sabor");
	   
	   pessoaJuridica.setRazaoSocial("Bom Sabor");
	   
	   pessoaJuridica.setNome("Bom Sabor");
	   
	   pessoaJuridica.setEmail("bom_sabor");
	   
	   pessoaJuridica.setTelefone("(41)98838-1322");
	   
       pessoaController.salvarPj(pessoaJuridica);	   
	   
	   
	 
	   
	   
	   
	   
   }



}
