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
import br.com.rpg.dao.CampanhaDao;
import br.com.rpg.dao.PersonagemDao;
import br.com.rpg.model.Campanha;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

/**
 * @author Erick Lima
 */
@Resource
@BreadCrumb(menu = "menu.campanha", controller = "menu.administracao.campanha")
public class CampanhaController {

	private final Result result;
	private GridHeader<Campanha> gridHeader;
	private final Validator validator;
	private final CampanhaDao campanhaDao;
	private final PersonagemDao personagemDao;

	public CampanhaController(Result result, Validator validator, CampanhaDao campanhaDao, PersonagemDao personagemDao) {
		this.result = result;
		gridHeader = new GridHeader<>(result, Campanha.class);
		this.validator = validator;
		this.campanhaDao = campanhaDao;
		this.personagemDao = personagemDao;
	}

	@Get
	@BreadCrumb(menu = "", controller = "")
	public void home() {
	}

	@Get("/campanha")
	public void index() {
		gridHeader.getHeader();
	}

	@Get
	public void add() {
		setSelects();
	}

	@Post
	public void add(Campanha campanha) {
		validator.validate(campanha);
		validator.onErrorRedirectTo(this).add();
		campanhaDao.adicionar(campanha);
		result.redirectTo(this).index();
	}

	@Get("/campanha/edit/{id}")
	public Campanha edit(Long id) {
		setSelects();
		return campanhaDao.buscar(id);
	}

	@Post
	public void edit(Campanha campanha) {
		validator.validate(campanha);
		validator.onErrorRedirectTo(this).edit(campanha.getId());
		campanhaDao.atualizar(campanha);
		result.redirectTo(this).index();
	}

	@Get("/campanha/view/{id}")
	public Campanha view(Long id) {
		setSelects();
		return campanhaDao.buscar(id);
	}

	@Delete
	public void del(Campanha campanha) {
		campanhaDao.deletar(campanha);
		result.redirectTo(this).index();
	}

	@Get
	public void gridy(String search, int page, String sortName, String sortOrder, String find, int rows) {
		Gridy g = campanhaDao.listar(search, page, sortName, sortOrder, find, rows);
		List<Campanha> campanhas = g.getList();
		List list = new ArrayList();
		for (Campanha campanha : campanhas) {
			Map campanhaM = new HashMap();
			campanhaM.put("id", campanha.getId());
			campanhaM.put("nome", campanha.getNome());
			list.add(campanhaM);
		}
		Map root = new HashMap();
		root.put("list", list);
		root.put("total", g.getTotal());
		JSONArray json = new JSONArray();
		json.put(root);
		result.use(Results.http()).body(json.toString().substring(1, json.toString().length() - 1));
	}
	
	private void setSelects(){
		result.include("personagens", personagemDao.listarTodos());
	}
}