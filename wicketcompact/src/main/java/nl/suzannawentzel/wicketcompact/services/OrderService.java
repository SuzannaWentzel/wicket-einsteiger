package nl.suzannawentzel.wicketcompact.services;

import nl.suzannawentzel.wicketcompact.entities.Article;
import nl.suzannawentzel.wicketcompact.entities.Order;

import java.math.BigDecimal;

public class OrderService extends BaseService<Order>
{
	public BigDecimal salesFor(final Article article) {
		return listAll().stream().filter(order -> order.getArticle().equals(article)).map(order -> order.getArticle().getPrice().multiply(new BigDecimal(order.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}
