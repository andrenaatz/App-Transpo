package br.com.cursojava.apptarefas.usuario;

import java.util.List;

public class UsuarioFacade {
	
	public boolean isNomeValido(String nome) {
		//n�o pode ser igual a outro usuario
		return nome != null && nome.length() <= 120;
	}
	
	public boolean isEmailValido() {
		return email != null && email.;
	}
	
	public boolean isSenhaValida(String senha) {
		//m�nimo 6 caracteres
		return senha != null && senha.length() >= 6;
	}
	
}	
