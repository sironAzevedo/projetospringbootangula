package br.com.projeto.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table(name = "tb_mes_salario", schema="blog")
@Entity
public class MesSalario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_mes_salario")
	private Integer codigo;

	@ManyToOne(targetEntity=Mes.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_mes", nullable = false)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Mes mes;

	@Column(name = "valor_salario")
	private BigDecimal valorSalario;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Mes getMes() {
		return mes;
	}

	public void setMes(Mes mes) {
		this.mes = mes;
	}

	public BigDecimal getValorSalario() {
		return valorSalario;
	}

	public void setValorSalario(BigDecimal valorSalario) {
		this.valorSalario = valorSalario;
	}

	@Override
	public String toString() {
		return "MesSalario [codigo=" + codigo + ", mes=" + mes + ", valorSalario=" + valorSalario + "]";
	}
}
