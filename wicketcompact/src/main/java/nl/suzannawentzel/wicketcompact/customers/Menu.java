package nl.suzannawentzel.wicketcompact.customers;

import nl.suzannawentzel.wicketcompact.articles.ArticleCard;
import nl.suzannawentzel.wicketcompact.entities.Article;
import nl.suzannawentzel.wicketcompact.entities.Category;
import nl.suzannawentzel.wicketcompact.entities.Table;
import nl.suzannawentzel.wicketcompact.models.EntityModel;
import nl.suzannawentzel.wicketcompact.resources.BootstrapCssResourceReference;
import nl.suzannawentzel.wicketcompact.resources.DefaultThemeResourceReference;
import nl.suzannawentzel.wicketcompact.services.ArticleService;
import nl.suzannawentzel.wicketcompact.services.CategoryService;
import nl.suzannawentzel.wicketcompact.services.ServiceRegistry;
import nl.suzannawentzel.wicketcompact.services.TableService;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Menu extends WebPage
{
	private final WebMarkupContainer tableNotSupportingOrderingOnlineHint;

	public Menu() {
		this.tableNotSupportingOrderingOnlineHint = new WebMarkupContainer("notElectronicallyOrderableMessage");
	}

	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		final Long tableId = getPageParameters().get("tableId").toLong();
		Table table = ServiceRegistry.get(TableService.class).get(tableId);
		add(new Label("tableName", table.getName()));
		this.tableNotSupportingOrderingOnlineHint.setVisible(!table.getOrderableElectronically());
		add(this.tableNotSupportingOrderingOnlineHint);

		addMenu();
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(CssHeaderItem.forReference(BootstrapCssResourceReference.get()));
		response.render(CssHeaderItem.forReference(DefaultThemeResourceReference.get()));
	}

	private void addMenu() {
		List<Category> categories =
			new ArrayList<>(ServiceRegistry.get(CategoryService.class).listAll());

		final ListView<Category>
			categoryListView = new ListView<Category>("categoriesList", categories)
		{
			@Override
			protected void populateItem(ListItem<Category> listItem)
			{
				listItem.add(new Label("categoryName", listItem.getModelObject().getName()));

				List<Article> articles = new ArrayList<>(ServiceRegistry.get(ArticleService.class).listByCategoryFilterValid(listItem.getModelObject()));
				final ListView<Article> articleListView = new ListView<Article>("itemList", articles) {
					@Override
					protected void populateItem(ListItem<Article> listItemArticle) {
						ArticleCard articleCard = new ArticleCard("itemCard", listItemArticle.getModel());
						listItemArticle.add(articleCard);
					}
				};

				listItem.add(articleListView);
			}
		};
		add(categoryListView);
	}
}
