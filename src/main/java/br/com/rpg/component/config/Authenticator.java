package br.com.rpg.component.config;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.rpg.session.UserSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Guilherme Felipe de C. G. da Silva
 */
@Component
@ApplicationScoped
public class Authenticator implements Serializable {

	private List<UserSession> userSessions = new ArrayList();

	public void login(UserSession userSession) {
		userSession.setDropped(false);
		logoutOldSession(userSession);
		userSessions.add(userSession);
	}

	public void logout(UserSession userSession) {
		userSession.setSystemUser(null);
		userSessions.remove(userSession);
	}

	private void logoutOldSession(UserSession userSession) {
		UserSession u = find(userSession);
		if (u != null) {
			u.logout();
			u.setDropped(true);
			userSessions.remove(u);
		}
	}

	private UserSession find(UserSession userSession) {
		for (UserSession u : userSessions) {
			if (u.getSystemUser() != null) {
				if (!userSession.equals(u)) {
					if (u.getSystemUser().getId().equals(userSession.getSystemUser().getId())) {
						return u;
					}
				}
			}
		}
		return null;
	}
}