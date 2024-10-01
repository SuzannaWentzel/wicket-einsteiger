package nl.suzannawentzel.wicketcompact.articles;

import nl.suzannawentzel.wicketcompact.entities.Article;
import nl.suzannawentzel.wicketcompact.models.EntityModel;
import nl.suzannawentzel.wicketcompact.services.ArticleService;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

public class ArticleCard extends Panel
{
	public ArticleCard(String id, IModel<Article> article) {
		super(id);
		add(new Label("itemName", article.getObject().getName()));
		add(new Label("itemDescription", article.getObject().getDescription()));
		add(new Label("itemPrice", article.getObject().getPrice()));

		final AttributeAppender
			srcAppender = new AttributeAppender("src", new PropertyModel<>(new EntityModel<>(article.getObject(), ArticleService.class), "imageUrl"));
		add(new WebMarkupContainer("itemImage").add(srcAppender));

	}
}
