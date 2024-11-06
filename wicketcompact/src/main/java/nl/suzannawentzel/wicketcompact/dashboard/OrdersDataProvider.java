package nl.suzannawentzel.wicketcompact.dashboard;

import nl.suzannawentzel.wicketcompact.entities.Order;
import nl.suzannawentzel.wicketcompact.models.EntityModel;
import nl.suzannawentzel.wicketcompact.services.ArticleService;
import nl.suzannawentzel.wicketcompact.services.OrderService;
import nl.suzannawentzel.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class OrdersDataProvider extends SortableDataProvider<Order, Void>
{
	@Override
	public Iterator<? extends Order> iterator(long first, long count)
	{
		return ServiceRegistry.get(OrderService.class).listByDateDesc().iterator();
	}

	@Override
	public long size()
	{
		return ServiceRegistry.get(OrderService.class).listByDateDesc().size();
	}

	@Override
	public IModel<Order> model(Order order)
	{
		return new CompoundPropertyModel<>(new EntityModel<>(order, OrderService.class));
	}
}
