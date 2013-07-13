package br.com.rpg.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * @author Bruno Volpini
 */
@Entity
public class ControllerGroup implements Serializable {

	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	private String name;
	@OneToMany
	private List<Controller> controllers;

	public List<Controller> getControllers() {
		return controllers;
	}

	public void setControllers(List<Controller> controllers) {
		this.controllers = controllers;
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
}