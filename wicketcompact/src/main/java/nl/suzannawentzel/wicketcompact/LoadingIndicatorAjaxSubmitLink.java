package nl.suzannawentzel.wicketcompact;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.IAjaxIndicatorAware;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.AjaxIndicatorAppender;
import org.apache.wicket.markup.html.form.validation.IFormValidator;

public class LoadingIndicatorAjaxSubmitLink extends AjaxSubmitLink implements IAjaxIndicatorAware
{
	private final AjaxIndicatorAppender ajaxIndicatorAppender;

	public LoadingIndicatorAjaxSubmitLink(String id)
	{
		super(id);
		this.ajaxIndicatorAppender = new AjaxIndicatorAppender();
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(ajaxIndicatorAppender);
	}

	@Override
	public String getAjaxIndicatorMarkupId()
	{
		return ajaxIndicatorAppender.getMarkupId();
	}
}
