package nl.suzannawentzel.wicketcompact;

import nl.suzannawentzel.wicketcompact.entities.Category;
import nl.suzannawentzel.wicketcompact.services.CategoryService;
import nl.suzannawentzel.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.model.IModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryListModel implements IModel<List<Category>>
{
	@Override
	public List<Category> getObject()
	{
		return new ArrayList<>(ServiceRegistry.get(CategoryService.class).listAll());
	}
}
