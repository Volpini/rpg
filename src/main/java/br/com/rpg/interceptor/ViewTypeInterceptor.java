package br.com.rpg.interceptor;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.rpg.enumeration.ViewType;

/**
 * @author Bruno Gonçales Lima
 * @autor Guilherme Felipe de C. G. da Silva
 */
@Intercepts
public class ViewTypeInterceptor implements Interceptor {
	
	private final Result result;
	
	public ViewTypeInterceptor(Result result) {
		this.result = result;
	}
	
	public void intercept(InterceptorStack stack, ResourceMethod method,
			Object resourceInstance) throws InterceptionException {
		if (method.getMethod().getName().equals(ViewType.ADD.getNome())) {
			result.include("viewType", ViewType.ADD);
		} else if (method.getMethod().getName().equals(ViewType.VIEW.getNome())) {
			result.include("viewType", ViewType.VIEW);
			result.include("disabled", "disabled");
		} else if (method.getMethod().getName().equals(ViewType.EDIT.getNome())) {
			result.include("viewType", ViewType.EDIT);
		} else if (method.getMethod().getName().equals(ViewType.GRID.getNome())) {
			result.include("viewType", ViewType.GRID);
		}
		String controller = resourceInstance.getClass().getSimpleName().replaceFirst("Controller", "");
		controller = controller.substring(0, 1).toLowerCase() + controller.substring(1);
		result.include("controller", controller);
		stack.next(method, resourceInstance);
	}
	
	public boolean accepts(ResourceMethod method) {
		return true;
	}
}
