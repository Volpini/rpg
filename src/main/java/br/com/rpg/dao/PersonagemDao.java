package br.com.rpg.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.rpg.model.Personagem;
import br.com.rpg.model.PersonagemItem;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 * @author Bruno Volpini
 */
@Component
public class PersonagemDao extends Dao<Personagem> {

	public PersonagemDao(Session session) {
		super(session, Personagem.class);
	}

	@Override
	public void adicionar(Personagem personagem) {
		removeAtributosNull(personagem);
		setItensPersonagem(personagem);
		super.adicionar(personagem);
	}

	@Override
	public void atualizar(Personagem personagem) {
		removeAtributosNull(personagem);
		setItensPersonagem(personagem);
		super.atualizar(personagem);
	}
	
	public void setItensPersonagem(Personagem personagem){
		if(personagem.getItensPersonagem() != null){
			for (PersonagemItem personagemItem : personagem.getItensPersonagem()) {
				personagemItem.setPersonagem(personagem);
			}
		}
	}
	
	private void removeAtributosNull(Personagem personagem){
		List<PersonagemItem> itensPersonagem = personagem.getItensPersonagem();
		if(itensPersonagem != null){
			List<PersonagemItem> itensPersonagemFinal = new ArrayList();
			for (PersonagemItem personagemItem : itensPersonagem) {
				if(personagemItem.getItem().getId() != null){
					itensPersonagemFinal.add(personagemItem);
				}
			}
			personagem.setItensPersonagem(itensPersonagemFinal);
		}
	}
}