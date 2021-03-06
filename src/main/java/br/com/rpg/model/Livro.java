package br.com.rpg.model;

import br.com.rpg.component.grid.GridColumn;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * @author Bruno Volpini
 */
@Entity
public class Livro implements Serializable {

	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@GridColumn(label = "Nome", position = 0, value = "name", searchable = true)
	private String name;
	@NotNull
	@GridColumn(label = "Versão", position = 1, value = "versao", searchable = true)
	private String versao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}
	
	public String getForSelect(){
		return this.name;
	}
}