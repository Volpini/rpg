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
import br.com.rpg.dao.LivroDao;
import br.com.rpg.model.Livro;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

/**
 * @author Bruno Volpini
 */
@Resource
@BreadCrumb(menu = "menu.livro", controller = "menu.administracao.livro")
public class LivroController {

	private final Result result;
	private GridHeader<Livro> gridHeader;
	private final Validator validator;
	private final LivroDao livroDao;

	public LivroController(Result result, Validator validator, LivroDao livroDao) {
		this.result = result;
		gridHeader = new GridHeader<>(result, Livro.class);
		this.validator = validator;
		this.livroDao = livroDao;
	}

	@Get
	@BreadCrumb(menu = "", controller = "")
	public void home() {
	}

	@Get("/livro")
	public void index() {
		gridHeader.getHeader();
	}

	@Get
	public void add() {
	}

	@Post
	public void add(Livro livro) {
		validator.validate(livro);
		validator.onErrorRedirectTo(this).add();
		livroDao.adicionar(livro);
		result.redirectTo(this).index();
	}

	@Get("/livro/edit/{id}")
	public Livro edit(Long id) {
		return livroDao.buscar(id);
	}

	@Post
	public void edit(Livro livro) {
		validator.validate(livro);
		validator.onErrorRedirectTo(this).edit(livro.getId());
		livroDao.atualizar(livro);
		result.redirectTo(this).index();
	}

	@Get("/livro/view/{id}")
	public Livro view(Long id) {
		return livroDao.buscar(id);
	}

	@Delete
	public void del(Livro livro) {
		livroDao.deletar(livro);
		result.redirectTo(this).index();
	}

	@Get
	public void gridy(String search, int page, String sortName, String sortOrder, String find, int rows) {
		Gridy g = livroDao.listar(search, page, sortName, sortOrder, find, rows);
		List<Livro> livros = g.getList();
		List list = new ArrayList();
		for (Livro livro : livros) {
			Map item = new HashMap();
			item.put("id", livro.getId());
			item.put("name", livro.getName());
			item.put("versao", livro.getVersao());
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