package nl.suzannawentzel.wicketcompact;

import nl.suzannawentzel.wicketcompact.resources.LogoResourceReference;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import java.awt.print.Book;

public class Header extends Panel
{
	private final MarkupContainer dashboardLink;
	private final MarkupContainer categoriesLink;
	private final MarkupContainer itemsLink;
	private final MarkupContainer tablesLink;

	public Header(String id) {
		super(id);
//		add(new BookmarkablePageLink<Void>("logo", getApplication().getHomePage()));
		dashboardLink = add(new BookmarkablePageLink<Void>("dashboard", getApplication().getHomePage()));
		categoriesLink = add(new BookmarkablePageLink<Void>("categories", CategoriesPage.class));
		itemsLink = add(new BookmarkablePageLink<Void>("items", ItemsPage.class));
		tablesLink = add(new BookmarkablePageLink<Void>("tables", TablesPage.class));
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		System.err.println(getPage().getClass());
		dashboardLink.setEnabled(!getPage().getClass().equals(HomePage.class));
		categoriesLink.setEnabled(!getPage().getClass().equals(CategoriesPage.class));
		itemsLink.setEnabled(!getPage().getClass().equals(ItemsPage.class));
		tablesLink.setEnabled(!getPage().getClass().equals(TablesPage.class));

		add(new Image("brand", new LogoResourceReference()));
	}
}
