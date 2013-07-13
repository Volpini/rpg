package br.com.rpg.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Guilherme Felipe de C. G. da Silva
 */
@Entity
public class Permission implements Serializable {

	@Id
	@GeneratedValue
	private Long id;
	@Column(columnDefinition = "TINYINT(1)")
	private boolean pAdd = false;
	@Column(columnDefinition = "TINYINT(1)")
	private boolean pEdit = false;
	@Column(columnDefinition = "TINYINT(1)")
	private boolean pView = false;
	@Column(columnDefinition = "TINYINT(1)")
	private boolean pDel = false;
	@Column(columnDefinition = "TINYINT(1)")
	private boolean pGrid = false;
	@ManyToOne(fetch = FetchType.EAGER)
	private Controller cont;

	public Controller getCont() {
		return cont;
	}

	public void setCont(Controller cont) {
		this.cont = cont;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean ispAdd() {
		return pAdd;
	}

	public void setpAdd(boolean pAdd) {
		this.pAdd = pAdd;
	}

	public boolean ispEdit() {
		return pEdit;
	}

	public void setpEdit(boolean pEdit) {
		this.pEdit = pEdit;
	}

	public boolean ispView() {
		return pView;
	}

	public void setpView(boolean pView) {
		this.pView = pView;
	}

	public boolean ispDel() {
		return pDel;
	}

	public void setpDel(boolean pDel) {
		this.pDel = pDel;
	}

	public boolean ispGrid() {
		return pGrid;
	}

	public void setpGrid(boolean pGrid) {
		this.pGrid = pGrid;
	}

	@Override
	public Permission clone() {
		Permission permission = new Permission();
		permission.setCont(getCont());
		permission.setpAdd(ispAdd());
		permission.setpEdit(ispEdit());
		permission.setpView(ispView());
		permission.setpDel(ispDel());
		permission.setpGrid(ispGrid());
		return permission;
	}
}