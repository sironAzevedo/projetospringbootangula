package br.com.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.model.entity.MesSalario;
import br.com.projeto.model.entity.ResponseModel;
import br.com.projeto.projetoCommon.valueObjects.MesSalarioVO;
import br.com.projeto.repository.MesSalarioRepository;
import br.com.projeto.service.exceptions.ObjectNotFoundException;

@Service
public class MesSalarioService {

	@Autowired
	private MesSalarioRepository repo;

	public MesSalario salvar(MesSalarioVO vo) {
		MesSalario salario = new MesSalario();

		salario.setMes(vo.getMes());
		salario.setValorSalario(vo.getValorSalario());
		salario = this.repo.save(salario);

		return salario;
	}

	public ResponseModel atualizar(MesSalarioVO vo) {

		MesSalario salario = new MesSalario();
		try {
			salario.setCodigo(vo.getCodigo());
			salario.setMes(vo.getMes());
			salario.setValorSalario(vo.getValorSalario());
			this.repo.save(salario);
			return new ResponseModel(1, "Registro atualizado com sucesso!");
		} catch (Exception e) {
			return new ResponseModel(0, e.getMessage());
		}
	}

	public MesSalarioVO buscar(Integer codigo) {
		MesSalario obj = repo.findOne(codigo);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Codigo: " + codigo);
		}

		MesSalarioVO vo = new MesSalarioVO();
		vo.setCodigo(obj.getCodigo());
		vo.setMes(obj.getMes());
		vo.setValorSalario(obj.getValorSalario());

		return vo;
	}

}