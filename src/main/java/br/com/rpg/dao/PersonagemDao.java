package br.com.rpg.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.rpg.model.Personagem;
import org.hibernate.Session;

/**
 * @author Bruno Volpini
 */
@Component
public class PersonagemDao extends Dao<Personagem> {

	public PersonagemDao(Session session) {
		super(session, Personagem.class);
	}
}