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
public class PersonagemHabilidade implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Habilidade habilidade;

	@ManyToOne
	private Personagem personagem;

	private Integer valor;

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

	/**
	 * @return the valor
	 */
	public Integer getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(Integer valor) {
		this.valor = valor;
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
