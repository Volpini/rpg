/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rpg.converter;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 *
 * @author ECLima
 */
@Convert(Date.class)
public class DateConverter implements Converter<Date> {

    @Override
    public Date convert(String value, Class<? extends Date> type, ResourceBundle rb) {

        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(value);
        } catch (ParseException e) {

            return null;
        }
    }

}
