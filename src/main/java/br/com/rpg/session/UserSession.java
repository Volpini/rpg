package br.com.rpg.session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.rpg.component.config.Authenticator;
import br.com.rpg.model.Permission;
import br.com.rpg.model.SystemUser;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PreDestroy;

/**
 * @author Guilherme Felipe de C. G. da Silva
 */
@SessionScoped
@Component
public class UserSession implements Serializable {

	private SystemUser systemUser;
	private List<Permission> permissions;
	private final Authenticator authenticator;
	private boolean dropped = false;

	public UserSession(Authenticator authenticator) {
		this.authenticator = authenticator;
	}

	public void setSystemUser(SystemUser systemUser) {
		if (systemUser != null) {
			permissions = systemUser.getUserGroup().getPermissions();
			// Forçando o hibernate para executar o select do campo lazy
			if (!permissions.isEmpty()) {
				permissions.get(0).getId();
			}
		} else {
			permissions = null;
		}
		this.systemUser = systemUser;
	}

	public SystemUser getSystemUser() {
		return systemUser;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public boolean isDropped() {
		return dropped;
	}

	public void setDropped(boolean dropped) {
		this.dropped = dropped;
	}

	public boolean isLogado() {
		return getSystemUser() != null;
	}

	public void logout() {
		authenticator.logout(this);
	}

	@PreDestroy
	public void destroy() {
		authenticator.logout(this);
	}
}