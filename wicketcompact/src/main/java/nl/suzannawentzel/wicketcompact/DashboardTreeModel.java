package nl.suzannawentzel.wicketcompact;

import nl.suzannawentzel.wicketcompact.entities.Category;
import nl.suzannawentzel.wicketcompact.services.ArticleService;
import nl.suzannawentzel.wicketcompact.services.CategoryService;
import nl.suzannawentzel.wicketcompact.services.OrderService;
import nl.suzannawentzel.wicketcompact.services.ServiceRegistry;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Locale;

public class DashboardTreeModel extends DefaultTreeModel
{
	public DashboardTreeModel() {
		super(null);
		setRoot(getTreeRoot());
	}

	private TreeNode getTreeRoot()
	{
		final DefaultMutableTreeNode root = new DefaultMutableTreeNode("Categories");
		final Collection<Category> categories = ServiceRegistry.get(CategoryService.class).listAll();
		final ArticleService articleService = ServiceRegistry.get(ArticleService.class);
		final OrderService orderService = ServiceRegistry.get(OrderService.class);
		categories.forEach(category -> {
			final DefaultMutableTreeNode categoryNode = new DefaultMutableTreeNode(category.getName());
			root.add(categoryNode);
			articleService.listByCategory(category).forEach(item -> {
				final BigDecimal salesForArticle = orderService.salesFor(item);
				final String salesForArticlesAsString = NumberFormat.getCurrencyInstance(Locale.FRANCE).format(salesForArticle);
				final String nodeDescription = String.format("%s (Profit %s)", item.getName(), salesForArticlesAsString);
				categoryNode.add(new DefaultMutableTreeNode(nodeDescription));
			});
		});
		return root;
	}
}
