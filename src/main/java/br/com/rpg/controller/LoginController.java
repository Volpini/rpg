package br.com.rpg.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.rpg.component.config.Authenticator;
import br.com.rpg.dao.SystemUserDao;
import br.com.rpg.model.SystemUser;
import br.com.rpg.service.LoginService;
import br.com.rpg.session.UserSession;
import br.com.rpg.util.Criptografia;

/**
 * @author Guilherme Felipe de C. G. da Silva
 */
@Resource
public class LoginController {

	private final Result result;
	private final UserSession userSession;
	private final LoginService loginService;
	private final Validator validator;
	private final Criptografia criptografia;
	private final Environment environment;
	private final SystemUserDao systemUserDao;
	private final Authenticator authenticator;

	public LoginController(Result result, UserSession userSession, LoginService loginService, Validator validator,
			Criptografia criptografia, Environment environment, SystemUserDao systemUserDao,
			Authenticator authenticator) {
		this.result = result;
		this.userSession = userSession;
		this.loginService = loginService;
		this.validator = validator;
		this.criptografia = criptografia;
		this.environment = environment;
		this.systemUserDao = systemUserDao;
		this.authenticator = authenticator;
	}

	@Get("/")
	public void index() {
	}

	@Post("/login")
	public void login(SystemUser systemUser) {
		systemUser.setName("User");
		validator.validate(systemUser);
		validator.onErrorUsePageOf(this).index();
		if (loginService.existente(systemUser)) {
			UserSession colocaNaSessao = loginService.colocaNaSessao(systemUser);
			authenticator.login(colocaNaSessao);
			this.result.redirectTo(SystemUserController.class).home();
		} else {
			validator.add(new I18nMessage("login", "error.invalidCredentials"));
			validator.onErrorUsePageOf(this).index();
		}
	}

	@Get("/logout")
	public void logout() {
		authenticator.logout(userSession);
		result.redirectTo(this).index();
	}

	@Get("/forgotPassword")
	public void forgotPassword() {
	}

	@Get("/confirmPassword/{antigaSenha}/{novaSenha}/{email}")
	public void confirmPassword(String antigaSenha, String novaSenha, String email) {
		if (!loginService.changePassword(antigaSenha, novaSenha, email)) {
			validator.add(new I18nMessage("email", "error.expiredEmail"));
		}
		validator.onErrorRedirectTo(this).index();
		result.redirectTo(this).index();
	}
}