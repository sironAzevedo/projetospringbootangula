package br.com.projeto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "tb_mes_salario")
@Entity
public class MesSalario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_mes_salario")
	private Integer codigo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_mes", nullable = true)
	private Mes mes;

	@Column(name = "valor_salario")
	private Integer valorSalario;

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

	public Integer getValorSalario() {
		return valorSalario;
	}

	public void setValorSalario(Integer valorSalario) {
		this.valorSalario = valorSalario;
	}

	@Override
	public String toString() {
		return "MesSalario [codigo=" + codigo + ", mes=" + mes + ", valorSalario=" + valorSalario + "]";
	}
}
