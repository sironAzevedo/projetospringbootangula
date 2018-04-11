package br.com.projeto.projetoCommon.valueObjects;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 */
public class Mensagem implements Serializable {

	private static final long serialVersionUID = 1L;
	private String remetente;
	private Set<DestinatarioVO> destinatarios;
	private String assunto;
	private String texto;
	private boolean html;
	private boolean anexo;

	public Mensagem() {
	}

	public Mensagem(String remetente, Set<DestinatarioVO> destinatarios, String assunto, String texto, boolean html) {
		this.remetente = remetente;
		this.destinatarios = destinatarios;
		this.assunto = assunto;
		this.texto = texto;
		this.html = html;
	}

	public Mensagem(String remetente, Set<DestinatarioVO> destinatarios, String assunto, String texto) {
		this(remetente, destinatarios, assunto, texto, false);
	}

	public Mensagem(String remetente, Set<DestinatarioVO> destinatarios, String assunto, String texto, boolean html,
			boolean anexo) {
		this.remetente = remetente;
		this.destinatarios = destinatarios;
		this.assunto = assunto;
		this.texto = texto;
		this.html = html;
		this.anexo = anexo;
	}

	public String getRemetente() {
		return remetente;
	}

	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}

	public Set<DestinatarioVO> getDestinatarios() {
		return destinatarios;
	}

	public void setDestinatarios(Set<DestinatarioVO> destinatarios) {
		this.destinatarios = destinatarios;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public boolean isHtml() {
		return html;
	}

	public void setHtml(boolean html) {
		this.html = html;
	}

	public boolean isAnexo() {
		return anexo;
	}

	public void setAnexo(boolean anexo) {
		this.anexo = anexo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}