package br.com.rpg.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.rpg.model.Controller;
import org.hibernate.Session;

/**
 *
 * @author Guilherme Felipe de C. G. da Silva
 */
@Component
public class ControllerDao extends Dao<Controller> {

	public ControllerDao(Session session) {
		super(session, Controller.class);
	}
}