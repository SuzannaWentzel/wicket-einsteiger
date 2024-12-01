package nl.suzannawentzel.wicketcompact.customers;

import nl.suzannawentzel.wicketcompact.LoadingIndicatorAjaxSubmitLink;
import nl.suzannawentzel.wicketcompact.entities.Article;
import nl.suzannawentzel.wicketcompact.entities.Order;
import nl.suzannawentzel.wicketcompact.entities.OrderStatus;
import nl.suzannawentzel.wicketcompact.entities.Table;
import nl.suzannawentzel.wicketcompact.models.EntityModel;
import nl.suzannawentzel.wicketcompact.services.ArticleService;
import nl.suzannawentzel.wicketcompact.services.OrderService;
import nl.suzannawentzel.wicketcompact.services.ServiceRegistry;
import nl.suzannawentzel.wicketcompact.services.TableService;
import nl.suzannawentzel.wicketcompact.tables.ModifyTablePage;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.math.BigDecimal;

public class ArticleCard extends Panel
{
	private final Label articleName = new Label("name");
	private final Label description = new Label("description");
	private final Label price = new Label("price");
	private final WebMarkupContainer image = new WebMarkupContainer("image");
	private final IModel<Article> articleModel;
	private NumberTextField<Integer> amountInput;

	private final LoadingIndicatorAjaxSubmitLink orderLink;
	private final Form orderForm = new Form("orderForm");
	private final WebMarkupContainer orderConfirmation;

	public ArticleCard(String id, Article article) {
		super(id);
		this.articleModel = new EntityModel<>(article, ArticleService.class);
		setDefaultModel(this.articleModel);

		this.orderLink = new LoadingIndicatorAjaxSubmitLink("orderBtn")
		{
			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				super.onSubmit(target);
				placeOrder(target, amountInput.getModelObject());
			}
		};

		orderConfirmation = new WebMarkupContainer("orderConfirmation");
		orderConfirmation.setOutputMarkupPlaceholderTag(true);
	}

	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		add(articleName);
		add(description);
		add(price);
		image.add(new AttributeModifier("src", new Model<String>()
		{
			@Override
			public String getObject()
			{
				return articleModel.getObject().getImageUrl();
			}
		}));
		add(image);

		add(orderForm);
		orderForm.add(orderLink);
		amountInput = new NumberTextField<>("amount", Model.of(), Integer.class);
		orderForm.add(amountInput);

		add(orderConfirmation);
		orderConfirmation.setVisible(false);
	}

	private void placeOrder(AjaxRequestTarget target, Integer quantity) {
		ServiceRegistry.get(OrderService.class).save(new Order(getTable(), this.articleModel.getObject(), quantity));
		orderConfirmation.setVisible(true);
		target.add(this.orderConfirmation);
		target.appendJavaScript(String.format("$('#%s').show; window.setTimeout(() => { $('#%s').hide(600); }, 3000);", orderConfirmation.getMarkupId(), orderConfirmation.getMarkupId()));
	}

	private Table getTable()
	{
		return findParent(Menu.class).getTable();
	}

}
