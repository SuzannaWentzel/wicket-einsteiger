package nl.suzannawentzel.wicketcompact.articles;

import nl.suzannawentzel.wicketcompact.BaseWebPage;
import nl.suzannawentzel.wicketcompact.entities.Article;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class CreateArticlePage extends BaseWebPage
{
	public CreateArticlePage(PageParameters parameters)
	{
		super(parameters);
	}

	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		final EditArticle editArticlePanel = new EditArticle("editArticle");
		editArticlePanel.setArticle(new Article());
		add(editArticlePanel);
	}
}
