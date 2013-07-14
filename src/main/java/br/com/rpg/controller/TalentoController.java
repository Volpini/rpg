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
import br.com.rpg.dao.TalentoDao;
import br.com.rpg.model.Talento;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

/**
 * @author Bruno Volpini
 */
@Resource
@BreadCrumb(menu = "menu.talento", controller = "menu.administracao.talento")
public class TalentoController {

	private final Result result;
	private GridHeader<Talento> gridHeader;
	private final Validator validator;
	private final TalentoDao talentoDao;

	public TalentoController(Result result, Validator validator, TalentoDao talentoDao) {
		this.result = result;
		gridHeader = new GridHeader<>(result, Talento.class);
		this.validator = validator;
		this.talentoDao = talentoDao;
	}

	@Get
	@BreadCrumb(menu = "", controller = "")
	public void home() {
	}

	@Get("/talento")
	public void index() {
		gridHeader.getHeader();
	}

	@Get
	public void add() {
	}

	@Post
	public void add(Talento talento) {
		validator.validate(talento);
		validator.onErrorRedirectTo(this).add();
		talentoDao.adicionar(talento);
		result.redirectTo(this).index();
	}

	@Get("/talento/edit/{id}")
	public Talento edit(Long id) {
		return talentoDao.buscar(id);
	}

	@Post
	public void edit(Talento talento) {
		validator.validate(talento);
		validator.onErrorRedirectTo(this).edit(talento.getId());
		talentoDao.atualizar(talento);
		result.redirectTo(this).index();
	}

	@Get("/talento/view/{id}")
	public Talento view(Long id) {
		return talentoDao.buscar(id);
	}

	@Delete
	public void del(Talento talento) {
		talentoDao.deletar(talento);
		result.redirectTo(this).index();
	}

	@Get
	public void gridy(String search, int page, String sortName, String sortOrder, String find, int rows) {
		Gridy g = talentoDao.listar(search, page, sortName, sortOrder, find, rows);
		List<Talento> talentos = g.getList();
		List list = new ArrayList();
		for (Talento talento : talentos) {
			Map item = new HashMap();
			item.put("id", talento.getId());
			item.put("name", talento.getName());
			list.add(item);
		}
		Map root = new HashMap();
		root.put("list", list);
		root.put("total", g.getTotal());
		JSONArray json = new JSONArray();
		json.put(root);
		result.use(Results.http()).body(json.toString().substring(1, json.toString().length() - 1));
	}
}