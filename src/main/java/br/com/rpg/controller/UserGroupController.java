package br.com.rpg.controller;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.rpg.annotation.BreadCrumb;
import br.com.rpg.component.grid.GridHeader;
import br.com.rpg.component.grid.Gridy;
import br.com.rpg.dao.ControllerGroupDao;
import br.com.rpg.dao.UserGroupDao;
import br.com.rpg.model.UserGroup;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

/**
 * @author Guilherme Felipe de C. G. da Silva
 * @author Bruno Volpini
 */
@Resource
@BreadCrumb(menu = "menu.user", controller = "menu.administracao.grupo")
public class UserGroupController {

	private final Result result;
	private final Validator validator;
	private final UserGroupDao userGroupDao;
	private GridHeader<UserGroup> gridHeader;
	private ControllerGroupDao controllerGroupDao;

	public UserGroupController(Result result, Validator validator, UserGroupDao userGroupDao,
			ControllerGroupDao controllerGroupDao) {
		this.result = result;
		this.validator = validator;
		this.userGroupDao = userGroupDao;
		this.controllerGroupDao = controllerGroupDao;
		gridHeader = new GridHeader<>(result, UserGroup.class);
	}

	@Get("/userGroup")
	public void index() {
		gridHeader.getHeader();
	}

	@Get
	public void add() {
		setSelects();
	}

	@Post
	public void add(UserGroup userGroup) {
		validator.validate(userGroup);
		validator.onErrorRedirectTo(this).add();
		userGroupDao.adicionar(userGroup);
		result.redirectTo(this).index();
	}

	@Get("/userGroup/edit/{id}")
	public UserGroup edit(Long id) {
		setSelects();
		return userGroupDao.buscar(id);
	}

	@Post
	public void edit(UserGroup userGroup) {
		validator.validate(userGroup);
		validator.onErrorRedirectTo(this).add();
		userGroupDao.atualizar(userGroup);
		result.redirectTo(this).index();
	}

	@Delete
	public void del(UserGroup userGroup) {
		userGroupDao.deletar(userGroup);
		result.redirectTo(this).index();
	}

	@Get("/userGroup/view/{id}")
	public UserGroup view(Long id) {
		setSelects();
		return userGroupDao.buscar(id);
	}

	@Get
	public void gridy(String search, int page, String sortName, String sortOrder, String find, int rows) {
		Gridy g = userGroupDao.listar(search, page, sortName, sortOrder, find, rows);
		List<UserGroup> userGroups = g.getList();
		JSONArray list = new JSONArray();
		for (UserGroup userGroup : userGroups) {
			Map child = new HashMap();
			child.put("id", userGroup.getId());
			child.put("name", userGroup.getName());
			child.put("description", userGroup.getDescription());
			list.put(child);
		}
		Map root = new HashMap();
		root.put("list", list);
		root.put("total", g.getTotal());
		JSONArray json = new JSONArray();
		json.put(root);
		result.use(Results.http()).body(json.toString().substring(1, json.toString().length() - 1));
	}

	private void setSelects() {
		result.include("controllerGroups", controllerGroupDao.listarTodos());
	}
}