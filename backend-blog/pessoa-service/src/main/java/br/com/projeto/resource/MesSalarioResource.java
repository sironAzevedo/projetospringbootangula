package br.com.projeto.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.model.entity.MesSalario;
import br.com.projeto.model.entity.ResponseModel;
import br.com.projeto.projetoCommon.valueObjects.MesSalarioVO;
import br.com.projeto.repository.MesSalarioRepository;
import br.com.projeto.service.MesSalarioService;

@CrossOrigin(origins = "http://localhost:8000")
@RestController
@RequestMapping("/service")
public class MesSalarioResource {

	public static final Logger logger = LoggerFactory.getLogger(MesSalarioResource.class);

	@Autowired
	private MesSalarioRepository mesSalarioRepository;

	@Autowired
	private MesSalarioService service;

	/**
	 * SALVAR UM NOVO REGISTRO
	 * 
	 * @param pessoa
	 * @return
	 */
	@RequestMapping(value = "/salario", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody MesSalarioVO vo) {
		logger.info("Salvar Mes Salario", vo.toString());

		MesSalario salario = service.salvar(vo);
		return new ResponseEntity<MesSalario>(salario, HttpStatus.OK);
	}

	/**
	 * ATUALIZAR O REGISTRO DE UMA PESSOA
	 * 
	 * @param pessoa
	 * @return
	 */
	@RequestMapping(value = "/salario", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity<?> atualizar(@RequestBody MesSalarioVO vo) {
		logger.info("Atualizar Mes Salario", vo.toString());

		ResponseModel obj = service.atualizar(vo);
		return ResponseEntity.ok().body(obj);
	}

	/**
	 * CONSULTAR TODAS AS PESSOAS
	 * 
	 * @return
	 */
	@RequestMapping(value = "/salario", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<MesSalario> consultar() {
		logger.info("consultar Mes Salario");

		return this.mesSalarioRepository.findAll();
	}

	/**
	 * BUSCAR UMA PESSOA PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	@RequestMapping(value = "/salario/{codigo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> buscar(@PathVariable("codigo") Integer codigo) {
		logger.info("consultar Mes Salario por codigo", codigo);
		MesSalarioVO obj = service.buscar(codigo);
		return ResponseEntity.ok().body(obj);
	}

	// Estou fazendo esse metodo
	@RequestMapping(value = "/salario/mes/{codigoMes}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody MesSalario buscarMesSalarioPorMes(@PathVariable("codigo") Integer codigoMes) {

		return this.mesSalarioRepository.findOne(codigoMes);
	}

	/***
	 * EXCLUIR UM REGISTRO PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	@RequestMapping(value = "/salario/{codigo}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel excluir(@PathVariable("codigo") Integer codigo) {

		MesSalario mesSalario = mesSalarioRepository.findOne(codigo);

		try {

			mesSalarioRepository.delete(mesSalario);

			return new ResponseModel(1, "Registro excluido com sucesso!");

		} catch (Exception e) {
			return new ResponseModel(0, e.getMessage());
		}
	}
}
