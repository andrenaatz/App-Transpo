package br.com.cursojava.tarefa;

import br.com.apptarefadao.situacao.Situacao;
import br.com.apptarefadao.tarefa.Tarefa;
<<<<<<< HEAD:apptarefas/src/main/java/br/com/cursojava/apptarefas/tarefa/TarefaBusiness.java
import br.com.cursojava.apptarefasfacade.utils.ValidationResult;
=======
import br.com.cursojava.utils.ValidationResult;
>>>>>>> f08cdc3d34af9118c8d24617e38c6f91be9206de:app-tarefas-business/src/main/java/br/com/cursojava/tarefa/TarefaBusiness.java

public class TarefaBusiness {

	public ValidationResult validar(Tarefa tarefa) {
		ValidationResult result = new ValidationResult();

		String nome = tarefa.getNome();
		String descricao = tarefa.getDescricao();
		Situacao situacao = tarefa.getSituacao();
		if (nome == null || "".equals(nome.trim())) {
			result.addErrorMessage("nome", "O campo Nome � Obrigat�rio!");
		} else if (nome.length() <= 3) {
			result.addErrorMessage("nome", "O campo Nome deve possuir, no m�nimo, 3 caracteres!");
		}
		if (descricao == null || "".equals(descricao.trim())) {
			result.addErrorMessage("descricao", "O campo Descricao � Obrigat�rio!");
		} else if (descricao.length() < 20) {
			result.addErrorMessage("descricao", "O campo Descri��o deve possuir, no m�nimo, 20 caracteres!");
		}
		try {
			if (situacao == null || "".equals(situacao.getNome().trim())) {
				result.addErrorMessage("situacao", "O campo Situa��o � obrigat�rio");
			}
		} catch (NullPointerException e) {
			result.addErrorMessage("situacao", "O campo Situa��o � obrigat�rio");
		}
		return result;
	}

}
