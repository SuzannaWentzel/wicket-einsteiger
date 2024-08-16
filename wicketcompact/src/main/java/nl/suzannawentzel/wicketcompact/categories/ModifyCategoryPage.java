package nl.suzannawentzel.wicketcompact.categories;

import nl.suzannawentzel.wicketcompact.BaseWebPage;
import nl.suzannawentzel.wicketcompact.services.CategoryService;
import nl.suzannawentzel.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class ModifyCategoryPage extends BaseWebPage
{
	public ModifyCategoryPage(PageParameters parameters)
	{
		super(parameters);
	}

	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		final EditCategory editCategoryPanel = new EditCategory("editCategory");
		final Long categoryId = getPageParameters().get("id").to(Long.class);
		editCategoryPanel.setCategory(ServiceRegistry.get(CategoryService.class).get(categoryId));
		add(editCategoryPanel);
	}
}
