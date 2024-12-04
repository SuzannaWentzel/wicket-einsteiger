package nl.suzannawentzel.wicketcompact;

import nl.suzannawentzel.wicketcompact.articles.CreateArticlePage;
import nl.suzannawentzel.wicketcompact.articles.ItemsPage;
import org.apache.wicket.util.tester.FormTester;
import org.junit.Test;

public class CreateArticleTest extends BasePageWithoutLoginTest
{
	@Override
	public void setUp() {
		super.setUp();
		tester.startPage(CreateArticlePage.class);
		tester.assertRenderedPage(CreateArticlePage.class);
	}

	@Test
	public void createsArticle() {
		final FormTester formTester = tester.newFormTester("editArticle:form");
		formTester.setValue("name", "Water");
		formTester.select("category", 0);
		formTester.setValue("description", "Necessary.");
		formTester.setValue("price", "2,00");
		formTester.setValue("imageUrl", "http://example.com/image.png");
		formTester.submit();
		tester.assertRenderedPage(ItemsPage.class);
		tester.assertModelValue("articles:15:name", "Water");
	}
}
