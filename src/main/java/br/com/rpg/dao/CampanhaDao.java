package br.com.rpg.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.rpg.component.grid.GridColumn;
import br.com.rpg.component.grid.Gridy;
import br.com.rpg.model.Campanha;
import br.com.rpg.model.SystemUser;
import br.com.rpg.model.view.CampanhasMestrePersonagemView;
import br.com.rpg.session.UserSession;
import java.lang.reflect.Field;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 * @author Erick Lima
 */
@Component
public class CampanhaDao extends Dao<Campanha> {

    private UserSession userSession;

    public CampanhaDao(Session session, UserSession userSession) {
        super(session, Campanha.class);
        this.userSession = userSession;
    }

    @Override
    public Gridy listar(String search, int page, String sortName, String sortOrder, String find, int rows) {
        SystemUser u = userSession.getSystemUser();
        addCriterion(Restrictions.eq("mestre", u));
        return super.listar(search, page, sortName, sortOrder, find, rows);
    }

    public Gridy listarCampanhasView(String search, int page, String sortName, String sortOrder, String find, int rows, Long userId) {
        Class perClass = CampanhasMestrePersonagemView.class;
        Criteria criteria = session.createCriteria(perClass).setMaxResults(rows).setFirstResult(rows * (page - 1));
        Criteria total = session.createCriteria(perClass);

        Conjunction conjunction = Restrictions.conjunction();
        find = alterSeparator(find);
        criteria = createAlias(criteria, find);
        if (find != null && search != null) {
            add(conjunction, find, search);
        } else if (search != null) {
            Disjunction or = Restrictions.disjunction();
            for (Field field : perClass.getDeclaredFields()) {
                if (field.getAnnotation(GridColumn.class) != null) {
                    String column = alterSeparator(field.getAnnotation(GridColumn.class).value());
                    criteria = createAlias(criteria, column);
                    add(or, column, search);
                    
                }
            }
            
            conjunction.add(or);
        }
        //By mestre and jogador
        
        Disjunction orr = Restrictions.disjunction();
                orr.add(Restrictions.eq("mestre", userId))
                .add(Restrictions.eq("jogador", userId));
        conjunction.add(orr);
        sortName = alterSeparator(sortName);
        criteria = createAlias(criteria, sortName);
        if (sortName != null && sortOrder != null && !sortName.equals("options")) {
            if (sortOrder.equals("asc")) {
                criteria.addOrder(Order.asc(sortName));
            } else {
                criteria.addOrder(Order.desc(sortName));
            }
        } else {
            criteria.addOrder(Order.desc("id"));
        }
        
        criteria.add(conjunction);

        
        total.add(conjunction);
        List list = criteria.list();
        return new Gridy(list, (Long) total.setProjection(Projections.rowCount()).uniqueResult());
     
    }



}
