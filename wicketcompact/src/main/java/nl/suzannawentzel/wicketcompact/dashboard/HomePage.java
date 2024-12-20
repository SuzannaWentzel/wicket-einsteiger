package nl.suzannawentzel.wicketcompact.dashboard;

import nl.suzannawentzel.wicketcompact.BaseWebPage;
import nl.suzannawentzel.wicketcompact.DashboardTreeProvider;
import nl.suzannawentzel.wicketcompact.entities.Order;
import nl.suzannawentzel.wicketcompact.entities.OrderStatus;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.markup.html.repeater.tree.NestedTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.content.Folder;
import org.apache.wicket.extensions.markup.html.repeater.tree.theme.WindowsTheme;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.time.Duration;

import javax.swing.tree.DefaultMutableTreeNode;

public class HomePage extends BaseWebPage
{
	private static final long serialVersionUID = 1L;

	private final NestedTree<DefaultMutableTreeNode> dashboardTree;

	private final DataView<Order> orders;

	private final WebMarkupContainer ordersParent;

	public HomePage(final PageParameters parameters) {
		super(parameters);
		this.dashboardTree = new NestedTree<DefaultMutableTreeNode>("dashboardTree", new DashboardTreeProvider())
		{
			@Override
			protected Component newContentComponent(String id, IModel<DefaultMutableTreeNode> model)
			{
				return new Folder<>(id, this, model);
			}
		};

		this.dashboardTree.add(new WindowsTheme());

		this.ordersParent = new WebMarkupContainer("ordersParent");
		orders = new DataView<Order>("orders", new OrdersDataProvider())
		{
			@Override
			protected void populateItem(Item<Order> item)
			{
				item.setModel(new CompoundPropertyModel<>(item.getModel()));
				item.add(new Label("quantity"));
				item.add(new Label("article.name"));
				item.add(new Label("table.name"));
				item.add(new Label("createdAt"));
				final Label statusLabel = new Label("status.text");
				item.add(statusLabel);

				final Order order = item.getModelObject();
				statusLabel.add(new AttributeAppender("class", () -> {
					switch (order.getStatus()) {
						case NEW:
							return " badge-info";
						case PREPARATION:
							return " badge-warning";
						case DONE:
							return " badge-success";
						default:
							return "";
					}
				}));

				statusLabel.add(new AjaxEventBehavior("click")
				{
					@Override
					protected void onEvent(AjaxRequestTarget target)
					{
						final OrderStatus status = order.getStatus();
						if (status.equals(OrderStatus.NEW)) {
							order.setStatus(OrderStatus.PREPARATION);
						} else if (status.equals(OrderStatus.PREPARATION)) {
							order.setStatus(OrderStatus.DONE);
						}
						target.add(statusLabel);
					}
				});
				statusLabel.setOutputMarkupPlaceholderTag(true);
			}
		};
		this.ordersParent.setOutputMarkupPlaceholderTag(true);
		this.dashboardTree.setOutputMarkupPlaceholderTag(true);
	}

	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		add(this.dashboardTree);
		add(this.ordersParent);
		ordersParent.add(this.orders);

		add(new AbstractAjaxTimerBehavior(Duration.seconds(5))
		{
			@Override
			protected void onTimer(AjaxRequestTarget target)
			{
				target.add(ordersParent);
				target.add(dashboardTree);
			}
		});
	}
}
