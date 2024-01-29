package nl.suzannawentzel.wicketcompact;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class BaseEntitiesPage extends BaseWebPage
{
	public BaseEntitiesPage(PageParameters parameters)
	{
		super(parameters);
		add(new BookmarkablePageLink<Void>("linkDashboard", getApplication().getHomePage()));
	}
}
