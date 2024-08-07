package nl.suzannawentzel.wicketcompact.converters;

import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateConverter implements IConverter<LocalDate>
{
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);

	@Override
	public LocalDate convertToObject(String s, Locale locale) throws ConversionException
	{
		return LocalDate.parse(s, formatter);
	}

	@Override
	public String convertToString(LocalDate localDate, Locale locale)
	{
		return formatter.format(localDate);
	}
}
