package br.com.cursojava.apptarefas.usuario;

import java.util.Date;
import java.util.List;

import br.com.cursojava.apptarefas.utils.ValidationResult;

public class UsuarioFacade {

	private UsuarioRepositorio repositorio = new UsuarioRepositorio();
	private UsuarioBusiness business  = new UsuarioBusiness();

	public Usuario novoUsuario() {
		return new Usuario();
	}

	public Usuario carregarUsuario(Integer id) {
		return repositorio.buscarPorId(id);
	}

	public ValidationResult salvar(Usuario usuarioAtual) {
		boolean ok = false;
		usuarioAtual.setDataHoraAtualizacao(new Date());
		ValidationResult result = business.validar(usuarioAtual);
		if(result.isOk()){
			if (usuarioAtual.getId() == null) {
				ok = repositorio.inserir(usuarioAtual);
			} else {
				ok = repositorio.atualizar(usuarioAtual);
			}
			if(!ok){
				result.addErrorMessage("persistencia", "N�o foi poss�vel salvar os dados do usu�rio.");
			}
		}
		return result;
	}

	public boolean removerContato(Usuario usuarioAtual) {
		boolean ok = false;
		int id = usuarioAtual.getId();
		ok = repositorio.remover(id);
		return ok;
	}

	public List<Usuario> carregarUsuarios() {
		return repositorio.buscarTodos();
	}
}
