package nl.suzannawentzel.wicketcompact.categories;

import nl.suzannawentzel.wicketcompact.BaseWebPage;
import nl.suzannawentzel.wicketcompact.entities.Category;
import nl.suzannawentzel.wicketcompact.services.CategoryService;
import nl.suzannawentzel.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class CreateCategoryPage extends BaseWebPage
{
	public CreateCategoryPage(PageParameters parameters)
	{
		super(parameters);
	}

	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		final EditCategory editCategoryPanel = new EditCategory("newCategory");
		editCategoryPanel.setCategory(new Category());
		add(editCategoryPanel);
	}
}
