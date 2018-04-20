package br.com.projeto.model.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table(name = "tb_conta")
@Entity
public class ContaModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_conta")
	private Integer codigo;

	@Column(name = "tipo_conta")
	private String tipoConta;

	@Column(name = "valor_conta")
	private BigDecimal valorConta;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_vencimento")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataVencimento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_pagamento")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataPagamento;

	@Column(name = "ds_comentario")
	private String dsComentario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_mes_salario", nullable = true)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private MesSalario mesSalario;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public BigDecimal getValorConta() {
		return valorConta;
	}

	public void setValorConta(BigDecimal valorConta) {
		this.valorConta = valorConta;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getDsComentario() {
		return dsComentario;
	}

	public void setDsComentario(String dsComentario) {
		this.dsComentario = dsComentario;
	}

	public MesSalario getMesSalario() {
		return mesSalario;
	}

	public void setMesSalario(MesSalario mesSalario) {
		this.mesSalario = mesSalario;
	}

	@Override
	public String toString() {
		return "ContaModel [codigo=" + codigo + ", tipoConta=" + tipoConta + ", valorConta=" + valorConta
				+ ", dataVencimento=" + dataVencimento + ", dataPagamento=" + dataPagamento + ", dsComentario="
				+ dsComentario + ", mesSalario=" + mesSalario + "]";
	}
}
