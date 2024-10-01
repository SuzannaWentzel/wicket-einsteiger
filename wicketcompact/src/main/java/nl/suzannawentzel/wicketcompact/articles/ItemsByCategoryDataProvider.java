package nl.suzannawentzel.wicketcompact.articles;

import nl.suzannawentzel.wicketcompact.entities.Article;
import nl.suzannawentzel.wicketcompact.entities.Category;
import nl.suzannawentzel.wicketcompact.models.EntityModel;
import nl.suzannawentzel.wicketcompact.services.ArticleService;
import nl.suzannawentzel.wicketcompact.services.CategoryService;
import nl.suzannawentzel.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import java.util.Iterator;

public class ItemsByCategoryDataProvider extends SortableDataProvider<Article, Void>
{
	private final EntityModel<Category, CategoryService> categoryModel;

	public ItemsByCategoryDataProvider(final Category category) {
		this.categoryModel = new EntityModel<>(category, CategoryService.class);
	}

	@Override
	public Iterator<? extends Article> iterator(long first, long count) {
		return ServiceRegistry.get(ArticleService.class).listValidByCategory(categoryModel.getObject()).iterator();
	}

	@Override
	public long size()
	{
		return ServiceRegistry.get(ArticleService.class).listAll().size();
	}

	@Override
	public IModel<Article> model(Article article) {
		return new CompoundPropertyModel<>(new EntityModel<>(article, ArticleService.class));
	}
}
