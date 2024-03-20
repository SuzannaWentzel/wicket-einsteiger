package nl.suzannawentzel.wicketcompact;

import nl.suzannawentzel.wicketcompact.entities.Article;
import nl.suzannawentzel.wicketcompact.entities.Category;
import nl.suzannawentzel.wicketcompact.services.ArticleService;
import nl.suzannawentzel.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigation;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.ArrayList;
import java.util.List;

public class ItemsPage extends BaseEntitiesPage
{
	public ItemsPage(PageParameters parameters)
	{
		super(parameters);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		final List<Article> articleList = new ArrayList<>(ServiceRegistry.get(ArticleService.class).listAll());
		final IDataProvider<Article> dataProvider = new ListDataProvider<Article>(articleList);
		final DataView<Article> articles = new DataView<Article>("articles", dataProvider)
		{
			@Override
			protected void populateItem(Item<Article> item)
			{
				item.add(new Label("name", item.getModelObject().getName()));
				item.add(new Label("description", item.getModelObject().getDescription()));
				item.add(new Label("price", item.getModelObject().getPrice()));
				final AttributeAppender srcAppender = new AttributeAppender("src", item.getModelObject().getImageUrl());
				item.add(new WebMarkupContainer("image").add(srcAppender));
			}
		};
		articles.setItemsPerPage(5);
		final PagingNavigation navigator = new PagingNavigation("navigator", articles);
		add(articles);
		add(navigator);
	}
}
