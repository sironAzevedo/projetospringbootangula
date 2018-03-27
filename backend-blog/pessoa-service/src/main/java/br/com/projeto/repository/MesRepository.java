package br.com.projeto.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import br.com.projeto.model.Mes;

public interface MesRepository extends Repository<Mes, Integer> {

	List<Mes> findAll();

	Mes findOne(Integer id);
}
