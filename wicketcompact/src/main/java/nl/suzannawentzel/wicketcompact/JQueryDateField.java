package nl.suzannawentzel.wicketcompact;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTextField;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.settings.JavaScriptLibrarySettings;

import java.time.LocalDate;

public class JQueryDateField extends LocalDateTextField
{
	private static final long serialVersionUID = 1L;
	private final String datePattern;
	private final String countryIsoCode;
	private CharSequence urlForIcon;
	private static final PackageResourceReference JQDatePickerRef =
		new PackageResourceReference(JQueryDateField.class, "resources/JQDatePicker.js");

	public JQueryDateField(String id, IModel<LocalDate> dateModel,
		String datePattern, String countryIsoCode){
		super(id, dateModel, datePattern);
		this.datePattern = datePattern;
		this.countryIsoCode = countryIsoCode;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		setOutputMarkupId(true);

		PackageResourceReference resourceReference = new PackageResourceReference(getClass(), "calendar-solid.svg");

		urlForIcon = urlFor(resourceReference, new PageParameters());

		add(AttributeModifier.replace("size", "12"));
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);

		//if component is disabled we don't have to load the JQueryUI datepicker
		if(!isEnabledInHierarchy())
			return;
		//add bundled JQuery
		JavaScriptLibrarySettings javaScriptSettings =
			getApplication().getJavaScriptLibrarySettings();
		response.render(JavaScriptHeaderItem.
			forReference(javaScriptSettings.getJQueryReference()));
		//add package resources
		response.render(JavaScriptHeaderItem.
			forReference(new PackageResourceReference(getClass(), "resources/jquery-ui.min.js")));
		response.render(CssHeaderItem.
			forReference(new PackageResourceReference(getClass(), "resources/jquery-ui.css")));
		//add custom file JQDatePicker.js. Reference JQDatePickerRef is a static field
		response.render(JavaScriptHeaderItem.forReference(JQDatePickerRef));

		//add the init script for datepicker
		String jqueryDateFormat = datePattern.replace("yyyy", "yy").toLowerCase();
		String initScript = ";initJQDatepicker('" + getMarkupId() + "', '" + countryIsoCode +
			"', '" + jqueryDateFormat + "', " + "'" + urlForIcon +"');";
		response.render(OnLoadHeaderItem.forScript(initScript));
	}
}
