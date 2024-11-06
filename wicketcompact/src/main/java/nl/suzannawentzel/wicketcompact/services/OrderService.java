package nl.suzannawentzel.wicketcompact.services;

import nl.suzannawentzel.wicketcompact.entities.Article;
import nl.suzannawentzel.wicketcompact.entities.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderService extends BaseService<Order>
{
	public BigDecimal salesFor(final Article article) {
		return listAll().stream().filter(order -> order.getArticle().equals(article)).map(order -> order.getArticle().getPrice().multiply(new BigDecimal(order.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public List<Order> listByDateDesc() {
		final ArrayList<Order> orders = new ArrayList<>(listAll());
		orders.sort(Comparator.comparing(Order::getCreatedAt));
		return orders;
	}
}
