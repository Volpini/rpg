package br.com.rpg.model;

import br.com.rpg.component.grid.GridColumn;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;

/**
 * @author Bruno Volpini
 */
@Entity
public class UserGroup implements Serializable {

	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@Column(unique = true)
	@GridColumn(label = "Nome", position = 0, value = "name", searchable = true)
	private String name;
	@GridColumn(label = "Descrição", position = 1, value = "description", searchable = true)
	private String description;
	@OrderBy(value = "cont")
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Permission> permissions;

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getForSelect() {
		return name;
	}
}