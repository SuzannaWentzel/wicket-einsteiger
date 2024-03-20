package nl.suzannawentzel.wicketcompact;

import nl.suzannawentzel.wicketcompact.entities.Article;
import nl.suzannawentzel.wicketcompact.entities.Table;
import nl.suzannawentzel.wicketcompact.services.ArticleService;
import nl.suzannawentzel.wicketcompact.services.ServiceRegistry;
import nl.suzannawentzel.wicketcompact.services.TableService;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigation;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.ArrayList;
import java.util.List;

public class TablesPage extends BaseEntitiesPage
{
	public TablesPage(PageParameters parameters)
	{
		super(parameters);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		final List<Table>
			tableList = new ArrayList<>(ServiceRegistry.get(TableService.class).listAll());
		final IDataProvider<Table> dataProvider = new ListDataProvider<Table>(tableList);
		final DataView<Table> tables = new DataView<Table>("tables", dataProvider)
		{
			@Override
			protected void populateItem(Item<Table> item)
			{
				item.add(new Label("name", item.getModelObject().getName()));
				item.add(new Label("seats", item.getModelObject().getSeatCount()));
			}
		};
		tables.setItemsPerPage(8);
		final PagingNavigation navigator = new PagingNavigation("navigator", tables);
		add(tables);
		add(navigator);

	}
}
