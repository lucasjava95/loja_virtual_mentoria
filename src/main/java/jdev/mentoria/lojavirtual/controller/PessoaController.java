package jdev.mentoria.lojavirtual.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jdev.mentoria.lojavirtual.ExceptionMentoriaJava;
import jdev.mentoria.lojavirtual.model.PessoaFisica;
import jdev.mentoria.lojavirtual.model.PessoaJuridica;
import jdev.mentoria.lojavirtual.repository.PessoaRepository;
import jdev.mentoria.lojavirtual.service.PessoaUserService;
import jdev.mentoria.lojavirtual.util.ValidaCNPJ;
import jdev.mentoria.lojavirtual.util.ValidaCPF;


@RestController
public class PessoaController {
	
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	@Autowired
	private PessoaUserService pessoaUserService;
	
	
	
    @ResponseBody
    @PostMapping(value = "**/salvarPj")
	public ResponseEntity<PessoaJuridica> salvarPj(@RequestBody PessoaJuridica pessoaJuridica) throws ExceptionMentoriaJava{
    	
         if(pessoaJuridica == null) {
        	 
        	 
        	 throw new ExceptionMentoriaJava("Pessoa Juridica não pode ser Null");
        	 	 
         }
         
         
         if(pessoaJuridica.getId() == null && pessoaRepository.existeCnpjCadastrado(pessoaJuridica.getCnpj()) != null) {
        	 
       
        	 
        	 throw new ExceptionMentoriaJava("Já existe CNPJ cadastrado com o numero: "  + pessoaJuridica.getCnpj());
        	 
        	 
         }
         
         
         if(pessoaJuridica.getId() == null && pessoaRepository.existeInscrEstadualCadastrado(pessoaJuridica.getInscricEstadual()) != null) {
        	 
       
        	 
        	 throw new ExceptionMentoriaJava("Já existe Inscrição Estadual cadastrado com o numero: "  + pessoaJuridica.getCnpj());
        	 
        	 
         }
         
         
         if(!ValidaCNPJ.isCNPJ(pessoaJuridica.getCnpj())) {
        	 
        	 throw new ExceptionMentoriaJava("CNPJ " + pessoaJuridica.getCnpj() + " está inválido!");
        	 
        	 
         }
         
         
         
         
         pessoaJuridica = pessoaUserService.salvarPessoaJuridica(pessoaJuridica);
    	
    	
 
    	return new ResponseEntity<PessoaJuridica>(pessoaJuridica, HttpStatus.OK);
    	

    }
    
    
    @ResponseBody
    @PostMapping(value = "**/salvarPf")
	public ResponseEntity<PessoaFisica> salvarPf(@RequestBody PessoaFisica pessoaFisica) throws ExceptionMentoriaJava{
    	
         if(pessoaFisica == null) {
        	 
        	 
        	 throw new ExceptionMentoriaJava("Pessoa Fisica não pode ser Null");
        	 	 
         }
         
         
         if(pessoaFisica.getId() == null && pessoaRepository.existeCpfCadastrado(pessoaFisica.getCpf()) != null) {
        	 
       
        	 
        	 throw new ExceptionMentoriaJava("Já existe CPF cadastrado com o numero: "  + pessoaFisica.getCpf());
        	 
        	 
         }
         
         
     
         
         
         if(!ValidaCPF.isCPF(pessoaFisica.getCpf())) {
        	 
        	 throw new ExceptionMentoriaJava("CPF " + pessoaFisica.getCpf() + " está inválido!");
        	 
        	 
         }
         
         
         
         
         pessoaFisica = pessoaUserService.salvarPessoaFisica(pessoaFisica);
    	
    	
 
    	return new ResponseEntity<PessoaFisica>(pessoaFisica, HttpStatus.OK);
    	
    
    
    }
    
    
    
	
	

}
