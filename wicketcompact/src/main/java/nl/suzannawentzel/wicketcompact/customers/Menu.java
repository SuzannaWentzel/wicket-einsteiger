package nl.suzannawentzel.wicketcompact.customers;

import nl.suzannawentzel.wicketcompact.entities.Table;
import nl.suzannawentzel.wicketcompact.resources.BootstrapCssResourceReference;
import nl.suzannawentzel.wicketcompact.services.ServiceRegistry;
import nl.suzannawentzel.wicketcompact.services.TableService;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class Menu extends WebPage
{
	private final WebMarkupContainer tableNotSupportingOrderingOnlineHint;

	public Menu() {
		this.tableNotSupportingOrderingOnlineHint = new WebMarkupContainer("notElectronicallyOrderableMessage");
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
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(CssHeaderItem.forReference(BootstrapCssResourceReference.get()));
	}
}
