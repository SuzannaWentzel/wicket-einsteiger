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
import org.apache.wicket.model.CompoundPropertyModel;

public class LoginPage extends WebPage
{
	private static final String USERNAME = "user";
	private static final String PASSWORD = "veryStrongPassword";

	private TextField<String> usernameField;
	private PasswordTextField passwordField;

	private FeedbackPanel loginFeedback;

	private final Form<Credentials> form = new Form<Credentials>("loginForm", CompoundPropertyModel.of(new Credentials()))
	{
		@Override
		protected void onSubmit()
		{
			super.onSubmit();

			if (!isValid(form.getModelObject())) {
				error("Credentials invalid");
			} else {
				setResponsePage(LoginPage.class);
			}
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
		loginFeedback = new SgFeedbackPanel("loginFeedback");
		add(loginFeedback);
		add(form);
		usernameField = new TextField<String>("username");
		passwordField = new PasswordTextField("password");
		form.add(usernameField);
		form.add(passwordField);
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(CssHeaderItem.forReference(BootstrapCssResourceReference.get()));
		response.render(CssHeaderItem.forReference(DefaultThemeResourceReference.get()));
	}

	public boolean isValid(Credentials credentials)
	{
		return credentials.getUsername().equals(USERNAME) && credentials.getPassword().equals(PASSWORD);
	}
}
