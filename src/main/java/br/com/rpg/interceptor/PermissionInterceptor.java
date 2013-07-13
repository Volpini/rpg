package br.com.rpg.interceptor;

import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.core.Localization;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.caelum.vraptor.util.hibernate.HibernateTransactionInterceptor;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.view.Results;
import br.com.rpg.controller.LoginController;
import br.com.rpg.model.Permission;
import br.com.rpg.session.UserSession;
import java.util.ArrayList;

/**
 * @author Guilherme Felipe de C. G .da Silva
 */
@Intercepts(before = HibernateTransactionInterceptor.class)
public class PermissionInterceptor implements Interceptor {

	private UserSession userSession;
	private Result result;
	private final Validator validator;
	private final Localization localization;

	public PermissionInterceptor(UserSession userSession, Result result, Validator validator, Localization localization) {
		this.userSession = userSession;
		this.result = result;
		this.validator = validator;
		this.localization = localization;
	}

	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method, Object resourceInstance) {
		boolean permitido = false;
		String controller = method.getResource().getType().getName();
		controller = controller.substring(controller.lastIndexOf(".") + 1);
		if (userSession.isLogado()) {
			if (!method.getMethod().getName().equals("home")
					&& !method.getResource().getType().getSimpleName().contains("Profile")
					&& !method.getResource().getType().getSimpleName().contains("Updates")) {
				for (Permission permission : userSession.getPermissions()) {
					if (permission.getCont().getName().equals(controller)) {
						switch (method.getMethod().getName()) {
							case "add":
								if (permission.ispAdd()) {
									permitido = true;
								}
								break;
							case "edit":
								if (permission.ispEdit()) {
									permitido = true;
								}
								break;
							case "view":
								if (permission.ispView()) {
									permitido = true;
								}
								break;
							case "del":
								if (permission.ispDel()) {
									permitido = true;
								}
								break;
							case "index":
								if (permission.ispGrid()) {
									permitido = true;
								}
								break;
							default:
								permitido = true;
								break;
						}
					}
				}
			} else {
				permitido = true;
			}
			if (permitido) {
				stack.next(method, resourceInstance);
			} else {
				result.use(Results.page()).forwardTo("/WEB-INF/jsp/erro/acessoNegado.jsp");
			}
		} else {
			if (userSession.isDropped()) {
				ArrayList errors = new ArrayList();
				I18nMessage i18nMessage = new I18nMessage("dropped", "dropped");
				i18nMessage.setBundle(localization.getBundle());
				errors.add(i18nMessage);
				result.include("errors", errors);
			}
			result.redirectTo(LoginController.class).index();
		}
	}

	@Override
	public boolean accepts(ResourceMethod method) {
		if (method.getResource().getType().equals(LoginController.class)
				|| method.getResource().getType().getSimpleName().contains("Ajax")) {
			return false;
		}
		return true;
	}
}