package jdev.mentoria.lojavirtual;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import jdev.mentoria.lojavirtual.controller.PessoaController;
import jdev.mentoria.lojavirtual.enums.TipoEndereco;
import jdev.mentoria.lojavirtual.model.Endereco;
import jdev.mentoria.lojavirtual.model.PessoaJuridica;
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
	   
	   pessoaJuridica.setInscricEstadual("929-228-333/85");
	   
	   pessoaJuridica.setNomeFantasia("Jdev Treinamentos");
	   
	   pessoaJuridica.setRazaoSocial("Jdev Treinamentos");
	   
	   pessoaJuridica.setNome("Jdev Treinamentos");
	   
	   pessoaJuridica.setEmail("jdev.treinamentos@gmail.com");
	   
	   pessoaJuridica.setTelefone("(44)98838-2322");
	   
	   
	   Endereco endereco1 = new Endereco();
	   
	   endereco1.setBairro("Jardim Dias");
	   
	   endereco1.setCep("60865-290");
	   
	   endereco1.setComplemento("casa azul");
	   
	   endereco1.setEmpresa(pessoaJuridica);
	   
	   endereco1.setNumero("312");
	   
	   endereco1.setPessoa(pessoaJuridica);
	   
	   endereco1.setRuaLogra("av. Dom Pedro");
	   
	   endereco1.setTipoEndereco(TipoEndereco.COBRANCA);
	   
	   endereco1.setUf("PR");
	   
	   endereco1.setCidade("Curitiba");
	   
	   
	   
	   
	   
     Endereco endereco2 = new Endereco();
	   
     endereco2.setBairro("Jardim Dias");
	   
     endereco2.setCep("60865-290");
	   
     endereco2.setComplemento("casa azul");
	   
     endereco2.setEmpresa(pessoaJuridica);
	   
     endereco2.setNumero("126");
	   
     endereco2.setPessoa(pessoaJuridica);
	   
     endereco2.setRuaLogra("avenida Jesus dos Santos");
	   
     endereco2.setTipoEndereco(TipoEndereco.ENTREGA);
	   
     endereco2.setUf("PR");
     
     endereco2.setCidade("Maringá");
     
     
       pessoaJuridica.getEnderecos().add(endereco1);
     
       pessoaJuridica.getEnderecos().add(endereco2);
	   
	   
	 
     pessoaJuridica =   pessoaController.salvarPj(pessoaJuridica).getBody(); //getBody() pois com apenas o salvarPj() retorna-se um ResponseEntity.
       
       
	 assertEquals(true, pessoaJuridica.getId() > 0);
	 
	 
	 for (Endereco endereco: pessoaJuridica.getEnderecos()) {
		 
		 assertEquals(true, endereco.getId() >0);
		 
			
	}
	 
	   
	 
	  assertEquals(2, pessoaJuridica.getEnderecos().size());
	   
	   
	   
   }



}
