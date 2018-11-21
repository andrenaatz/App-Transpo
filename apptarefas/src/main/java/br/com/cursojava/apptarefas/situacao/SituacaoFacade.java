package br.com.cursojava.apptarefas.situacao;

import java.util.Date;
import java.util.List;


import br.com.cursojava.apptarefas.utils.ValidationResult;

public class SituacaoFacade {

	private SituacaoRepositorio repositorio = new SituacaoRepositorio();
	private SituacaoBusiness business = new SituacaoBusiness();

	public Situacao novaSituacao() {
		return new Situacao();
	}

	public Situacao carregarSituacao(Integer id) {
		return repositorio.buscarPorId(id);
	}

	public List<Situacao> carregarSituacoes() {
		return repositorio.buscarTodos();
	}

	public ValidationResult salvar(Situacao situacaoAtual) {
		boolean ok = false;
		situacaoAtual.setDataHoraAtualizacao(new Date());
		ValidationResult result = business.validar(situacaoAtual);
		if (result.isOk()) {
			if (situacaoAtual.getId() == null) {
				ok = repositorio.inserir(situacaoAtual);
				System.out.println("Inserindo situa��o no Banco: " + situacaoAtual.getNome());
			} else {
				ok = repositorio.atualizar(situacaoAtual);
				System.out.println("Atualizando situa��o no Banco: " + situacaoAtual.getNome());
			}
			if (!ok) {
				result.addErrorMessage("persistencia", "N�o foi poss�vel salvar os dados do projeto");
			}
		}
		return result;
	}

	public boolean removerSituacao(Situacao situacaoAtual) {
		return repositorio.remover(situacaoAtual.getId());

	}

}
