package br.com.cursojava.apptarefas.situacao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.apptarefadao.situacao.Situacao;
import br.com.apptarefadao.situacao.StatusSituacao;
import br.com.apptarefadao.situacao.TipoSituacao;
import br.com.cursojava.apptarefas.utils.AbstractBean;
import br.com.cursojava.apptarefasfacade.situacao.SituacaoFacade;
import br.com.cursojava.apptarefasfacade.utils.ValidationResult;

@ManagedBean(name = "situacaoBean")
@ViewScoped
public class SituacaoBean extends AbstractBean {

	private SituacaoFacade facade = new SituacaoFacade();
	private Situacao situacaoAtual = facade.novaSituacao();
	private List<Situacao> situacoes;
	private String oid;
	private boolean novo = true;
	private boolean podeEditar = false;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
		if ("novo".equals(oid)) {
			situacaoAtual = facade.novaSituacao();
			novo();
		} else {
			try {
				Integer id = Integer.parseInt(oid);
				setSituacaoAtual(facade.carregarSituacao(id));
				novo = false;
			} catch (NumberFormatException ex) {
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage message = new FacesMessage("Id inv�lido");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				context.addMessage(null, message);
			}
		}
	}

	public Integer getId() {
		return situacaoAtual != null ? situacaoAtual.getId() : null;
	}

	public void setId(Integer id) {
		if (situacaoAtual != null) {
			situacaoAtual.setId(id);
		}
	}

	

	public String getNome() {
		return situacaoAtual != null ? situacaoAtual.getNome() : "";
	}

	public void setNome(String nome) {
		if (situacaoAtual != null) {
			situacaoAtual.setNome(nome);
		}
	}

	

	public TipoSituacao getTipo() {
		return situacaoAtual != null ? situacaoAtual.getTipo() : null;
	}

	public void setTipo(TipoSituacao tipo) {
		if (situacaoAtual != null) {
			situacaoAtual.setTipo(tipo);
		}
	}


	public Date getDataHoraCriacao() {
		return situacaoAtual != null ? situacaoAtual.getDataHoraCriacao() : null;

	}

	public void setDataHoraCriacao(Date dataHoraCriacao) {
		if (situacaoAtual != null) {
			situacaoAtual.setDataHoraCriacao(dataHoraCriacao);
		}
	}

	public Date getDataHoraAtualizacao() {
		return situacaoAtual != null ? situacaoAtual.getDataHoraAtualizacao() : null;
	}

	public void setDataHoraAtualizacao(Date dataHoraAtualizacao) {
		if (situacaoAtual != null) {
			situacaoAtual.setDataHoraAtualizacao(dataHoraAtualizacao);
		}
	}

	public Date getDataHoraRemocao() {
		return situacaoAtual != null ? situacaoAtual.getDataHoraRemocao() : null;
	}

	public void setDataHoraRemocao(Date dataHoraRemocao) {
		if (situacaoAtual != null) {
			situacaoAtual.setDataHoraRemocao(dataHoraRemocao);
		}
	}

	public StatusSituacao getStatus() {
		return situacaoAtual != null ? situacaoAtual.getStatus() : null;
	}

	public void setStatus(StatusSituacao status) {
		if (situacaoAtual != null) {
			situacaoAtual.setStatus(status);
		}
	}

	public TipoSituacao[] getTipos() {
		return TipoSituacao.values();
	}

	public StatusSituacao[] getSituacao() {
		return StatusSituacao.values();
	}

	public void salvar() {

		ValidationResult result;
		if (situacaoAtual != null) {
			if (situacaoAtual.getDataHoraCriacao() == null) {
				situacaoAtual.setDataHoraCriacao(new Date());
			}
			situacaoAtual.setDataHoraAtualizacao(new Date());
			result = facade.salvar(situacaoAtual);

			if (result.isOk()) {
				addMessage("Situa��o salva com sucesso", FacesMessage.SEVERITY_INFO);
				setNovo(false);
				setPodeEditar(false);
			} else {
				Map<String, String> messages = result.getMessages();
				for (Map.Entry<String, String> message : messages.entrySet()) {
					addMessage(message.getValue(), FacesMessage.SEVERITY_ERROR);
				}
			}

		}
	}

	public void remover() {
		boolean ok = false;

		if (situacaoAtual != null && !novo) {
			situacaoAtual.setDataHoraRemocao(new Date());
			ok = facade.removerSituacao(situacaoAtual);
			if (ok) {
				addMessage("Situa��o removida com sucesso", FacesMessage.SEVERITY_INFO);
				novo();
			} else {
				addMessage("N�o foi poss�vel remover a situa��o", FacesMessage.SEVERITY_ERROR);

			}

		}
	}

	public void editar() {
		this.setPodeEditar(true);

	}

	public String listar() {
		novo();
		return "lista.jsf?faces-redirect=true";
	}

	public String novo() {
		this.setSituacaoAtual(facade.novaSituacao());
		novo = true;
		editar();
		return "";
	}

	public List<Situacao> getSituacoes() {
		if (situacoes == null || situacoes.isEmpty()) {
			situacoes = facade.carregarSituacoes();
		}
		return situacoes;
	}


	private void addMessage(String messagem, Severity severidade) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(messagem);
		message.setSeverity(severidade);
		context.addMessage(null, message);
	}

	public boolean isPodeEditar() {
		return podeEditar;
	}

	public void setPodeEditar(boolean podeEditar) {
		this.podeEditar = podeEditar;
	}


	public void setNovo(boolean novo) {
		this.novo = novo;
	}

	public Situacao getSituacaoAtual() {
		return situacaoAtual;
	}

	public void setSituacaoAtual(Situacao situacaoAtual) {
		this.situacaoAtual = situacaoAtual;
	}

	public void cancelar() {

	}

	public void selecionar() {
		System.out.println("situacao Atual: " + situacaoAtual);
	}

}