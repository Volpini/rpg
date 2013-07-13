package br.com.rpg.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.rpg.dao.SystemUserDao;
import br.com.rpg.dao.UserGroupDao;
import br.com.rpg.model.SystemUser;
import br.com.rpg.session.UserSession;
import br.com.rpg.util.Criptografia;

/**
 *
 * @author Guilherme Felipe de C. G. da Silva
 */
@Resource
public class ProfileController {

	private final Result result;
	private final SystemUserDao systemUserDao;
	private final UserSession userSession;
	private final Validator validator;
	private final Criptografia criptografia;
	private final UserGroupDao userGroupDao;

	public ProfileController(Result result, SystemUserDao systemUserDao, UserSession userSession, Validator validator, Criptografia criptografia,
			UserGroupDao userGroupDao) {
		this.result = result;
		this.systemUserDao = systemUserDao;
		this.userSession = userSession;
		this.validator = validator;
		this.criptografia = criptografia;
		this.userGroupDao = userGroupDao;
	}

	@Get("/profile")
	public SystemUser index() {
		return systemUserDao.buscar(userSession.getSystemUser().getId());
	}

	@Get("/profile/changePassword")
	public SystemUser changePassword() {
		return systemUserDao.buscar(userSession.getSystemUser().getId());
	}

	@Post
	public void edit(SystemUser systemUser) {
		validator.validate(systemUser);
		validator.onErrorRedirectTo(this).index();
		systemUserDao.atualizar(systemUser);
		systemUser.setUserGroup(userGroupDao.buscar(systemUser.getUserGroup().getId()));
		userSession.setSystemUser(systemUser);
		result.redirectTo(this).index();
	}

	@Post
	public void changePassword(String actualPassword, String newPassword, String repeatPassword) {
		if (actualPassword != null && newPassword != null && repeatPassword != null) {
			if (!criptografia.criptografarSenha(actualPassword).equals(userSession.getSystemUser().getPassword())) {
				validator.add(new ValidationMessage("actual password is wrong", "error"));
			}
			if (!newPassword.equals(repeatPassword)) {
				validator.add(new ValidationMessage("password is diferent", "error"));
			}
		} else {
			if (actualPassword == null) {
				validator.add(new I18nMessage("actualPassword", "error.notNull", "", ""));
			}
			if (newPassword == null) {
				validator.add(new I18nMessage("newPassword", "error.notNull", "", ""));
			}
			if (repeatPassword == null) {
				validator.add(new I18nMessage("repeatNewPassword", "error.notNull", "", ""));
			}
		}
		validator.onErrorRedirectTo(this).changePassword();
		SystemUser systemUser = userSession.getSystemUser();
		systemUser.setPassword(criptografia.criptografarSenha(newPassword));
		systemUserDao.atualizar(systemUser);
		result.redirectTo(this).index();
	}
}