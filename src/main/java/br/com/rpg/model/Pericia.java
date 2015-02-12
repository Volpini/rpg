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
import javax.validation.constraints.NotNull;

/**
 *
 * @author ECLima
 */
@Entity
public class Pericia implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String nome;
    
    @ManyToOne
    private Habilidade habilidade;

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
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the habilidade
     */
    public Habilidade getHabilidade() {
        return habilidade;
    }

    /**
     * @param habilidade the habilidade to set
     */
    public void setHabilidade(Habilidade habilidade) {
        this.habilidade = habilidade;
    }

}
