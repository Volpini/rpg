package br.com.rpg.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.rpg.model.Campanha;
import br.com.rpg.model.Personagem;
import br.com.rpg.model.PersonagemHabilidade;
import br.com.rpg.model.PersonagemItem;
import br.com.rpg.model.PersonagemPericia;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;

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
        coupleUpPersonagem(personagem);
        super.adicionar(personagem);

    }

    @Override
    public void atualizar(Personagem personagem) {
        removeAtributosNull(personagem);
        coupleUpPersonagem(personagem);
        super.atualizar(personagem);
    }

    public void coupleUpPersonagem(Personagem p) {
        setItensPersonagem(p);
        setPericiasPersonagem(p);
        setHabilidadesPersonagem(p);
    }

    public void setItensPersonagem(Personagem personagem) {
        if (personagem.getItensPersonagem() != null) {
            for (PersonagemItem personagemItem : personagem.getItensPersonagem()) {
                personagemItem.setPersonagem(personagem);
            }
        }
    }

    public void setPericiasPersonagem(Personagem personagem) {
        if (personagem.getPericias() != null) {
            for (PersonagemPericia personagemPericia : personagem.getPericias()) {
                personagemPericia.setPersonagem(personagem);
            }
        }
    }

    public void setHabilidadesPersonagem(Personagem personagem) {
        if (personagem.getHabilidades() != null) {
            for (PersonagemHabilidade personagemHabilidade : personagem.getHabilidades()) {
                personagemHabilidade.setPersonagem(personagem);
            }
        }
    }

    private void removeAtributosNull(Personagem personagem) {
        List<PersonagemItem> itensPersonagem = personagem.getItensPersonagem();
        if (itensPersonagem != null) {
            List<PersonagemItem> itensPersonagemFinal = new ArrayList();
            for (PersonagemItem personagemItem : itensPersonagem) {
                if (personagemItem.getItem().getId() != null) {
                    itensPersonagemFinal.add(personagemItem);
                }
            }
            personagem.setItensPersonagem(itensPersonagemFinal);
        }
    }

    public List<Personagem> listarTodosByCampanha(Long idCampanha) throws EntityNotFoundException {
        Campanha c = (Campanha) session.get(Campanha.class, idCampanha);
        if (c == null) {
            throw new EntityNotFoundException("Campanha não encontrada");
        }
        return c.getPersonagens();
    }

    public List<Personagem> listarTodosNaoMestre(Long idMestre) throws EntityNotFoundException {
        Criteria criteria = session.createCriteria(Personagem.class);
        criteria.add(Restrictions.ne("systemUser.id",idMestre));
                
        return criteria.list();
    }
}
