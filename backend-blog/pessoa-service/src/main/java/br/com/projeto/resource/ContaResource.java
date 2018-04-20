package br.com.projeto.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.model.entity.ContaModel;
import br.com.projeto.model.entity.ResponseModel;
import br.com.projeto.repository.ContaRepository;

@CrossOrigin(origins = "http://localhost:8000")
@RestController
@RequestMapping("/service")
public class ContaResource {

	@Autowired
	private ContaRepository contaRepository;

	/**
	 * SALVAR UM NOVO REGISTRO
	 * 
	 * @param conta
	 * @return
	 */
	@RequestMapping(value = "/conta", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel salvar(@RequestBody ContaModel conta) {

		try {

			this.contaRepository.save(conta);

			return new ResponseModel(1, "Registro salvo com sucesso!");

		} catch (Exception e) {

			return new ResponseModel(0, e.getMessage());
		}
	}

	/**
	 * ATUALIZAR O REGISTRO DE UMA PESSOA
	 * 
	 * @param conta
	 * @return
	 */
	@RequestMapping(value = "/conta", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel atualizar(@RequestBody ContaModel conta) {

		try {

			this.contaRepository.save(conta);

			return new ResponseModel(1, "Registro atualizado com sucesso!");

		} catch (Exception e) {

			return new ResponseModel(0, e.getMessage());
		}
	}

	/**
	 * CONSULTAR TODAS AS PESSOAS
	 * 
	 * @return
	 */
	@RequestMapping(value = "/conta", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<ContaModel> consultar() {

		return this.contaRepository.findAll();
	}

	/**
	 * BUSCAR UMA PESSOA PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	@RequestMapping(value = "/conta/{codigo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ContaModel buscar(@PathVariable("codigo") Integer codigo) {

		return this.contaRepository.findOne(codigo);
	}

	/***
	 * EXCLUIR UM REGISTRO PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	@RequestMapping(value = "/conta/{codigo}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel excluir(@PathVariable("codigo") Integer codigo) {

		ContaModel contaModel = contaRepository.findOne(codigo);

		try {

			contaRepository.delete(contaModel);

			return new ResponseModel(1, "Registro excluido com sucesso!");

		} catch (Exception e) {
			return new ResponseModel(0, e.getMessage());
		}
	}

}
