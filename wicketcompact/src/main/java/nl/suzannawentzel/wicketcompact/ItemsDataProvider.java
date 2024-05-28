package nl.suzannawentzel.wicketcompact;

import nl.suzannawentzel.wicketcompact.entities.Article;
import nl.suzannawentzel.wicketcompact.models.EntityModel;
import nl.suzannawentzel.wicketcompact.services.ArticleService;
import nl.suzannawentzel.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;

import org.apache.wicket.model.IModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ItemsDataProvider extends SortableDataProvider<Article, Void>
{
	@Override
	public Iterator<? extends Article> iterator(long first, long count)
	{
		final List<Article> allItems = new ArrayList<>(ServiceRegistry.get(
			ArticleService.class).listAll());

		final List<Article> subList = allItems.subList((int) first, (int) (first + count >= allItems.size() ? allItems.size() : first + count));
		return subList.iterator();
	}

	@Override
	public long size()
	{
		return ServiceRegistry.get(ArticleService.class).listAll().size();
	}

	@Override
	public IModel<Article> model(Article object)
	{
		return new EntityModel<>(object, ArticleService.class);
	}
}
