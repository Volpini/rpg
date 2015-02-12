/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.rpg.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author ECLima
 */
@Entity
public class PersonagemPericia implements Serializable{
    
    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToOne
    private Pericia pericia;
    
    @ManyToOne
    private Personagem personagem;
    
    
    private Double graduacao;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the pericia
     */
    public Pericia getPericia() {
        return pericia;
    }

    /**
     * @param pericia the pericia to set
     */
    public void setPericia(Pericia pericia) {
        this.pericia = pericia;
    }

    /**
     * @return the graduacao
     */
    public Double getGraduacao() {
        return graduacao;
    }

    /**
     * @param graduacao the graduacao to set
     */
    public void setGraduacao(Double graduacao) {
        this.graduacao = graduacao;
    }

    /**
     * @return the personagem
     */
    public Personagem getPersonagem() {
        return personagem;
    }

    /**
     * @param personagem the personagem to set
     */
    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }
    
    
}
