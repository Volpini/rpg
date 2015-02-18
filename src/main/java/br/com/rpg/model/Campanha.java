/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.rpg.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author ECLima
 */
@Entity
public class Campanha implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String nome;
    private String descricao;
    @ManyToMany
    @JoinTable(name="campanha_personagem",
            joinColumns = {@JoinColumn(name="campanha_id")},
            inverseJoinColumns = {@JoinColumn(name="personagem_id")})
    private List<Personagem> personagens;
    
    @ManyToOne
    @JoinColumn(name="mestre_id")
    private SystemUser mestre;
    
    
    

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
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the personagens
     */
    public List<Personagem> getPersonagens() {
        return personagens;
    }

    /**
     * @param personagens the personagens to set
     */
    public void setPersonagens(List<Personagem> personagens) {
        this.personagens = personagens;
    }

    /**
     * @return the mestre
     */
    public SystemUser getMestre() {
        return mestre;
    }

    /**
     * @param mestre the mestre to set
     */
    public void setMestre(SystemUser mestre) {
        this.mestre = mestre;
    }
    
}
