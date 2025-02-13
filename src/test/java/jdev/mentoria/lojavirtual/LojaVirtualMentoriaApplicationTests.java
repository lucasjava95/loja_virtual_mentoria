package jdev.mentoria.lojavirtual;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	
	@Autowired
	private WebApplicationContext wac;  /*Objeto que pega as informacoes do contexto da aplicacao*/
	
	
	
	/*Teste do End-point de salvar*/
	@Test
	public void testRestApiCadastroAcesso() throws Exception {
		
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		
		MockMvc mockMvc = builder.build();
		
		
		Acesso acesso = new Acesso();
		
		acesso.setDescricao("ROLE_TESTE_MOCK NOVO");
		
		
	    ObjectMapper objectMapper = new ObjectMapper();
		
		
		ResultActions retornoApi = mockMvc
				    
				                   .perform(MockMvcRequestBuilders.post("/salvarAcesso")
				                		   
				                		   
				                    .content(objectMapper.writeValueAsString(acesso))
				                    
				                	.accept(MediaType.APPLICATION_JSON)
				                	
				                     .contentType(MediaType.APPLICATION_JSON));  /*indica o tipo de dados da API (no caso, JSON)*/
		
		
		        System.out.println("Retorno da API: "  + retornoApi.andReturn().getResponse().getContentAsString());
		
		
		
		      /*Converte o retorno da API (JSON) para o objeto em questao (Acesso)*/
		        
		    Acesso objetoRetorno = objectMapper.readValue(retornoApi.andReturn().getResponse().getContentAsString(), Acesso.class);
   
   
     assertEquals(acesso.getDescricao(), objetoRetorno.getDescricao());
		
		                             
		      
		
		
		
	}
	
	
	/*Teste do End-point de deletar*/
	@Test
	public void testRestApiDeleteAcesso() throws Exception {
		
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		
		MockMvc mockMvc = builder.build();
		
		
		Acesso acesso = new Acesso();
		
		acesso.setDescricao("ROLE_TESTE_MOCK_DELETE");
		
		
		acesso = acessoRepository.save(acesso);
		
		
	    ObjectMapper objectMapper = new ObjectMapper();
		
		
		ResultActions retornoApi = mockMvc
				    
				                   .perform(MockMvcRequestBuilders.post("/deleteAcesso")
				                		   
				                		   
				                    .content(objectMapper.writeValueAsString(acesso))
				                    
				                	.accept(MediaType.APPLICATION_JSON)
				                	
				                     .contentType(MediaType.APPLICATION_JSON));  /*indica o tipo de dados da API (no caso, JSON)*/
		
		
		        System.out.println("Retorno da API: "  + retornoApi.andReturn().getResponse().getContentAsString());
		
		        System.out.println("Status de retorno: " + retornoApi.andReturn().getResponse().getStatus());
		        
		        
		        assertEquals("Deletado com sucesso", retornoApi.andReturn().getResponse().getContentAsString());
		        
		        assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
                
		
		                       	
	}
	
	
	
	
	
	
	/*Teste do End-point de deletar apenas com o ID*/
	@Test
	public void testRestApiDeleteAcessoId() throws Exception {
		
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		
		MockMvc mockMvc = builder.build();
		
		
		Acesso acesso = new Acesso();
		
		acesso.setDescricao("ROLE_TESTE_MOCK_DELETE_ID");
		
		
		acesso = acessoRepository.save(acesso);
		
		
	    ObjectMapper objectMapper = new ObjectMapper();
		
		
		ResultActions retornoApi = mockMvc
				    
				                   .perform(MockMvcRequestBuilders.delete("/deleteAcessoPorId/" + acesso.getId())
				                		   
				                		   
				                    .content(objectMapper.writeValueAsString(acesso))
				                    
				                	.accept(MediaType.APPLICATION_JSON)
				                	
				                     .contentType(MediaType.APPLICATION_JSON));  /*indica o tipo de dados da API (no caso, JSON)*/
		
		
		        System.out.println("Retorno da API: "  + retornoApi.andReturn().getResponse().getContentAsString());
		
		        System.out.println("Status de retorno: " + retornoApi.andReturn().getResponse().getStatus());
		        
		        
		        assertEquals("Deletado com sucesso (ID)", retornoApi.andReturn().getResponse().getContentAsString());
		        
		        assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
                
		
		                       	
	} 
	
	
	
	
	@Test
	public void testRestApiObterAcessoId() throws Exception {
		
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		
		MockMvc mockMvc = builder.build();
		
		
		Acesso acesso = new Acesso();
		
		acesso.setDescricao("ROLE_TESTE_MOCK_OBTER_ID");
		
		
		acesso = acessoRepository.save(acesso);
		
		
	    ObjectMapper objectMapper = new ObjectMapper();
		
		
		ResultActions retornoApi = mockMvc
				    
				                   .perform(MockMvcRequestBuilders.get("/obterAcesso/" + acesso.getId())
				                		   
				                		   
				                    .content(objectMapper.writeValueAsString(acesso))
				                    
				                	.accept(MediaType.APPLICATION_JSON)
				                	
				                     .contentType(MediaType.APPLICATION_JSON));  /*indica o tipo de dados da API (no caso, JSON)*/
		
		
		        System.out.println("Retorno da API: "  + retornoApi.andReturn().getResponse().getContentAsString());
		
		        System.out.println("Status de retorno: " + retornoApi.andReturn().getResponse().getStatus());
		        
		        
		        
		        Acesso objetoAcesso =  objectMapper.readValue(retornoApi.andReturn().getResponse().getContentAsString(), Acesso.class);
		        
		        
		        
		        assertEquals(objetoAcesso.getDescricao(), acesso.getDescricao());
		        		        
		        assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
                
		
		                       	
	}
	
	
	
	
	@Test
	public void testRestApiObterAcessoDesc() throws Exception {
		
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		
		MockMvc mockMvc = builder.build();
		
		
		Acesso acesso = new Acesso();
		
		acesso.setDescricao("ROLE_TESTE_MOCK_OBTER_ACESSO_LIST");
		
		
		acesso = acessoRepository.save(acesso);
		
		
	    ObjectMapper objectMapper = new ObjectMapper();
		
		
		ResultActions retornoApi = mockMvc
				    
				                   .perform(MockMvcRequestBuilders.get("/buscarPorDesc/OBTER_ACESSO")
				                		   
				                		   
				                    .content(objectMapper.writeValueAsString(acesso))
				                    
				                	.accept(MediaType.APPLICATION_JSON)
				                	
				                     .contentType(MediaType.APPLICATION_JSON));  /*indica o tipo de dados da API (no caso, JSON)*/
		
		
		    		        
		        assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
		        
		        
		        
		        List<Acesso> retornoApiList = objectMapper.readValue(retornoApi.andReturn().getResponse().getContentAsString(), 
		        		
		        		
		        		new TypeReference<List<Acesso>>() {
						});
		        
		        
		        
		        assertEquals(1, retornoApiList.size());
		        
		        
		        assertEquals(acesso.getDescricao(), retornoApiList.get(0).getDescricao());
		        
		        
		        acessoRepository.deleteById(acesso.getId());
		        
                
		
		                       	
	}
	
	
	

	

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
