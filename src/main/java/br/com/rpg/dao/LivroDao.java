package br.com.rpg.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.rpg.model.Livro;
import org.hibernate.Session;

/**
 * @author Bruno Volpini
 */
@Component
public class LivroDao extends Dao<Livro> {

	public LivroDao(Session session) {
		super(session, Livro.class);
	}
}