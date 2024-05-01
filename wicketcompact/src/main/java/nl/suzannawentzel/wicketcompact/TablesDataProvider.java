package nl.suzannawentzel.wicketcompact;

import nl.suzannawentzel.wicketcompact.entities.Table;
import nl.suzannawentzel.wicketcompact.models.EntityModel;
import nl.suzannawentzel.wicketcompact.services.ServiceRegistry;
import nl.suzannawentzel.wicketcompact.services.TableService;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;

import java.util.Iterator;

public class TablesDataProvider extends SortableDataProvider<Table, String>
{
	@Override
	public Iterator<? extends Table> iterator(long first, long count)
	{
		return ServiceRegistry.get(TableService.class).listAll().iterator();
	}

	@Override
	public long size()
	{
		return ServiceRegistry.get(TableService.class).listAll().size();
	}

	@Override
	public IModel<Table> model(Table object)
	{
		return new EntityModel<Table, TableService>(object.getId(), TableService.class);
	}
}
