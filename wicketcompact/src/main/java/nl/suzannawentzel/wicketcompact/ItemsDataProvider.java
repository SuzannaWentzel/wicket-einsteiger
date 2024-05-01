package nl.suzannawentzel.wicketcompact;

import nl.suzannawentzel.wicketcompact.entities.Article;
import nl.suzannawentzel.wicketcompact.models.EntityModel;
import nl.suzannawentzel.wicketcompact.services.ArticleService;
import nl.suzannawentzel.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;

import org.apache.wicket.model.IModel;

import java.util.Iterator;

public class ItemsDataProvider extends SortableDataProvider<Article, String>
{
	public ItemsDataProvider() {
		setSort("name", SortOrder.ASCENDING);
	}

	@Override
	public Iterator<? extends Article> iterator(long first, long count)
	{
		return ServiceRegistry.get(ArticleService.class).listAll().iterator();
	}

	@Override
	public long size()
	{
		return ServiceRegistry.get(ArticleService.class).listAll().size();
	}

	@Override
	public IModel<Article> model(Article object)
	{
		return new EntityModel<Article, ArticleService>(object.getId(), ArticleService.class);
	}
}
