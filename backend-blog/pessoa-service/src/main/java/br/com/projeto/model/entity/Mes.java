package br.com.projeto.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_mes")
@Entity
public class Mes {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_mes")
	private Integer codigo;

	@Column(name = "ds_mes")
	private String dsMes;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDsMes() {
		return dsMes;
	}

	public void setDsMes(String dsMes) {
		this.dsMes = dsMes;
	}

	@Override
	public String toString() {
		return "Mes [codigo=" + codigo + ", dsMes=" + dsMes + "]";
	}
}
