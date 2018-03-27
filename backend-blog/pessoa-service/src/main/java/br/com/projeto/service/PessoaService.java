package br.com.projeto.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.model.PessoaModel;
import br.com.projeto.model.ResponseModel;
import br.com.projeto.projetoCommon.valueobjects.Mensagem;
import br.com.projeto.repository.PessoaRepository;
import br.com.projeto.service.impl.emailImpl;

@CrossOrigin(origins = "http://localhost:8000")
@RestController
@RequestMapping("/service")
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	/**
	 * SALVAR UM NOVO REGISTRO
	 * 
	 * @param pessoa
	 * @return
	 */
	@RequestMapping(value = "/pessoa", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel salvar(@RequestBody PessoaModel pessoa) {

		try {

			this.pessoaRepository.save(pessoa);

			return new ResponseModel(1, "Registro salvo com sucesso!");

		} catch (Exception e) {

			return new ResponseModel(0, e.getMessage());
		}
	}

	/**
	 * ATUALIZAR O REGISTRO DE UMA PESSOA
	 * 
	 * @param pessoa
	 * @return
	 */
	@RequestMapping(value = "/pessoa", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel atualizar(@RequestBody PessoaModel pessoa) {

		try {

			this.pessoaRepository.save(pessoa);

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
	@RequestMapping(value = "/pessoa", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<PessoaModel> consultar() {

		return this.pessoaRepository.findAll();
	}

	/**
	 * BUSCAR UMA PESSOA PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	@RequestMapping(value = "/pessoa/{codigo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody PessoaModel buscar(@PathVariable("codigo") Integer codigo) {

		return this.pessoaRepository.findOne(codigo);
	}

	/***
	 * EXCLUIR UM REGISTRO PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	@RequestMapping(value = "/pessoa/{codigo}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel excluir(@PathVariable("codigo") Integer codigo) {

		PessoaModel pessoaModel = pessoaRepository.findOne(codigo);

		try {

			pessoaRepository.delete(pessoaModel);

			return new ResponseModel(1, "Registro excluido com sucesso!");

		} catch (Exception e) {
			return new ResponseModel(0, e.getMessage());
		}
	}

	/***
	 * IMPRESSAO DE RELATORIO
	 * 
	 * @return .pdf
	 */
	@RequestMapping(value = "/pessoa/download", method = RequestMethod.GET)
	public void getFileUsuario(HttpServletRequest request, HttpServletResponse response) {

		ServletContext context = request.getServletContext();
		String fileToDownload = "C:\\Users\\sironazevedo\\Documents\\CV - SIRON AZEVEDO SANTOS DA SILVA.pdf";
		File file = new File(fileToDownload);
		try {
			InputStream inputStream = new FileInputStream(file);
			response.setContentType(context.getMimeType(fileToDownload));
			response.setHeader("Content-Disposition", String.format("attachent: filename=\''%s\''", fileToDownload));
			response.setHeader("Content-Length", String.valueOf(file.length()));
			IOUtils.copy(inputStream, response.getOutputStream());
			response.flushBuffer();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/pessoa/enviarEmail/{type}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel getEnviarEmail(@RequestBody Mensagem mensagem,
			@PathVariable("type") String type) {

		emailImpl email = new emailImpl();

		try {
			email.sendEmail(mensagem);
			return new ResponseModel(1, "Email enviado com sucesso!");

		} catch (Exception e) {
			return new ResponseModel(0, e.getMessage());
		}

	}

}