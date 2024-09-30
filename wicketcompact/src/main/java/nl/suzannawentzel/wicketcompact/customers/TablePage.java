package nl.suzannawentzel.wicketcompact.customers;

import nl.suzannawentzel.wicketcompact.entities.Table;
import nl.suzannawentzel.wicketcompact.services.ServiceRegistry;
import nl.suzannawentzel.wicketcompact.services.TableService;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class TablePage extends WebPage
{
	public TablePage(PageParameters parameters) {
		super(parameters);
	}

	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		final Long tableId = getPageParameters().get("id").to(Long.class);
		Table table = ServiceRegistry.get(TableService.class).get(tableId);

		add(new Label("tableName", table.getName()));

		Label notElectronicallyOrderableMessage = new Label("notElectronicallyOrderableMessage", Model.of("It is not possible to order electronically at this table at this time"));
		if (table.getOrderableElectronically()) {
			notElectronicallyOrderableMessage.setVisible(false);
			notElectronicallyOrderableMessage.setOutputMarkupPlaceholderTag(true);
		}

		add(notElectronicallyOrderableMessage);
	}
}
