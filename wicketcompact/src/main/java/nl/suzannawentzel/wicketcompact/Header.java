package nl.suzannawentzel.wicketcompact;

import nl.suzannawentzel.wicketcompact.resources.LogoResourceReference;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import java.awt.print.Book;

public class Header extends Panel
{
	public Header(String id) {
		super(id);
//		add(new BookmarkablePageLink<Void>("logo", getApplication().getHomePage()));
		add(new BookmarkablePageLink<Void>("dashboard", getApplication().getHomePage()));
		add(new BookmarkablePageLink<Void>("categories", CategoriesPage.class));
		add(new BookmarkablePageLink<Void>("items", ItemsPage.class));
		add(new BookmarkablePageLink<Void>("tables", TablesPage.class));
		add(new Image("brand", new LogoResourceReference()));
	}
}
