package nl.suzannawentzel.wicketcompact;

import nl.suzannawentzel.wicketcompact.entities.Table;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class TablesPage extends BaseEntitiesPage
{
	private final DataView<Table> tables;

	public TablesPage(PageParameters parameters)
	{
		super(parameters);
		IDataProvider<Table> dataProvider = new TablesDataProvider();
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
