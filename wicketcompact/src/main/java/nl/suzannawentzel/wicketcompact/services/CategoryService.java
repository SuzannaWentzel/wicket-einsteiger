package nl.suzannawentzel.wicketcompact.services;

import nl.suzannawentzel.wicketcompact.entities.Category;

public class CategoryService extends BaseService<Category>
{
	public CategoryService() {
		final Category coffee = new Category("Coffee", "https://images.pexels.com/photos/302899/pexels-photo-302899.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2");
		final Category tea = new Category("Tea", "https://images.pexels.com/photos/1493080/pexels-photo-1493080.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2");
		final Category juices = new Category("Juices", "https://images.pexels.com/photos/1233319/pexels-photo-1233319.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2");
		final Category milkshakes = new Category("Milkshakes", "https://images.pexels.com/photos/3727250/pexels-photo-3727250.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2");

		save(coffee);
		save(tea);
		save(juices);
		save(milkshakes);
	}
}
