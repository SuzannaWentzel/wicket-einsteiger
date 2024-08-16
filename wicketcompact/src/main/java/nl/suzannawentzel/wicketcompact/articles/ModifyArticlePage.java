package nl.suzannawentzel.wicketcompact.articles;

import nl.suzannawentzel.wicketcompact.BaseWebPage;
import nl.suzannawentzel.wicketcompact.services.ArticleService;
import nl.suzannawentzel.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class ModifyArticlePage extends BaseWebPage
{
	public ModifyArticlePage(PageParameters parameters)
	{
		super(parameters);
	}

	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		final EditArticle editArticlePanel = new EditArticle("editArticle");
		final Long articleId = getPageParameters().get("id").to(Long.class);
		editArticlePanel.setArticle(ServiceRegistry.get(ArticleService.class).get(articleId));
		add(editArticlePanel);
	}
}
