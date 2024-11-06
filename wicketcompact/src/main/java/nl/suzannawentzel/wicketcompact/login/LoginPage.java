package nl.suzannawentzel.wicketcompact.login;

import nl.suzannawentzel.wicketcompact.LoadingIndicatorAjaxSubmitLink;
import nl.suzannawentzel.wicketcompact.SgFeedbackPanel;
import nl.suzannawentzel.wicketcompact.resources.BootstrapCssResourceReference;
import nl.suzannawentzel.wicketcompact.resources.DefaultThemeResourceReference;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxCallListener;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class LoginPage extends WebPage
{
	private TextField<String> usernameField;
	private PasswordTextField passwordField;

	private FeedbackPanel loginFeedback;

	private final Form<Credentials> form = new Form<Credentials>("loginForm")
	{
		@Override
		protected void onSubmit()
		{
			super.onSubmit();
			setResponsePage(LoginPage.class);
		}
	};

	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		initializeForm();
	}

	private void initializeForm()
	{
		form.setModel(new Credentials());
		loginFeedback = new SgFeedbackPanel("loginFeedback");
		add(loginFeedback);
		add(form);
		usernameField = new TextField<String>("username");
		passwordField = new PasswordTextField("password");
		form.add(usernameField);
		form.add(passwordField);
		form.add(new LoadingIndicatorAjaxSubmitLink("btnLogin") {

			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				super.onSubmit(target);
				target.add(LoginPage.this.loginFeedback);
			}

			@Override
			protected void onError(AjaxRequestTarget target) {
				super.onSubmit(target);
				target.add(LoginPage.this.loginFeedback);
			}

			@Override
			protected void updateAjaxAttributes(AjaxRequestAttributes attributes)
			{
				super.updateAjaxAttributes(attributes);
				attributes.getAjaxCallListeners().add(new AjaxCallListener() {
					@Override
					public CharSequence getBeforeSendHandler(Component component) {
						return String.format("$('#%s').attr('disabled', 'disabled');", getMarkupId());
					}

					@Override
					public CharSequence getCompleteHandler(Component component)
					{
						return String.format("$('#%s').removeAttr('disabled');", getMarkupId());
					}
				});
			}
		});
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(CssHeaderItem.forReference(BootstrapCssResourceReference.get()));
		response.render(CssHeaderItem.forReference(DefaultThemeResourceReference.get()));
	}
}
