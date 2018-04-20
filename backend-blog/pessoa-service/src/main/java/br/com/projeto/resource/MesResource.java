package br.com.projeto.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.model.entity.Mes;
import br.com.projeto.repository.MesRepository;

@CrossOrigin(origins = "http://localhost:8000")
@RestController
@RequestMapping("/service")
public class MesResource {

	@Autowired
	private MesRepository mesRepository;

	/**
	 * CONSULTAR TODOS OS MESES
	 * 
	 * @return
	 */
	@RequestMapping(value = "/meses", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Mes> consultar() {

		return this.mesRepository.findAll();
	}

	/**
	 * BUSCAR UMA MES PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	@RequestMapping(value = "/meses/{codigo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Mes buscar(@PathVariable("codigo") Integer codigo) {

		return this.mesRepository.findOne(codigo);
	}

}
