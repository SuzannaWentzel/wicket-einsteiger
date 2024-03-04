package nl.suzannawentzel.wicketcompact;

import nl.suzannawentzel.wicketcompact.resources.BootstrapCssResourceReference;
import nl.suzannawentzel.wicketcompact.resources.DefaultThemeResourceReference;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class BaseWebPage extends WebPage {
	private Tenant tenant;

	public BaseWebPage(PageParameters parameters) {
		super(parameters);
		add(new Header("header").setRenderBodyOnly(true));
		add(new Footer("footer").setRenderBodyOnly(true));
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		this.tenant = Tenant.get();
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(CssHeaderItem.forReference(BootstrapCssResourceReference.get()));
		response.render(CssHeaderItem.forReference(DefaultThemeResourceReference.get()));
	}
}
