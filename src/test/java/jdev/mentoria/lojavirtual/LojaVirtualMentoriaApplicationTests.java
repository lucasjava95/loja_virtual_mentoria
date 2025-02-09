package jdev.mentoria.lojavirtual;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jdev.mentoria.lojavirtual.controller.AcessoController;
import jdev.mentoria.lojavirtual.model.Acesso;
import jdev.mentoria.lojavirtual.repository.AcessoRepository;
import junit.framework.TestCase;

@SpringBootTest(classes = LojaVirtualMentoriaApplication.class) //especifica a classe que será testada
class LojaVirtualMentoriaApplicationTests extends TestCase {    //TestCase para se fazer testes unitarios
	
	
	
	@Autowired
	private AcessoController acessoController;
	
	
	@Autowired
	private AcessoRepository acessoRepository;
	

	@Test
	public void testCadastraAcesso() {
		
		
		Acesso acesso = new Acesso();
		
        
        acesso.setDescricao("ROLE_TESTE_JUNIT");
        
       
        // acessoService.save(acesso);
        
        
      acesso =   acessoController.salvarAcesso(acesso).getBody(); 
      
      
      assertEquals(true, acesso.getId() > 0);  /*Metodo do TestCase, que espera um true para a condicao: acesso.getId() >  0 */
      
      
      assertEquals("ROLE_TESTE_JUNIT", acesso.getDescricao());
      
      
      /*Teste de carregamento*/
       
      Acesso acesso2 = acessoRepository.findById(acesso.getId()).get();
      
      
      assertEquals(acesso.getId(), acesso2.getId());
      
      
      
      /*Teste de delete*/
      
      acessoRepository.deleteById(acesso2.getId());
      
      
      acessoRepository.flush(); /*Na linha acima, as alteraçoes (no caso, delete) ainda esta em memoria e, o flush serve para rodar as alteracoes no BD*/
      
      
      Acesso acesso3 = acessoRepository.findById(acesso2.getId()).orElse(null); /*p/ nao dar Excecao, se tiver o objeto (acesso2) retorna ele, se nao tiver retorna NULL*/
      
      
      assertEquals(true, acesso3 == null);
      
      
      /*Teste de Query*/
      
      acesso = new Acesso();
      
      
      acesso.setDescricao("ROLE_ALUNO");
      
      
      acesso =  acessoController.salvarAcesso(acesso).getBody();
      
      
      List<Acesso> acessos = acessoRepository.buscarAcessoDesc("ALUNO".trim().toUpperCase());
      
      
      assertEquals(1, acessos.size());
      
      acessoRepository.deleteById(acesso.getId());
      
      
      
        
		
	}

}
