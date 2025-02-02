package jdev.mentoria.lojavirtual;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jdev.mentoria.lojavirtual.controller.AcessoController;
import jdev.mentoria.lojavirtual.model.Acesso;

@SpringBootTest(classes = LojaVirtualMentoriaApplication.class) //especifica a classe que será testada
class LojaVirtualMentoriaApplicationTests {
	
	
	
	@Autowired
	private AcessoController acessoController;
	
	

	@Test
	public void testCadastraAcesso() {
		
		
		Acesso acesso = new Acesso();
		
        
        acesso.setDescricao("ROLE_SECRETARIO");
        
       // acessoService.save(acesso);

       acessoController.salvarAcesso(acesso); 
        
		
	}

}
