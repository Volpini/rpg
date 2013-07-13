package br.com.rpg.enumeration;

/**
 * @author Bruno Gonçales Lima
 */
public enum ViewType {

	EDIT("edit"), ADD("add"), GRID("index"), VIEW("view");
	private final String nome;

	ViewType(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public boolean getIsView() {
		return this.equals(VIEW);
	}

	public boolean getIsAdd() {
		return this.equals(ADD);
	}

	public boolean getIsGrid() {
		return this.equals(GRID);
	}

	public boolean getIsEdit() {
		return this.equals(EDIT);
	}
}