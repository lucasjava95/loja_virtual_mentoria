package jdev.mentoria.lojavirtual;

import jdev.mentoria.lojavirtual.util.ValidaCNPJ;
import jdev.mentoria.lojavirtual.util.ValidaCPF;

public class TesteCPFCNPJ {
	
	
	public static void main(String[] args) {
		
		
	boolean isCnpj = 	ValidaCNPJ.isCNPJ("38.017.829/0001-70");
	
	boolean isCpf  = ValidaCPF.isCPF("067.796.723-34");
	
	
	System.out.println("Cnpj válido: " + isCnpj);
	
	System.out.println("CPF válido: " + isCpf);
		
		
	}
	

}
