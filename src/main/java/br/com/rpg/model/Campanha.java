package br.com.rpg.model;

import br.com.rpg.component.grid.GridColumn;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.joda.time.LocalDateTime;

/**
 *
 * @author ECLima
 */
@Entity
public class Campanha implements Serializable {

	@Id
	@GeneratedValue
	private Long id;
	@GridColumn(label = "Nome", position = 0, searchable = true, value = "nome")
	private String nome;
        @Lob
	private String descricao;
        
        @Temporal(TemporalType.DATE)
        private Date dataInicio;
        
        @Temporal(TemporalType.DATE)
        private Date dataCadastro;
        
	@ManyToMany
	@JoinTable(name = "campanha_personagem",
		joinColumns = {
			@JoinColumn(name = "campanha_id")},
		inverseJoinColumns = {
			@JoinColumn(name = "personagem_id")})
	private List<Personagem> personagens;

	@ManyToOne
	@JoinColumn(name = "mestre_id")
	private SystemUser mestre;

        
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Personagem> getPersonagens() {
		return personagens;
	}

	public void setPersonagens(List<Personagem> personagens) {
		this.personagens = personagens;
	}

	public SystemUser getMestre() {
		return mestre;
	}

	public void setMestre(SystemUser mestre) {
		this.mestre = mestre;
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
