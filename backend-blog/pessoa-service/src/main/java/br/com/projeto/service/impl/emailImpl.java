package br.com.projeto.service.impl;

import br.com.projeto.projetoCommon.valueObjects.Mensagem;
import br.com.projeto.service.email.MailConfig;

/**
 *
 * @author siron
 */
@SuppressWarnings("unused")
public class emailImpl {
	public void sendEmail(Mensagem mensagem) {
		MailConfig mailConfig = new MailConfig();
		try {
			mailConfig.sendEmail(mensagem);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}