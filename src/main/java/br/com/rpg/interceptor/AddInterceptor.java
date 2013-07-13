package br.com.rpg.interceptor;

import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.caelum.vraptor.util.hibernate.HibernateTransactionInterceptor;
import br.com.caelum.vraptor.validator.I18nMessage;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Guilherme Felipe de C. G. da Silva
 */
@Intercepts(before = HibernateTransactionInterceptor.class)
public class AddInterceptor implements Interceptor {

	private Result result;
	private final Validator validator;
	private final Logger logger = LoggerFactory.getLogger(EditInterceptor.class);

	public AddInterceptor(Result result, Validator validator) {
		this.result = result;
		this.validator = validator;
	}

	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method, Object resourceInstance) {
		String controller = method.getResource().getType().getSimpleName();
		controller = controller.substring(0, controller.lastIndexOf("Controller"));
		controller = controller.substring(0, 1).toLowerCase() + controller.substring(1, controller.length());
		try {
			stack.next(method, resourceInstance);
		} catch (Exception e) {
			logger.warn(e.getMessage());
			if (e.getCause().getClass().equals(ConstraintViolationException.class)) {
				if (e.getCause().getCause().getClass().equals(MySQLIntegrityConstraintViolationException.class)) {
					validator.add(new I18nMessage("error", "error.duplicatedEntry"));
				} else {
					validator.add(new I18nMessage("error", e.getCause().getCause().getLocalizedMessage()));
				}
			} else {
				validator.add(new I18nMessage("error", "error.cannotAdd"));
			}
		} finally {
			if (!validator.hasErrors()) {
				result.include("success", "addSuccess");
			}
			if (!result.used()) {
				validator.onErrorRedirectTo(method.getResource()); // Gambiarra. não pode remover essa linha.
				result.redirectTo("/" + controller);
			}
		}
	}

	@Override
	public boolean accepts(ResourceMethod method) {
		if (method.containsAnnotation(Post.class)) {
			return method.getMethod().getName().equals("add");
		}
		return false;
	}
}