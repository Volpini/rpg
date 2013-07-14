package br.com.rpg.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.rpg.model.Item;
import org.hibernate.Session;

/**
 * @author Bruno Volpini
 */
@Component
public class ItemDao extends Dao<Item> {

	public ItemDao(Session session) {
		super(session, Item.class);
	}
}