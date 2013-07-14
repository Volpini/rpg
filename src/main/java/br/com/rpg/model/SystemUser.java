package br.com.rpg.model;

import br.com.rpg.component.grid.GridColumn;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * @author Bruno Volpini
 */
@Entity
public class SystemUser implements Serializable {

	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@GridColumn(label = "Nome", position = 0, value = "name", searchable = true)
	private String name;
	@NotNull
	@Column(unique = true)
	@GridColumn(label = "E-mail", position = 1, value = "email", searchable = true)
	private String email;
	@NotNull
	private String password;
	@ManyToOne(fetch = FetchType.EAGER)
	@GridColumn(label = "Grupo", position = 2, value = "userGroup_name", searchable = true)
	private UserGroup userGroup;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}
	public String getForSelect(){
		return this.name;
	}
}