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
import br.com.rpg.dao.PersonagemDao;
import br.com.rpg.dao.SystemUserDao;
import br.com.rpg.model.Personagem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

/**
 * @author Bruno Volpini
 */
@Resource
@BreadCrumb(menu = "menu.personagem", controller = "menu.administracao.personagem")
public class PersonagemController {

	private final Result result;
	private GridHeader<Personagem> gridHeader;
	private final Validator validator;
	private final PersonagemDao personagemDao;
	private final SystemUserDao systemUserDao;
	
	public PersonagemController(Result result, Validator validator, PersonagemDao personagemDao,
			SystemUserDao systemUserDao) {
		this.result = result;
		gridHeader = new GridHeader<>(result, Personagem.class);
		this.validator = validator;
		this.personagemDao = personagemDao;
		this.systemUserDao = systemUserDao;
	}

	@Get
	@BreadCrumb(menu = "", controller = "")
	public void home() {
	}

	@Get("/personagem")
	public void index() {
		gridHeader.getHeader();
	}

	@Get
	public void add() {
		setSelects();
	}

	@Post
	public void add(Personagem personagem) {
		validator.validate(personagem);
		validator.onErrorRedirectTo(this).add();
		personagemDao.adicionar(personagem);
		result.redirectTo(this).index();
	}

	@Get("/personagem/edit/{id}")
	public Personagem edit(Long id) {
		setSelects();
		return personagemDao.buscar(id);
	}

	@Post
	public void edit(Personagem personagem) {
		validator.validate(personagem);
		validator.onErrorRedirectTo(this).edit(personagem.getId());
		personagemDao.atualizar(personagem);
		result.redirectTo(this).index();
	}

	@Get("/personagem/view/{id}")
	public Personagem view(Long id) {
		setSelects();
		return personagemDao.buscar(id);
	}

	@Delete
	public void del(Personagem personagem) {
		personagemDao.deletar(personagem);
		result.redirectTo(this).index();
	}

	@Get
	public void gridy(String search, int page, String sortName, String sortOrder, String find, int rows) {
		Gridy g = personagemDao.listar(search, page, sortName, sortOrder, find, rows);
		List<Personagem> personagens = g.getList();
		List list = new ArrayList();
		for (Personagem personagem : personagens) {
			Map item = new HashMap();
			item.put("id", personagem.getId());
			item.put("name", personagem.getName());
			item.put("systemUser_name", personagem.getSystemUser().getName());
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
		result.include("systemUsers", systemUserDao.listarTodos());
	}
}