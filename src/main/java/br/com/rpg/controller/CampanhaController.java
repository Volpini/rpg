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
import br.com.rpg.model.Aventura;
import br.com.rpg.model.Campanha;
import br.com.rpg.model.Personagem;
import br.com.rpg.model.SystemUser;
import br.com.rpg.model.view.CampanhasMestrePersonagemView;
import br.com.rpg.session.UserSession;
import br.com.rpg.util.Util;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.joda.time.LocalDateTime;
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
    private final UserSession user;
    private GridHeader<Aventura> gridAventura;

    public CampanhaController(Result result, Validator validator, CampanhaDao campanhaDao, PersonagemDao personagemDao, UserSession user) {
        this.result = result;
        gridHeader = new GridHeader<>(result, Campanha.class);
        this.validator = validator;
        this.campanhaDao = campanhaDao;
        this.personagemDao = personagemDao;
        this.user = user;
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
        Campanha campanha = campanhaDao.buscar(id);
        setDatas(campanha);
        return campanha;
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
        gridAventura = new GridHeader<>(result, Aventura.class);
        gridAventura.setShowOptions(false);
        Campanha campanha = campanhaDao.buscar(id);
        setDatas(campanha);
        if(!this.user.getSystemUser().equals(campanha.getMestre())){
            gridAventura.disableAddButton();
        }
        gridAventura.getHeader();
        return campanha;

    }

    @Delete
    public void del(Campanha campanha) {
        campanhaDao.deletar(campanha);
        result.redirectTo(this).index();
    }

    @Get
    public void gridy(String search, int page, String sortName, String sortOrder, String find, int rows) {
        SystemUser user = this.user.getSystemUser();
        Gridy g = campanhaDao.listarCampanhasView(search, page, sortName, sortOrder, find, rows, user.getId());
        List<CampanhasMestrePersonagemView> campanhas = g.getList();
        List list = new ArrayList();

        for (CampanhasMestrePersonagemView campanha : campanhas) {
           Map campanhaM = new HashMap();
            campanhaM.put("id", campanha.getId());
            campanhaM.put("nome", campanha.getNome());
            list.add(campanhaM);
            if (user.getId() == campanha.getMestre()) {
                campanhaM.put("classDelete", Util.CLASS_SHOW_BUTTON);
                campanhaM.put("classEdit", Util.CLASS_SHOW_BUTTON);
            } else {
                campanhaM.put("classDelete", Util.CLASS_HIDE_BUTTON);
                campanhaM.put("classEdit", Util.CLASS_HIDE_BUTTON);
            }

        }
        Map root = new HashMap();
        root.put("list", list);
        root.put("total", g.getTotal());
        JSONArray json = new JSONArray();
        json.put(root);
        result.use(Results.http()).body(json.toString().substring(1, json.toString().length() - 1));
    }

    private void setSelects() {
        List<Personagem> personagens = personagemDao.listarTodosNaoMestre(this.user.getSystemUser().getId());
        

        result.include("personagens", personagens);
    }
    private void setDatas(Campanha campanha){
        Date dataIn = campanha.getDataInicio();
        Date dataCad = campanha.getDataCadastro();
        if(dataIn!=null){
            LocalDateTime dateInicio = new LocalDateTime(dataIn.getTime());
            result.include("dataInicio", dateInicio);
                }
        if(dataCad!=null){
            LocalDateTime dateCadastro = new LocalDateTime(dataCad.getTime());
            result.include("dataCadastro", dateCadastro);
        }
        
        
    }

}
