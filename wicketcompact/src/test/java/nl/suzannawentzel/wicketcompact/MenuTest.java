package nl.suzannawentzel.wicketcompact;

import nl.suzannawentzel.wicketcompact.articles.ItemsPage;
import nl.suzannawentzel.wicketcompact.categories.CategoriesPage;
import nl.suzannawentzel.wicketcompact.dashboard.HomePage;
import nl.suzannawentzel.wicketcompact.tables.TablesPage;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

public class MenuTest
{
	private WicketTester tester;

	@Before
	public void setUp() {
		this.tester = new WicketTester(new WicketApplication() {
			@Override
			protected void setupRequestCycleListeners() {
				// do nothing
			}
		});
	}

	@Test
	public void navigation() {
		tester.startPage(HomePage.class);
		tester.assertRenderedPage(HomePage.class);

		tester.clickLink("header:navbar:categories");
		tester.assertRenderedPage(CategoriesPage.class);

		tester.clickLink("header:navbar:items");
		tester.assertRenderedPage(ItemsPage.class);

		tester.clickLink("header:navbar:tables");
		tester.assertRenderedPage(TablesPage.class);
	}
}
