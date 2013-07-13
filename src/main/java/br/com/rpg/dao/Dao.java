package br.com.rpg.dao;

import br.com.rpg.component.grid.GridColumn;
import br.com.rpg.component.grid.Gridy;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 * Dao Genérico
 *
 * @author Guilherme Felipe C. G. da Silva
 * @author Bruno Volpini
 *
 */
public abstract class Dao<T> {

	private Session session;
	private Class<T> perClass;
	private Set<String> alias = new HashSet<String>();
	private Criterion criterion;

	public Dao(Session session, Class<T> perClass) {
		this.session = session;
		this.perClass = perClass;
	}

	public void adicionar(T t) {
		session.save(t);
		session.flush();
		session.clear();
	}

	public void deletar(T t) {
		session.delete(t);
		session.flush();
		session.clear();
	}

	public List listarTodos() {
		Criteria criteria = session.createCriteria(perClass);
		return criteria.list();
	}

	public T buscar(Long id) {
		return (T) session.get(perClass, id);
	}

	public void atualizar(T t) {
		session.update(t);
		session.flush();
		session.clear();
	}

	public Gridy listar(String search, int page, String sortName, String sortOrder, String find, int rows) {
		Criteria criteria = session.createCriteria(perClass)
				.setMaxResults(rows)
				.setFirstResult(rows * (page - 1));
		Criteria total = session.createCriteria(perClass);

		Conjunction conjunction = Restrictions.conjunction();
		find = alterSeparator(find);
		criteria = createAlias(criteria, total, find);
		if (find != null && search != null) {
			add(conjunction, find, search);
		} else if (search != null) {
			Disjunction or = Restrictions.disjunction();
			for (Field field : perClass.getDeclaredFields()) {
				if (field.getAnnotation(GridColumn.class) != null) {
					String column = alterSeparator(field.getAnnotation(GridColumn.class).value());
					criteria = createAlias(criteria, total, column);
					add(or, column, search);
				}
			}
			conjunction.add(or);
		}
		sortName = alterSeparator(sortName);
		criteria = createAlias(criteria, total, sortName);
		if (sortName != null && sortOrder != null && !sortName.equals("options")) {
			if (sortOrder.equals("asc")) {
				criteria.addOrder(Order.asc(sortName));
			} else {
				criteria.addOrder(Order.desc(sortName));
			}
		} else {
			criteria.addOrder(Order.desc("id"));
		}
		if (criterion != null) {
			conjunction.add(criterion);
		}
		criteria.add(conjunction);
		total.add(conjunction);
		List list = criteria.list();
		return new Gridy(list, (Long) total.setProjection(Projections.rowCount()).uniqueResult());
	}

	/**
	 * Cria os alias das tabelas com base no valor do campo. </br> Este metodo
	 * serve para fazer o join das tabelas relacionadas com base no nome do
	 * campo de busca. </br> Exemplo: sortName = "subcategoria.nome" - o alias é
	 * criado para a tabela subcategoria.
	 *
	 * @param criteria
	 * @param field
	 * @return
	 */
	private Criteria createAlias(Criteria criteria, Criteria total, String field) {
		if (field != null) {
			if (field.contains(".")) {
				String values[];
				values = field.split("\\.");
				for (int i = 0; i < (values.length - 1); i++) {
					if (!alias.contains(values[i])) {
						alias.add(values[i]);
						criteria.createAlias(values[i], values[i], Criteria.LEFT_JOIN);
						total.createAlias(values[i], values[i], Criteria.LEFT_JOIN);
					}
				}
			}
		}
		return criteria;
	}

	private String alterSeparator(String field) {
		String replaced = null;
		if (field != null) {
			replaced = field.replaceAll("_", "\\.");
		}
		return replaced;
	}

	private void add(Junction conjunction, String find, String search) {
		try {
			if (getType(find).equals(Integer.class)) {
				try {
					conjunction.add(Restrictions.like(find, Integer.parseInt(search)));
				} catch (NumberFormatException nfe) {
					conjunction.add(Restrictions.like(find, 123333));
				}
			} else if (getType(find).equals(BigDecimal.class)) {
				try {
					conjunction.add(Restrictions.like(find, new BigDecimal(search)));
				} catch (NumberFormatException nfe) {
					conjunction.add(Restrictions.like(find, new BigDecimal(123338)));
				}
			} else if (!getType(find).isEnum()) {
				conjunction.add(Restrictions.like(find, search, MatchMode.ANYWHERE));
			}
		} catch (NoSuchFieldException | SecurityException ex) {
			Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	protected void addCriterion(Criterion criterion) {
		this.criterion = criterion;
	}

	private Class getType(String find) throws NoSuchFieldException {
		String[] attrs = find.split("\\.");
		Class field = perClass;
		for (String attr : attrs) {
			field = field.getDeclaredField(attr).getType();
		}
		return field;
	}
}