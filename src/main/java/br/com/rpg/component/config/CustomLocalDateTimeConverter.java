package br.com.rpg.component.config;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.converter.ConversionError;
import br.com.caelum.vraptor.converter.jodatime.LocalDateTimeConverter;
import br.com.caelum.vraptor.core.Localization;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import org.joda.time.LocalDateTime;

/**
 *
 * @author Guilherme Felipe de C. G. da Silva
 */
@Convert(LocalDateTime.class)
public class CustomLocalDateTimeConverter extends LocalDateTimeConverter {

	public CustomLocalDateTimeConverter(Localization localization) {
		super(localization);
	}

	@Override
	public LocalDateTime convert(String value, Class<? extends LocalDateTime> type, ResourceBundle bundle) {
		try {
			return LocalDateTime.parse(value);
		} catch (Exception e) {
			throw new ConversionError(MessageFormat.format(bundle.getString("is_not_a_valid_datetime"), value));
		}
	}
}
