package br.com.rpg.component.config;

import br.com.caelum.vraptor.interceptor.multipart.DefaultMultipartConfig;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

/**
 * @author Guilherme Felipe de C. G. da Silva
 */
@Component
@ApplicationScoped
public class CustomMultipartConfig extends DefaultMultipartConfig {

	@Override
	public long getSizeLimit() {
		return 100 * 1024 * 1024; // 100MB
	}
}