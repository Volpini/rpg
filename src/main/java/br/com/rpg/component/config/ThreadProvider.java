package br.com.rpg.component.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

/**
 * @author Guilherme Felipe de C. G. da Silva
 */

@Component
@ApplicationScoped
public class ThreadProvider implements ComponentFactory<ExecutorService> {
	private ExecutorService pool;

	@PostConstruct
	public void initialize() {
		this.pool = Executors.newFixedThreadPool(10);
	}

	public ExecutorService getInstance() {
		return this.pool;
	}

	@PreDestroy
	public void shutdown() {
		this.pool.shutdown();
	}
}