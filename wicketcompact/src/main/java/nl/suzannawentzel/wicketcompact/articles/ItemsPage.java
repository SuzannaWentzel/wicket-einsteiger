package nl.suzannawentzel.wicketcompact.articles;

import nl.suzannawentzel.wicketcompact.BaseEntitiesPage;
import nl.suzannawentzel.wicketcompact.entities.Article;
import nl.suzannawentzel.wicketcompact.models.EntityModel;
import nl.suzannawentzel.wicketcompact.services.ArticleService;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class ItemsPage extends BaseEntitiesPage
{
	private final DataView<Article> articles;

	public ItemsPage(PageParameters parameters)
	{
		super(parameters);
		IDataProvider<Article> dataProvider = new ItemsDataProvider();
		this.articles = new DataView<Article>("articles", dataProvider)
		{
			@Override
			protected void populateItem(Item<Article> item)
			{
				final Article article = item.getModelObject();
				item.setModel(new CompoundPropertyModel<>(item.getModel()));
				item.add(new Label("name"));
				item.add(new Label("description"));
				item.add(new Label("price"));
				item.add(new Label("category.name"));
				item.add(new Label("validFrom"));
				item.add(new Label("validTo"));
				final AttributeAppender srcAppender = new AttributeAppender("src", new PropertyModel<>(new EntityModel<>(article, ArticleService.class), "imageUrl"));
				item.add(new WebMarkupContainer("image").add(srcAppender));
				item.add(new BookmarkablePageLink<>("editArticle", ModifyArticlePage.class, new PageParameters().add("id", item.getModelObject().getId())));
			}
		};

	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		articles.setItemsPerPage(20);
		add(articles);
	}

	@Override
	protected IPageable getPageable() {
		return this.articles;
	}
}
