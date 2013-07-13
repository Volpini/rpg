package br.com.rpg.interceptor;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.rpg.annotation.BreadCrumb;

/**
 *
 * @author Guilherme Felipe de C. G. da Silva
 */
@Intercepts
public class BreadCrumbInterceptor implements Interceptor {

	private final Result result;

	public BreadCrumbInterceptor(Result result) {
		this.result = result;
	}

	public void intercept(InterceptorStack stack, ResourceMethod method, Object resourceInstance) throws InterceptionException {
		if (method.containsAnnotation(BreadCrumb.class)) {
			result.include("breadCrumb", method.getMethod().getAnnotation(BreadCrumb.class));
		} else {
			result.include("breadCrumb", resourceInstance.getClass().getAnnotation(BreadCrumb.class));
		}
		stack.next(method, resourceInstance);
	}

	public boolean accepts(ResourceMethod method) {
		return true;
	}
}