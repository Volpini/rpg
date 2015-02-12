/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.rpg.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.rpg.model.Habilidade;
import org.hibernate.Session;

/**
 *
 * @author ECLima
 */
@Component
public class HabilidadeDao extends Dao<Habilidade>{

    public HabilidadeDao(Session session) {
        super(session, Habilidade.class);
    }
    
}
