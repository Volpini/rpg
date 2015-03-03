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
import br.com.rpg.dao.AventuraDao;
import br.com.rpg.dao.CampanhaDao;
import br.com.rpg.dao.PersonagemDao;
import br.com.rpg.model.Aventura;
import br.com.rpg.model.Campanha;
import br.com.rpg.util.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityNotFoundException;
import org.json.JSONArray;

/**
 * @author Erick Lima
 */
@Resource
@BreadCrumb(menu = "menu.aventura", controller = "menu.administracao.aventura")
public class AventuraController {

    private final Result result;
    private GridHeader<Aventura> gridHeader;
    private final Validator validator;
    private final PersonagemDao personagemDao;
    private final AventuraDao aventuraDao;
    private Campanha campanha ;
    private final CampanhaDao campanhaDao;
    
    public AventuraController(Result result, Validator validator, PersonagemDao personagemDao, AventuraDao aventuraDao, CampanhaDao campanhaDao) {
        this.result = result;
        gridHeader = new GridHeader<>(result, Aventura.class);
        this.validator = validator;
        
        this.personagemDao = personagemDao;
        this.aventuraDao = aventuraDao;
        this.campanhaDao = campanhaDao;
    }

    @Get
    @BreadCrumb(menu = "", controller = "")
    public void home() {
    }

    @Get("/aventura")
    public void index() {
        gridHeader.getHeader();
    }

    @Get("/campanha/{idCampanha}/{nomeCampanha}/aventura/add")
    public void add(Long idCampanha,String nomeCampanha) {
        try{
            campanha =  campanhaDao.buscar(idCampanha);
            
             setSelects();
             
        }catch(EntityNotFoundException e){
            result.redirectTo(CampanhaController.class).index();
            
        }
        
        
    }

    @Post("/aventura/add")
    public void add(Aventura aventura, Campanha campanha) {
        validator.validate(aventura);
        validator.onErrorRedirectTo(this).add(campanha.getId(),campanha.getNome());
        aventura.setCampanha(campanha);
        aventuraDao.adicionar(aventura);
        result.redirectTo(CampanhaController.class).view(campanha.getId());
    }

    @Get("/aventura/edit/{id}")
    public Aventura edit(Long id) {
        setSelects();
        return aventuraDao.buscar(id);
    }

    @Post
    public void edit(Aventura aventura) {
        validator.validate(aventura);
        validator.onErrorRedirectTo(this).edit(aventura.getId());
        aventuraDao.atualizar(aventura);
        result.redirectTo(this).index();
    }

    @Get("/aventura/view/{id}")
    public Aventura view(Long id) {
        setSelects();
        return aventuraDao.buscar(id);
    }

    @Delete
    public void del(Aventura aventura) {
        aventuraDao.deletar(aventura);
        result.redirectTo(this).index();
    }

    @Get
    public void gridy(String search, int page, String sortName, String sortOrder, String find, int rows) {
        
        
    }

    @Get
    public void gridyByCampanha(String search, int page, String sortName, String sortOrder, String find, int rows, Long idAventura) {
                
        Gridy g = aventuraDao.listarAventurasByCampanha(search, page, sortName, sortOrder, find, rows, idAventura);
        List<Aventura> aventuras = g.getList();
        List list = new ArrayList();
        for (Aventura aventura : aventuras) {
            Map campanhaM = new HashMap();
            campanhaM.put("id", aventura.getId());
            campanhaM.put("nome", aventura.getNome());
            list.add(campanhaM);

            campanhaM.put("classDelete", Util.CLASS_HIDE_BUTTON);
            campanhaM.put("classEdit", Util.CLASS_HIDE_BUTTON);

        }
        Map root = new HashMap();
        root.put("list", list);
        root.put("total", g.getTotal());
        JSONArray json = new JSONArray();
        json.put(root);
        result.use(Results.http()).body(json.toString().substring(1, json.toString().length() - 1));
    }

    private void setSelects() {
        result.include("campanha", campanha);
        result.include("personagens", personagemDao.listarTodosByCampanha(campanha.getId()));
    }
}
