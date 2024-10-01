package nl.suzannawentzel.wicketcompact.categories;

import nl.suzannawentzel.wicketcompact.articles.ArticleCard;
import nl.suzannawentzel.wicketcompact.articles.ItemsByCategoryDataProvider;
import nl.suzannawentzel.wicketcompact.entities.Article;
import nl.suzannawentzel.wicketcompact.entities.Category;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;

public class CategoryPanel extends Panel
{
	private final Label categoryName = new Label("name");

	private final DataView<Article> articlesDataView;

	public CategoryPanel(final String id, final Category category) {
		super(id);

		articlesDataView = new DataView<Article>("items", new ItemsByCategoryDataProvider(category))
		{
			@Override
			protected void populateItem(Item<Article> item)
			{
				item.add(new ArticleCard("itemCard", item.getModelObject()));
				item.setRenderBodyOnly(true);
			}
		};
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(categoryName);
		add(this.articlesDataView);
	}
}
