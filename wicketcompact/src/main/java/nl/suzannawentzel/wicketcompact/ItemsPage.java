package nl.suzannawentzel.wicketcompact;

import nl.suzannawentzel.wicketcompact.entities.Article;
import nl.suzannawentzel.wicketcompact.entities.Category;
import nl.suzannawentzel.wicketcompact.services.ArticleService;
import nl.suzannawentzel.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
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
	private final DataView<Article> articles;

	public ItemsPage(PageParameters parameters)
	{
		super(parameters);
		final List<Article> articleList = new ArrayList<>(ServiceRegistry.get(ArticleService.class).listAll());
		IDataProvider<Article> dataProvider = new ListDataProvider<Article>(articleList);
		this.articles = new DataView<Article>("articles", dataProvider)
		{
			@Override
			protected void populateItem(Item<Article> item)
			{
				final Article article = item.getModelObject();
				item.add(new Label("name", article.getName()));
				item.add(new Label("description", article.getDescription()));
				item.add(new Label("price", article.getPrice()));
				item.add(new Label("category", article.getCategory().getName()));
				item.add(new Label("validFrom", article.getValidFrom()));
				item.add(new Label("validTo", article.getValidTo()));
				final AttributeAppender srcAppender = new AttributeAppender("src", article.getImageUrl());
				item.add(new WebMarkupContainer("image").add(srcAppender));
			}
		};

	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		articles.setItemsPerPage(3);
		add(articles);
	}

	@Override
	protected IPageable getPageable() {
		return this.articles;
	}
}
