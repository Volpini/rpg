package br.com.rpg.model;

import br.com.rpg.component.grid.GridColumn;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * @author Bruno Volpini
 */
@Entity
public class Personagem implements Serializable {

	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@GridColumn(label = "Nome", position = 0, value = "name", searchable = true)
	private String name;
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@GridColumn(label = "Jogador", position = 1, value = "systemUser_name", searchable = true)
	private SystemUser systemUser;
	@ManyToMany
	private List<Talento> talentos;
	@NotNull
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "personagem")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<PersonagemItem> itensPersonagem;
	
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

	public SystemUser getSystemUser() {
		return systemUser;
	}

	public List<Talento> getTalentos() {
		return talentos;
	}

	public void setTalentos(List<Talento> talentos) {
		this.talentos = talentos;
	}

	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}

	public List<PersonagemItem> getItensPersonagem() {
		return itensPersonagem;
	}

	public void setItensPersonagem(List<PersonagemItem> itensPersonagem) {
		this.itensPersonagem = itensPersonagem;
	}
	
	public String getForSelect(){
		return name;
	}
}