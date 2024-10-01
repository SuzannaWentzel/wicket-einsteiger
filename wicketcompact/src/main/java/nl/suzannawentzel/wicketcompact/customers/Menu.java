package nl.suzannawentzel.wicketcompact.customers;

import nl.suzannawentzel.wicketcompact.categories.CategoriesDataProvider;
import nl.suzannawentzel.wicketcompact.categories.CategoryPanel;
import nl.suzannawentzel.wicketcompact.entities.Category;
import nl.suzannawentzel.wicketcompact.entities.Table;
import nl.suzannawentzel.wicketcompact.resources.BootstrapCssResourceReference;
import nl.suzannawentzel.wicketcompact.resources.DefaultThemeResourceReference;
import nl.suzannawentzel.wicketcompact.services.ServiceRegistry;
import nl.suzannawentzel.wicketcompact.services.TableService;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;

public class Menu extends WebPage
{
	private final WebMarkupContainer tableNotSupportingOrderingOnlineHint;

	private final DataView<Category> categoryDataView;

	public Menu() {
		this.tableNotSupportingOrderingOnlineHint = new WebMarkupContainer("notElectronicallyOrderableMessage");
		this.categoryDataView = new DataView<Category>("categoriesList", new CategoriesDataProvider())
		{
			@Override
			protected void populateItem(Item<Category> item)
			{
				item.add(new CategoryPanel("categoryPanel", item.getModelObject()));
			}
		};
	}

	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		final Long tableId = getPageParameters().get("tableId").toLong();
		Table table = ServiceRegistry.get(TableService.class).get(tableId);
		add(new Label("tableName", table.getName()));
		this.tableNotSupportingOrderingOnlineHint.setVisible(!table.getOrderableElectronically());
		add(this.tableNotSupportingOrderingOnlineHint);
		add(this.categoryDataView);
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(CssHeaderItem.forReference(BootstrapCssResourceReference.get()));
		response.render(CssHeaderItem.forReference(DefaultThemeResourceReference.get()));
	}
}
