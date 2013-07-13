package br.com.rpg.component.grid;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.ioc.RequestScoped;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.json.JSONArray;

/**
 * Pega atributos anotados no model e gera json esperado com titulo e valor
 * @author Bruno Gonçales Lima
 * @param <T> Tipo do grid
 * @see GridColumn
 * @see GridHeaderColunm
 */
@RequestScoped
public class GridHeader<T> {

	private static final String GRID_COLUNAS = "gridColunas";
	private static final String GRID_BUSCA = "gridBusca";
	private static final String COLUNAS = "colunas";
	
	private final Result result;

	private final Class<T> clazzGrid;
	
	public GridHeader(Result result, Class<T> clazzGrid) {
		this.result = result;
		this.clazzGrid = clazzGrid;
	}

	/**
	 * Disponibiliza na view duas variáveis com informações do grid
	 * @see GridColumn
	 */
	public void getHeader() {
		this.result.include(COLUNAS, getAllNameFields());
		this.result.include(GRID_COLUNAS, getColunas());
		this.result.include(GRID_BUSCA, getPesquisaveis());
	}

	private JSONArray getColunas() {
		GridHeaderColunm colunmOptions = new GridHeaderColunm("Options","");
		colunmOptions.setWidth(90);
		List<GridHeaderColunm> allNameFields = getAllNameFields();
		allNameFields.add(colunmOptions);
		return new JSONArray( allNameFields , false);
	}

	private JSONArray getPesquisaveis() {
		return new JSONArray( getNameFieldsSeacheable() , false);
	}
	
	private List<GridHeaderColunm> getAllNameFields() {
		List<GridHeaderColunm> fields = new ArrayList<GridHeaderColunm>();
		
		List<Field> colunasNaoOrdenadas = new ArrayList<Field>();
		
		Field[] atributos = this.clazzGrid.getDeclaredFields();
		
		for (Field field : atributos) {
			if(field.isAnnotationPresent(GridColumn.class)){
				colunasNaoOrdenadas.add(field);
			}
		}
		
		Collections.sort(colunasNaoOrdenadas, getComparator());
		
		for (Field field : colunasNaoOrdenadas) {
			GridColumn colunm = field.getAnnotation(GridColumn.class);
			String name = colunm.label();
			String value = colunm.value();					
			GridHeaderColunm hc = new GridHeaderColunm(name, value);
			fields.add(hc);
		}
		
		return fields;
	}

	private Comparator<Field> getComparator() {
		Comparator<Field> comparator = new Comparator<Field>() {
			public int compare(Field o1, Field o2) {
				GridColumn colunm1 = o1.getAnnotation(GridColumn.class);
				GridColumn colunm2 = o2.getAnnotation(GridColumn.class);
				if(colunm1.position() < colunm2.position()){
					return -1;
				}
				if(colunm1.position() > colunm2.position()){
					return 1;
				}
				return 0;
			}
		};
		return comparator;
	}
	
	private List<GridHeaderColunm> getNameFieldsSeacheable() {
		List<GridHeaderColunm> fields = new ArrayList<GridHeaderColunm>();
		
		List<Field> colunasNaoOrdenadas = new ArrayList<Field>();
		
		Field[] atributos = this.clazzGrid.getDeclaredFields();
		
		for (Field field : atributos) {
			if(field.isAnnotationPresent(GridColumn.class)){
				GridColumn colunm = field.getAnnotation(GridColumn.class);
				if(colunm.searchable() == true){
					colunasNaoOrdenadas.add(field);
				}
			}
		}
		
		Collections.sort(colunasNaoOrdenadas, getComparator());
		
		for (Field field : colunasNaoOrdenadas) {
			GridColumn colunm = field.getAnnotation(GridColumn.class);
			String name = colunm.label();
			String value = colunm.value();					
			GridHeaderColunm hc = new GridHeaderColunm(name, value);
			fields.add(hc);
		}
		
		return fields;
	}
}