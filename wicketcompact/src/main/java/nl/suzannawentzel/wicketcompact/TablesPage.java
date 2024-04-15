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
import org.apache.wicket.markup.html.navigation.paging.IPageable;
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
	private final DataView<Table> tables;

	public TablesPage(PageParameters parameters)
	{
		super(parameters);
		final List<Table>
			tableList = new ArrayList<>(ServiceRegistry.get(TableService.class).listAll());
		final IDataProvider<Table> dataProvider = new ListDataProvider<Table>(tableList);
		this.tables = new DataView<Table>("tables", dataProvider)
		{
			@Override
			protected void populateItem(Item<Table> item)
			{
				Table table = item.getModelObject();
				item.add(new Label("name",table.getName()));
				item.add(new Label("seats", table.getSeatCount()));
				item.add(new Label("orderableElectronically", table.getOrderableElectronically()));
			}
		};
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(this.tables);
	}

	@Override
	protected IPageable getPageable() {
		return this.tables;
	}
}
