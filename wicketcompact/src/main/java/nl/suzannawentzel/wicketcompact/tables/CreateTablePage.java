package nl.suzannawentzel.wicketcompact.tables;

import nl.suzannawentzel.wicketcompact.BaseWebPage;
import nl.suzannawentzel.wicketcompact.entities.Table;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class CreateTablePage extends BaseWebPage
{

	public CreateTablePage(PageParameters parameters)
	{
		super(parameters);
	}

	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		final EditTable tableEditPanel = new EditTable("editTable");
		tableEditPanel.setTable(new Table());
		add(tableEditPanel);
	}
}
