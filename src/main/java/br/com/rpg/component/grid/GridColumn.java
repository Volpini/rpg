package br.com.rpg.component.grid;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Atributos no model que serão mostrados no grid devem ser anotados com esta anotação.
 * Para o campo value, quando houver uma classe filha, sempre usar "_" entre o nome da classe e do campo.
 * Exemplo: Classe Usuario => value = "grupo_nome"
 * 
 * @author Bruno Gonçales Lima
 * @author Guilherme Felipe de C. G. da Silva
 * @see GridHeader
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface GridColumn {

	/**
	 * Informa se campo é pesquisável ou não
	 * @return
	 */
	boolean searchable() default false;

	/**
	 * String para título no grid
	 * @return
	 */
	String label();
	
	/**
	 * String esperada como value no grid
	 * @return
	 */
	String value();
	
	/**
	 * Posição em que a coluna aparecerá no grid.
	 * @return
	 */
	int position();
	
}
