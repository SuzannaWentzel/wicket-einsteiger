package nl.suzannawentzel.wicketcompact.resources;

import org.apache.wicket.request.resource.PackageResourceReference;

public class JQueryUiCssResourceReference extends PackageResourceReference
{
	private static final JQueryUiCssResourceReference INSTANCE = new JQueryUiCssResourceReference();

	public JQueryUiCssResourceReference() { super(JQueryUiCssResourceReference.class, "jquery-ui.css"); }

	public static JQueryUiCssResourceReference get() { return INSTANCE; }
}
