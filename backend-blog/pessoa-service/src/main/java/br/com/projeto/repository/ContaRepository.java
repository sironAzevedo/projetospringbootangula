package br.com.projeto.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import br.com.projeto.model.ContaModel;

public interface ContaRepository extends Repository<ContaModel, Integer> {

	// public ContaModel salvarConta(ContaModel conta);

	void save(ContaModel conta);

	void delete(ContaModel conta);

	List<ContaModel> findAll();

	ContaModel findOne(Integer id);

}
