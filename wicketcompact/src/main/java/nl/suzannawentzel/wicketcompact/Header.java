package nl.suzannawentzel.wicketcompact;

import nl.suzannawentzel.wicketcompact.categories.CategoriesPage;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.panel.Panel;

public class Header extends Panel
{
	private WebMarkupContainer navbar;

	public Header(String id) {
		super(id);
	}

 	@Override
	public void onInitialize() {
		super.onInitialize();
		navbar = new WebMarkupContainer("navbar");
		navbar.add(new NavLink("dashboard", getApplication().getHomePage()));
		navbar.add(new NavLink("categories", CategoriesPage.class));
		navbar.add(new NavLink("items", ItemsPage.class));
		navbar.add(new NavLink("tables", TablesPage.class));

		final Tenant tenant = Tenant.get();
		navbar.add(new ContextImage("brand", tenant.equals(Tenant.DEFAULT)? "coffee.png" : "coffee_togo.png"));
		navbar.add(new AttributeAppender("class", tenant.equals(Tenant.DEFAULT) ? " navbar-light bg-light" : " navbar-dark bg-dark"));
		add(navbar);
	}
}
