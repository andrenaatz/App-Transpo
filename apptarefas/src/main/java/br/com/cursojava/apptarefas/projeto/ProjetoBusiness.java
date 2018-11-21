package br.com.cursojava.apptarefas.projeto;

import br.com.cursojava.apptarefas.utils.ValidationResult;

public class ProjetoBusiness {
	
	public ValidationResult validar(Projeto projeto){
		ValidationResult resultado = new ValidationResult();
		
		String nome = projeto.getNome();
		if(nome == null || "".equals(nome.trim())){
			resultado.addErrorMessage("nome", "O campo nome � Obrigat�rio");
		}else if(nome.length() < 3){
			resultado.addErrorMessage("nome", "O campo nome deve possuir ao menos 3 caracteres");
		}
		return resultado;
	}
	
	

}
