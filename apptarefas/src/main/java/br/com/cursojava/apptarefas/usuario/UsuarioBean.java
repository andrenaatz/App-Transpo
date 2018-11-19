package br.com.cursojava.apptarefas.usuario;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import br.com.cursojava.apptarefas.utils.AbstractBean;

@ManagedBean
@ViewScoped
public class UsuarioBean extends AbstractBean{

	private String oid;
	private List<Usuario> usuarios;
	private boolean novo = true;
	private boolean podeEditar;
	private UsuarioFacade facade = new UsuarioFacade();
	private Usuario usuarioAtual = facade.novoUsuario();
	Usuario usuarioSelecionado =  facade.novoUsuario();

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
		if ("novo".equals(oid)) {
			novo();
		} else {
			try {
				Integer id = Integer.parseInt(oid);
				usuarioAtual = facade.carregarUsuario(id);
				novo = false;
			} catch (Exception e) {
				addMessage("ID Inv�lido!", FacesMessage.SEVERITY_ERROR);
			}
		}
	}

	public boolean isPodeEditar() {
		return podeEditar;
	}

	public void setPodeEditar(boolean podeEditar) {
		this.podeEditar = podeEditar;
	}

	public List<Usuario> getUsuarios() {
		if (usuarios == null || usuarios.isEmpty()) {
			usuarios = facade.carregarUsuarios();
		}
		return usuarios;
	}

	public StatusUsuario[] getOpcoesStatus() {
		return StatusUsuario.values();
	}

	public void novo() {
		this.usuarioAtual = facade.novoUsuario();
		novo = true;
		editar();

	}

	public void editar() {
		this.setPodeEditar(true);
	}

	public String listar() {
		novo();
		return "./lista.jsf";
	}

	public void salvar() {
		boolean ok = false;
		if (usuarioAtual != null) {
			if (usuarioAtual.getDataHoraCriacao() == null) {
				usuarioAtual.setDataHoraCriacao(new Date());
			}
			usuarioAtual.setDataHoraAtualizacao(new Date());
			ok = facade.salvar(usuarioAtual);
		}
		if (ok) {
			addMessage("Usu�rio salva com sucesso", FacesMessage.SEVERITY_INFO);
			novo = false;
			podeEditar = false;
		} else {
			addMessage("N�o foi poss�vel salvar o Usu�rio", FacesMessage.SEVERITY_ERROR);
		}
	}

	public void remover() {
		boolean ok = false;
		if (usuarioAtual != null && !novo) {
			ok = facade.removerContato(usuarioAtual);
			if (ok) {
				addMessage("Usu�rio removida com Sucesso!", FacesMessage.SEVERITY_INFO);
				novo();
			} else {
				addMessage("N�o fo poss�vel remover o Usu�rio!", FacesMessage.SEVERITY_ERROR);
			}
		}
	}

	private void addMessage(String mensagem, Severity prioridade) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(mensagem);
		message.setSeverity(prioridade);
		context.addMessage(null, message);
	}

	public Integer getId() {
		return usuarioAtual != null ? usuarioAtual.getId() : null;
	}

	public void setId(Integer id) {
		if (usuarioAtual != null) {
			usuarioAtual.setId(id);
		}
	}

	public String getNome() {
		return usuarioAtual != null ? usuarioAtual.getNome() : "";
	}

	public void setNome(String nome) {
		if (usuarioAtual != null) {
			usuarioAtual.setNome(nome);
		}
	}

	public String getEmail() {
		return usuarioAtual != null ? usuarioAtual.getEmail() : "";
	}

	public void setEmail(String email) {
		if (usuarioAtual != null) {
			usuarioAtual.setEmail(email);
		}
	}

	public String getSenha() {
		return usuarioAtual != null ? usuarioAtual.getSenha() : "";
	}

	public void setSenha(String senha) {
		if (usuarioAtual != null) {
			usuarioAtual.setSenha(senha);
		}
	}

	public Date getDataHoraCriacao() {
		return usuarioAtual != null ? usuarioAtual.getDataHoraCriacao() : null;
	}

	public void setDataHoraCriacao(Date dataHoraCriacao) {
		if (usuarioAtual != null) {
			usuarioAtual.setDataHoraCriacao(dataHoraCriacao);
		}
	}

	public Date getDataHoraAtualizacao() {
		return usuarioAtual != null ? usuarioAtual.getDataHoraAtualizacao() : null;
	}

	public void setDataHoraAtualizacao(Date dataHoraAtualizacao) {
		if (usuarioAtual != null) {
			usuarioAtual.setDataHoraAtualizacao(dataHoraAtualizacao);
		}
	}

	public StatusUsuario getStatus() {
		return usuarioAtual != null ? usuarioAtual.getStatus() : null;
	}

	public void setStatus(StatusUsuario status) {
		if (usuarioAtual != null) {
			usuarioAtual.setStatus(status);
		}
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario UsuarioSelecionado) {
		this.usuarioSelecionado = UsuarioSelecionado;
	}

	public String cancelar() {
		usuarioSelecionado = usuarioAtual;
		System.out.println("Bot�o Cancelar Pressionado " + usuarioAtual.getId());
		return null;
	}

	public String selecionar() {
	
		System.out.println("Bot�o Selecionar Pressionado " + usuarioSelecionado);
		return null;

	}
	
	public void selecionarUsuario(ValueChangeEvent e) {
		System.out.println(e);
	}
}
