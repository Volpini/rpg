/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.rpg.model.view;

import br.com.rpg.component.grid.GridColumn;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ECLima
 */
@Entity
@Table(name = "campanhasview")
public class CampanhasMestrePersonagemView implements Serializable{
    
    @Id
    private Long id;
    
    @GridColumn(label = "Nome da Campanha", position = 0, searchable = true, value = "nome")
    private String nome;
    private Long mestre;
    private Long jogador;

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
     * @return the mestre
     */
    public Long getMestre() {
        return mestre;
    }

    /**
     * @param mestre the mestre to set
     */
    public void setMestre(Long mestre) {
        this.mestre = mestre;
    }

    /**
     * @return the jogador
     */
    public Long getJogador() {
        return jogador;
    }

    /**
     * @param jogador the jogador to set
     */
    public void setJogador(Long jogador) {
        this.jogador = jogador;
    }
}
