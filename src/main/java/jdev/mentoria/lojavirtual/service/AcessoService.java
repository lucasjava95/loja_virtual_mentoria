package jdev.mentoria.lojavirtual.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdev.mentoria.lojavirtual.model.Acesso;
import jdev.mentoria.lojavirtual.repository.AcessoRepository;



@Service
public class AcessoService {
	
	
	@Autowired
	private AcessoRepository acessoRepository;
	
	
	
	public Acesso save(Acesso acesso) {
		
		
/*Ao contrário se estivesso dentro do repository, pode ser feito qualquer validacao antes de salvar*/	
		
	 return acessoRepository.save(acesso);
		
		
		
	}
	

}
