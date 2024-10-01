package nl.suzannawentzel.wicketcompact.services;

import nl.suzannawentzel.wicketcompact.entities.Article;
import nl.suzannawentzel.wicketcompact.entities.Category;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleService extends BaseService<Article> {

	public ArticleService() {
		final CategoryService categoryService = ServiceRegistry.get(CategoryService.class);

		final Article cappuccino = new Article(categoryService.get(1L), "Cappuccino", "https://images.freeimages.com/images/large-previews/a79/cappuccino-1497220.jpg",
			new BigDecimal("3.20"), "Italian coffee specialty");
		final Article coffee = new Article(categoryService.get(1L), "Coffee", "https://images.freeimages.com/images/large-previews/a79/cappuccino-1497220.jpg",
			new BigDecimal("3.20"), "Just the regular coffee");
		final Article latte = new Article(categoryService.get(1L), "Latte", "https://images.freeimages.com/images/large-previews/a79/cappuccino-1497220.jpg",
			new BigDecimal("3.20"), "Coffee with a lot of creamed milk");
		final Article mintTea = new Article(categoryService.get(2L), "Fresh mint tea", "https://images.freeimages.com/images/large-previews/a79/cappuccino-1497220.jpg",
			new BigDecimal("3.20"), "Nice fresh tea");
		final Article gingerTea = new Article(categoryService.get(2L), "Fresh ginger tea", "https://images.freeimages.com/images/large-previews/a79/cappuccino-1497220.jpg",
			new BigDecimal("3.20"), "Nice spicy tea");
		final Article rooibosTea = new Article(categoryService.get(2L), "Rooibos tea", "https://images.freeimages.com/images/large-previews/a79/cappuccino-1497220.jpg",
			new BigDecimal("3.20"), "Nice decaf tea");
		final Article orangeJuice = new Article(categoryService.get(3L), "J'us de orange", "https://images.freeimages.com/images/large-previews/a79/cappuccino-1497220.jpg",
			new BigDecimal("3.20"), "Fresh sour juice");
		final Article strawberryMilkshake = new Article(categoryService.get(4L), "Strawberry milkshake", "https://images.freeimages.com/images/large-previews/a79/cappuccino-1497220.jpg",
			new BigDecimal("3.20"), "Nice creamy milkshake");
		final Article bananaMilkshake = new Article(categoryService.get(4L), "Banana milkshake", "https://images.freeimages.com/images/large-previews/a79/cappuccino-1497220.jpg",
			new BigDecimal("3.20"), "Nice creamy milkshake");
		final Article chocolateMilkshake = new Article(categoryService.get(4L), "Chocolate milkshake", "https://images.freeimages.com/images/large-previews/a79/cappuccino-1497220.jpg",
			new BigDecimal("3.20"), "Nice creamy milkshake");
		final Article sandwich = new Article(categoryService.get(5L), "Club Sandwich", "https://images.freeimages.com/images/large-previews/a79/cappuccino-1497220.jpg",
			new BigDecimal("3.20"), "Super extensive sandwich with a lot of different stufs");
		final Article scones = new Article(categoryService.get(5L), "Scones", "https://images.freeimages.com/images/large-previews/a79/cappuccino-1497220.jpg",
			new BigDecimal("3.20"), "Scones with clotted cream and marmelade");
		final Article bitterballen = new Article(categoryService.get(6L), "Bitterballen", "https://images.freeimages.com/images/large-previews/a79/cappuccino-1497220.jpg",
			new BigDecimal("3.20"), "6 crispy bitterballen with mustard");
		final Article vegaBurger = new Article(categoryService.get(7L), "Vegetarian burger", "https://images.freeimages.com/images/large-previews/a79/cappuccino-1497220.jpg",
			new BigDecimal("3.20"), "Nutty burger on an artisan bread with fries, salad and sauces");

		save(cappuccino);
		save(coffee);
		save(latte);
		save(mintTea);
		save(gingerTea);
		save(rooibosTea);
		save(orangeJuice);
		save(strawberryMilkshake);
		save(bananaMilkshake);
		save(chocolateMilkshake);
		save(sandwich);
		save(scones);
		save(bitterballen);
		save(vegaBurger);
	}

	public List<Article> listByCategory(final Category category)
	{
		return listAll().stream().filter(item -> item.getCategory().equals(category)).collect(
			Collectors.toList());
	}

	public List<Article> listValidByCategory(final Category category) {
		return listAll().stream().filter(item -> item.getCategory().equals(category) && isValid(item)).collect(Collectors.toList());
	}

	public Boolean isValid(Article article) {
		return isAfterOrEqual(LocalDate.now(), article.getValidFrom()) && LocalDate.now().isBefore(article.getValidTo());
	}

	public Boolean isAfterOrEqual(LocalDate date1, LocalDate date2)
	{
		return date1.isAfter(date2) || date1.equals(date2);
	}
}
