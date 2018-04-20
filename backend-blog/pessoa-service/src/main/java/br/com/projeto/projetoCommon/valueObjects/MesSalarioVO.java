package br.com.projeto.projetoCommon.valueObjects;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.projeto.model.entity.Mes;

public class MesSalarioVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer codigo;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Mes mes;

	private BigDecimal valorSalario;

	public MesSalarioVO() {
		super();
	}

	public MesSalarioVO(Integer codigo, Mes mes, BigDecimal valorSalario) {
		super();
		this.codigo = codigo;
		this.mes = mes;
		this.valorSalario = valorSalario;
	}

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
