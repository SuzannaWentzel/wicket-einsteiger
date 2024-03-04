package nl.suzannawentzel.wicketcompact.resources;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.PackageResourceReference;

import java.util.List;

public class DefaultThemeResourceReference extends PackageResourceReference
{
	public static final DefaultThemeResourceReference INSTANCE = new DefaultThemeResourceReference();

	public DefaultThemeResourceReference() {
		super(DefaultThemeResourceReference.class, "style.css");
	}

	public static DefaultThemeResourceReference get() {
		return INSTANCE;
	}
}
