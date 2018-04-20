package br.com.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.model.entity.MesSalario;

@Repository
public interface MesSalarioRepository extends JpaRepository<MesSalario, Integer> {

	@SuppressWarnings("unchecked")
	MesSalario save(MesSalario mesSalario);

	void delete(MesSalario mesSalario);

	List<MesSalario> findAll();

	MesSalario findOne(Integer id);

}
