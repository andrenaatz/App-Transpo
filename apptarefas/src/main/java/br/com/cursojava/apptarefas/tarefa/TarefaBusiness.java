package br.com.cursojava.apptarefas.tarefa;

import br.com.cursojava.apptarefas.situacao.Situacao;
import br.com.cursojava.apptarefas.utils.ValidationResult;

public class TarefaBusiness {
	
	public ValidationResult validar(Tarefa tarefa) {
		ValidationResult result = new ValidationResult();
		
		String nome = tarefa.getNome();
		if(nome != null || "".equals(nome.trim())) {
			result.addErrorMessage("nome", "O campo Nome � Obrigat�rio!");
		}
		String descricao = tarefa.getDescricao();
		if(descricao != null || "".equals(descricao.trim())) {
			result.addErrorMessage("descricao", " O campo Descricao � Obrigat�rio!");
		}
		Situacao situacao = tarefa.getSituacao();
		if(situacao != null || "".equals(situacao.getNome().trim())) {
			result.addErrorMessage("situacao", "O campo Situa��o � obrigat�rio");
		}
		
		return result;
	}

}
