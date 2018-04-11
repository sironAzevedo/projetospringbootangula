package br.com.projeto.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import br.com.projeto.model.entity.MesSalario;

public interface MesSalarioRepository extends Repository<MesSalario, Integer> {

	MesSalario save(MesSalario mesSalario);

	void delete(MesSalario mesSalario);

	List<MesSalario> findAll();

	MesSalario findOne(Integer id);

}
