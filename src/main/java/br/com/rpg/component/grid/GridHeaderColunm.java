package br.com.rpg.component.grid;

/**
 * Representação de uma coluna de um grid
 *
 * @author Bruno Gonçales Lima
 */
public class GridHeaderColunm {

    private String name;
    private String value;
    private int width;
    private String clazz;

    public GridHeaderColunm(String name, String value) {
        this.name = name;
        this.value = value.isEmpty() ? name.toLowerCase() : value;
        this.clazz="gridNotRemove"; 
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

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the clazz
     */
    public String getClazz() {
        return clazz;
    }

    /**
     * @param clazz the clazz to set
     */
    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

}
