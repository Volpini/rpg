package br.com.rpg.service;

import br.com.caelum.vraptor.ioc.Component;
import br.com.rpg.dao.SystemUserDao;
import br.com.rpg.model.SystemUser;
import br.com.rpg.session.UserSession;
import br.com.rpg.util.Criptografia;

/**
 * @author Guilherme Felipe de C. G. da Silva
 */
@Component
public class LoginService {

	private final SystemUserDao systemUserDao;
	private final UserSession userSession;
	private Criptografia criptografia;

	public LoginService(SystemUserDao systemUserDao, UserSession userSession, Criptografia criptografia) {
		this.systemUserDao = systemUserDao;
		this.userSession = userSession;
		this.criptografia = criptografia;
	}

	public boolean existente(SystemUser systemUser) {
		SystemUser u = systemUserDao.buscarPorEmail(systemUser.getEmail());
		Boolean existe = false;
		if (u != null) {
			if (criptografia.criptografarSenha(systemUser.getPassword()).equals(u.getPassword())) {
				existe = true;
			}
		}
		return existe;
	}

	public UserSession colocaNaSessao(SystemUser u) {
		userSession.setSystemUser(systemUserDao.buscarPorEmail(u.getEmail()));
		return userSession;
	}

	public void logout() {
		userSession.logout();
	}

	public boolean changePassword(String antigaSenha, String novaSenha, String email) {
		SystemUser systemUser = systemUserDao.buscarPorEmail(email);
		if (systemUser.getPassword().equals(antigaSenha)) {
			systemUser.setPassword(novaSenha);
			systemUserDao.atualizar(systemUser);
			return true;
		}
		return false;
	}
}
