package nl.suzannawentzel.wicketcompact.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class Order extends BaseEntity
{
	private Table table;

	private Article article;

	private Integer quantity;

	private OrderStatus status;

	private BigDecimal totalPrice;

	private LocalDateTime createdAt;

	public Order(Table table, Article article, Integer quantity) {
		this.table = table;
		this.article = article;
		this.quantity = quantity;
		this.status = OrderStatus.NEW;
		this.totalPrice = article.getPrice().multiply(BigDecimal.valueOf(quantity));
		this.createdAt = LocalDateTime.now();
	}

	public Table getTable()
	{
		return table;
	}

	public void setTable(Table table)
	{
		this.table = table;
	}

	public Article getArticle()
	{
		return article;
	}

	public void setArticle(Article article)
	{
		this.article = article;
	}

	public Integer getQuantity()
	{
		return quantity;
	}

	public void setQuantity(Integer quantity)
	{
		this.quantity = quantity;
	}

	public OrderStatus getStatus()
	{
		return status;
	}

	public void setStatus(OrderStatus status)
	{
		this.status = status;
	}

	public BigDecimal getTotalPrice()
	{
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice)
	{
		this.totalPrice = totalPrice;
	}

	public LocalDateTime getCreatedAt()
	{
		return createdAt;
	}
}
