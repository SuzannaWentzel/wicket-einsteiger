package nl.suzannawentzel.wicketcompact.services;

import nl.suzannawentzel.wicketcompact.entities.Category;

public class CategoryService extends BaseService<Category>
{
	public CategoryService() {
		final Category coffee = new Category("Coffee");
		final Category tea = new Category("Tea");
		final Category juices = new Category("Juices");
		final Category milkshakes = new Category("Milkshakes");

		save(coffee);
		save(tea);
		save(juices);
		save(milkshakes);
	}
}
