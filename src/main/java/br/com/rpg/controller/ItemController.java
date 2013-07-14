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
import br.com.rpg.dao.ItemDao;
import br.com.rpg.dao.LivroDao;
import br.com.rpg.model.Item;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

/**
 * @author Bruno Volpini
 */
@Resource
@BreadCrumb(menu = "menu.item", controller = "menu.administracao.item")
public class ItemController {

	private final Result result;
	private GridHeader<Item> gridHeader;
	private final Validator validator;
	private final ItemDao itemDao;
	private final LivroDao livroDao;

	public ItemController(Result result, Validator validator, ItemDao itemDao, LivroDao livroDao) {
		this.result = result;
		gridHeader = new GridHeader<>(result, Item.class);
		this.validator = validator;
		this.itemDao = itemDao;
		this.livroDao = livroDao;
	}

	@Get
	@BreadCrumb(menu = "", controller = "")
	public void home() {
	}

	@Get("/item")
	public void index() {
		gridHeader.getHeader();
	}

	@Get
	public void add() {
		setSelects();
	}

	@Post
	public void add(Item item) {
		validator.validate(item);
		validator.onErrorRedirectTo(this).add();
		itemDao.adicionar(item);
		result.redirectTo(this).index();
	}

	@Get("/item/edit/{id}")
	public Item edit(Long id) {
		setSelects();
		return itemDao.buscar(id);
	}

	@Post
	public void edit(Item item) {
		validator.validate(item);
		validator.onErrorRedirectTo(this).edit(item.getId());
		itemDao.atualizar(item);
		result.redirectTo(this).index();
	}

	@Get("/item/view/{id}")
	public Item view(Long id) {
		setSelects();
		return itemDao.buscar(id);
	}

	@Delete
	public void del(Item item) {
		itemDao.deletar(item);
		result.redirectTo(this).index();
	}

	@Get
	public void gridy(String search, int page, String sortName, String sortOrder, String find, int rows) {
		Gridy g = itemDao.listar(search, page, sortName, sortOrder, find, rows);
		List<Item> itens = g.getList();
		List list = new ArrayList();
		for (Item itemD : itens) {
			Map item = new HashMap();
			item.put("id", itemD.getId());
			item.put("name", itemD.getName());
			list.add(item);
		}
		Map root = new HashMap();
		root.put("list", list);
		root.put("total", g.getTotal());
		JSONArray json = new JSONArray();
		json.put(root);
		result.use(Results.http()).body(json.toString().substring(1, json.toString().length() - 1));
	}
	
	private void setSelects(){
		result.include("livros", livroDao.listarTodos());
	}
}