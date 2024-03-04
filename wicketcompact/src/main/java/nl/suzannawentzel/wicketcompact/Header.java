package nl.suzannawentzel.wicketcompact;

import nl.suzannawentzel.wicketcompact.resources.LogoResourceReference;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import java.awt.print.Book;

public class Header extends Panel
{
	private WebMarkupContainer navbar;

	public Header(String id) {
		super(id);
	}

	// WAS DOING: verschillende tenants, andere navbar per tenant
	// TODO: ASK HELP IMAGE RESOURCE
	// TODO: CHECK TUTORIAL AGAIN
	// TODO: IF NO FIX: ASK HELP NAVBAR
	@Override
	public void onInitialize() {
//		super.onInitialize();
//		navbar = new WebMarkupContainer("navbar");
//		navbar.add(new NavLink("dashboard", getApplication().getHomePage()));
//		navbar.add(new NavLink("categories", CategoriesPage.class));
//		navbar.add(new NavLink("items", ItemsPage.class));
//		navbar.add(new NavLink("tables", TablesPage.class));
//		navbar.add(new ContextImage("brand", "cat.jpg"));
//
//		final Tenant tenant = Tenant.get();
//		final String tenantClass = tenant.equals(Tenant.DEFAULT) ? "navbar-dark bg-dark": "navbar-light bg-light";
//		navbar.add(new AttributeAppender("class", tenantClass));
//		add(navbar);
		super.onInitialize();
		add(new NavLink("dashboard", getApplication().getHomePage()));
		add(new NavLink("categories", CategoriesPage.class));
		add(new NavLink("items", ItemsPage.class));
		add(new NavLink("tables", TablesPage.class));
		add(new ContextImage("brand", "coffee.png"));
	}
}
