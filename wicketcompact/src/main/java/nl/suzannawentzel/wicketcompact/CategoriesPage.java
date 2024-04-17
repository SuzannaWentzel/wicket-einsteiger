package nl.suzannawentzel.wicketcompact;

import nl.suzannawentzel.wicketcompact.entities.Category;
import nl.suzannawentzel.wicketcompact.services.CategoryService;
import nl.suzannawentzel.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigation;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.ArrayList;
import java.util.List;

public class CategoriesPage extends BaseEntitiesPage
{
	private DataView<Category> categories;
	final SortableDataProvider<Category, String> dataProvider;
	public CategoriesPage(PageParameters parameters)
	{
		super(parameters);
		dataProvider = new CategoriesDataProvider();
		categories = new DataView<Category>("categories", dataProvider)
		{
			@Override
			protected void populateItem(Item<Category> listItem)
			{
				Category category = listItem.getModelObject();
				listItem.add(new Label("name", LambdaModel.of(category::getName)));

				final AttributeAppender srcAppender = new AttributeAppender("src", LambdaModel.of(category::getImageUrl));
				listItem.add(new WebMarkupContainer("image").add(srcAppender));
			}
		};
	}

	@Override
	protected IPageable getPageable() {
		return categories;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		categories.setItemsPerPage(3);
		add(categories);
		add(new OrderByBorder<>("orderByName", "name", this.dataProvider));
	}
}
