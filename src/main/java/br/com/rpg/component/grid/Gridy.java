package br.com.rpg.component.grid;

import java.util.List;

/**
 *
 * @author Guilherme Felipe C. G. da Silva
 */
@SuppressWarnings({"unused", "rawtypes"})
public class Gridy {

	private List list;
	private Long total;

	public Gridy(List list, Long total) {
		this.list = list;
		this.total = total;
	}

	public List getList() {
		return list;
	}

	public Long getTotal() {
		return total;
	}
}