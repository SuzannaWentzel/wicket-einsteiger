package nl.suzannawentzel.wicketcompact;

import nl.suzannawentzel.wicketcompact.articles.CreateArticlePage;
import nl.suzannawentzel.wicketcompact.articles.ItemsPage;
import nl.suzannawentzel.wicketcompact.articles.ModifyArticlePage;
import nl.suzannawentzel.wicketcompact.categories.CategoriesPage;
import nl.suzannawentzel.wicketcompact.categories.CreateCategoryPage;
import nl.suzannawentzel.wicketcompact.categories.ModifyCategoryPage;
import nl.suzannawentzel.wicketcompact.converters.BooleanConverter;
import nl.suzannawentzel.wicketcompact.converters.CurrencyConverter;
import nl.suzannawentzel.wicketcompact.converters.LocalDateConverter;
import nl.suzannawentzel.wicketcompact.customers.Menu;
import nl.suzannawentzel.wicketcompact.dashboard.HomePage;
import nl.suzannawentzel.wicketcompact.exceptions.UnhandledExceptionPage;
import nl.suzannawentzel.wicketcompact.login.LoginPage;
import nl.suzannawentzel.wicketcompact.tables.CreateTablePage;
import nl.suzannawentzel.wicketcompact.tables.ModifyTablePage;
import nl.suzannawentzel.wicketcompact.tables.TablesPage;
import org.apache.wicket.ConverterLocator;
import org.apache.wicket.IConverterLocator;
import org.apache.wicket.Session;
import org.apache.wicket.bean.validation.BeanValidationConfiguration;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

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

		mountPage("/articles", ItemsPage.class);
		mountPage("/article/${id}", ModifyArticlePage.class);
		mountPage("/article/new", CreateArticlePage.class);

		mountPage("/categories", CategoriesPage.class);
		mountPage("/category/${id}", ModifyCategoryPage.class);
		mountPage("/category/new", CreateCategoryPage.class);

		mountPage("/tables", TablesPage.class);
		mountPage("/table/${id}", ModifyTablePage.class);
		mountPage("/table/new", CreateTablePage.class);

		mountPage("/menu", Menu.class);

		mountPage("/login", LoginPage.class);

		getRequestCycleListeners().add(new LoginAssertingRequestcycleListener());
		getApplicationSettings().setInternalErrorPage(UnhandledExceptionPage.class);
	}

	@Override
	public Session newSession(Request request, Response response) {
		return new SgSession(request);
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
