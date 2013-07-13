package br.com.rpg.component.grid;

/**
 * Representação de uma coluna de um grid
 * @author Bruno Gonçales Lima
 */

public class GridHeaderColunm {

	private final String name;
	private final String value;
	private int width;

	public GridHeaderColunm(String name, String value){
		this.name = name;
		this.value = value.isEmpty() ? name.toLowerCase() : value;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
