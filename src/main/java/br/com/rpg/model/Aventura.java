/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.rpg.model;

import br.com.rpg.component.grid.GridColumn;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ECLima
 */
@Entity
public class Aventura implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    @GridColumn(label = "Nome da Aventura", position = 0, searchable = true, value = "nome")
    private String nome;
    private String descricao;
    
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    @Temporal(TemporalType.DATE)
    private Date dataFim;
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
   
    
    @ManyToOne
    private Campanha campanha;
    @ManyToMany
    private List<Personagem> personagens;
    

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
     * @return the campanha
     */
    public Campanha getCampanha() {
        return campanha;
    }

    /**
     * @param campanha the campanha to set
     */
    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
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
     * @return the dataInicio
     */
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * @param dataInicio the dataInicio to set
     */
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return the dataFim
     */
    public Date getDataFim() {
        return dataFim;
    }

    /**
     * @param dataFim the dataFim to set
     */
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * @return the dataCadastro
     */
    public Date getDataCadastro() {
        return dataCadastro;
    }

    /**
     * @param dataCadastro the dataCadastro to set
     */
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    
}
