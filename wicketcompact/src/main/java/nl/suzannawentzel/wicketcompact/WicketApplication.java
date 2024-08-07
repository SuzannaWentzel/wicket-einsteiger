package nl.suzannawentzel.wicketcompact;

import com.sun.org.apache.xpath.internal.operations.Bool;
import nl.suzannawentzel.wicketcompact.converters.BooleanConverter;
import nl.suzannawentzel.wicketcompact.converters.CurrencyConverter;
import nl.suzannawentzel.wicketcompact.converters.LocalDateConverter;
import org.apache.wicket.ConverterLocator;
import org.apache.wicket.IConverterLocator;
import org.apache.wicket.bean.validation.BeanValidationConfiguration;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.convert.converter.IntegerConverter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see nl.suzannawentzel.wicketcompact.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();
		new BeanValidationConfiguration().configure(this);
		// add your configuration here
	}

	@Override
	protected IConverterLocator newConverterLocator() {
		final ConverterLocator defaultConverterLocator = new ConverterLocator();
		defaultConverterLocator.set(Boolean.class, new BooleanConverter());
		defaultConverterLocator.set(LocalDate.class, new LocalDateConverter());
		defaultConverterLocator.set(BigDecimal.class, new CurrencyConverter());
		return defaultConverterLocator;
	}
}
