package br.com.rpg.util;

import java.util.ArrayList;
import java.util.List;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.validator.Message;
import br.com.caelum.vraptor.validator.ValidationMessage;

/**
 * Classe utilitária para validar Entidade(s)/Modelo(s) com relacionamento(s).
 * Exemplo de uso: 
 * validatorUtil.validate(new String[]{"entidade1", "entidade2"}, entidade1, entidade1.getEntidade2());
 * 
 * @author Guilherme Felipe de C. G. da Silva
 */
@Component
public class ValidatorUtil {
	
	private Result result;
	private Validator validator;
	private ArrayList<Message> errors = new ArrayList<Message>();
	private ArrayList<Message> exibicaoErrors = new ArrayList<Message>();
	
	public ValidatorUtil(Result result, Validator validator){
		this.result = result;
		this.validator = validator;
	}
	
	/**
	 * @param keys Os nomes das entidades/modelos.
	 * @param values As entidades para validação separado por vírgula.
	 */
	public void validate(String[] keys, Object... values){
		List<Message> tmp = validator.getErrors();
		int cont = 0;
		for (int nObject = 0; nObject < values.length; nObject++) {
			if(values[nObject] != null){
				if(!(values[nObject] instanceof List)) {
					validator.validate(values[nObject]);
					int size = tmp.size();
					for(int i = cont; i < size; i++, cont++){
						errors.add(new ValidationMessage(tmp.get(i).getMessage(), keys[nObject]+"_"+tmp.get(i).getCategory()));
						validator.add(new ValidationMessage(tmp.get(i).getMessage(), keys[nObject]+"_"+tmp.get(i).getCategory()));
					}
				} else {
					@SuppressWarnings("rawtypes")
					List list = (List) values[nObject];
					for (int j = 0; j < list.size(); j++) {
						validator.validate(list.get(j));
						int size = tmp.size();
						for(int i = cont; i < size; i++, cont++){
							errors.add(new ValidationMessage(tmp.get(i).getMessage(), keys[nObject]+"_"+j+"_"+tmp.get(i).getCategory()));
							validator.add(new ValidationMessage(tmp.get(i).getMessage(), keys[nObject]+"_"+j+"_"+tmp.get(i).getCategory()));
						}
					}
				}
			}
		}
		result.include("utilErrors", errors);
	}
	
	/**
	 * Adiciona erro no validator.
	 * @param message
	 */
	public void add(Message message){
		exibicaoErrors.add(message);
		validator.add(message);
		result.include("exibicaoErrors", exibicaoErrors);
	}
}