package br.com.rpg.controller;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.view.Results;
import br.com.rpg.annotation.BreadCrumb;
import br.com.rpg.component.grid.GridHeader;
import br.com.rpg.component.grid.Gridy;
import br.com.rpg.dao.SystemUserDao;
import br.com.rpg.dao.UserGroupDao;
import br.com.rpg.model.SystemUser;
import br.com.rpg.util.Criptografia;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

/**
 * @author Bruno Volpini
 */
@Resource
@BreadCrumb(menu = "menu.user", controller = "menu.administracao.usuario")
public class SystemUserController {

	private final Result result;
	private GridHeader<SystemUser> gridHeader;
	private final SystemUserDao systemUserDao;
	private final Criptografia criptografia;
	private final Validator validator;
	private final UserGroupDao userGroupDao;
	private final Environment environment;

	public SystemUserController(Result result, SystemUserDao systemUserDao, Criptografia criptografia,
			Validator validator, UserGroupDao userGroupDao, Environment environment) {
		this.result = result;
		gridHeader = new GridHeader<>(result, SystemUser.class);
		this.systemUserDao = systemUserDao;
		this.criptografia = criptografia;
		this.validator = validator;
		this.userGroupDao = userGroupDao;
		this.environment = environment;
	}

	@Get
	@BreadCrumb(menu = "", controller = "")
	public void home() {
	}

	@Get("/systemUser")
	public void index() {
		gridHeader.getHeader();
	}

	@Get
	public void add() {
		setSelects();
	}

	@Post
	public void add(SystemUser systemUser) {
		String senha = criptografia.gerarSenhaAleatoria();
		systemUser.setPassword(criptografia.criptografarSenha(senha));
		validator.validate(systemUser);
		validator.onErrorRedirectTo(this).add();
		systemUserDao.adicionar(systemUser);
		result.redirectTo(this).index();
	}

	@Get("/systemUser/edit/{id}")
	public SystemUser edit(Long id) {
		setSelects();
		return systemUserDao.buscar(id);
	}

	@Post
	public void edit(SystemUser systemUser) {
		validator.validate(systemUser);
		validator.onErrorRedirectTo(this).edit(systemUser.getId());
		systemUserDao.atualizar(systemUser);
		result.redirectTo(this).index();
	}

	@Get("/systemUser/view/{id}")
	public SystemUser view(Long id) {
		setSelects();
		return systemUserDao.buscar(id);
	}

	@Delete
	public void del(SystemUser systemUser) {
		systemUserDao.deletar(systemUser);
		result.redirectTo(this).index();
	}

	@Get
	public void gridy(String search, int page, String sortName, String sortOrder, String find, int rows) {
		Gridy g = systemUserDao.listar(search, page, sortName, sortOrder, find, rows);
		List<SystemUser> systemUsers = g.getList();
		List list = new ArrayList();
		for (SystemUser systemUser : systemUsers) {
			Map item = new HashMap();
			item.put("id", systemUser.getId());
			item.put("name", systemUser.getName());
			item.put("email", systemUser.getEmail());
			item.put("userGroup_name", systemUser.getUserGroup().getName());
			list.add(item);
		}
		Map root = new HashMap();
		root.put("list", list);
		root.put("total", g.getTotal());
		JSONArray json = new JSONArray();
		json.put(root);
		result.use(Results.http()).body(json.toString().substring(1, json.toString().length() - 1));
	}

	private void setSelects() {
		result.include("groups", userGroupDao.listarTodos());
	}
}