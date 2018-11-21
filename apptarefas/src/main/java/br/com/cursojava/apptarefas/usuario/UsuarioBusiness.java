package br.com.cursojava.apptarefas.usuario;

import br.com.cursojava.apptarefas.utils.ValidationResult;

public class UsuarioBusiness {
	
	public ValidationResult validar(Usuario usuario){
		ValidationResult resultado = new ValidationResult();
		
		String nome = usuario.getNome();
		if(nome == null || "".equals(nome.trim())){
			resultado.addErrorMessage("nome", "O campo nome � Obrigat�rio.");
		}else if(nome.length() < 3){
			resultado.addErrorMessage("nome", "O campo nome deve possuir ao menos 3 caracteres.");
		}
		
		String email = usuario.getEmail();
		if(!email.contains("@")){
			resultado.addErrorMessage("email", "O formato de email n�o � v�lido.");
		}
		
		String senha = usuario.getSenha();
		if(senha.length() < 8){
			resultado.addErrorMessage("senha", "A senha deve conter pelo menos 8 caracteres");
		}
		
		return resultado;
	}

}
