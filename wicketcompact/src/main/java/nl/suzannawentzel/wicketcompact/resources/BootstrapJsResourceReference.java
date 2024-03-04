package nl.suzannawentzel.wicketcompact.resources;

import org.apache.wicket.Application;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

import java.util.List;

public class BootstrapJsResourceReference extends PackageResourceReference
{
	private static final BootstrapJsResourceReference INSTANCE = new BootstrapJsResourceReference();

	public BootstrapJsResourceReference() {
		super(BootstrapJsResourceReference.class, "bootstrap.js");
	}

	public static BootstrapJsResourceReference get() {
		return INSTANCE;
	}

	@Override
	public List<HeaderItem> getDependencies() {
		final List<HeaderItem> dependencies = super.getDependencies();
		final ResourceReference jQueryReference = Application.get().getJavaScriptLibrarySettings().getJQueryReference();
		dependencies.add(JavaScriptHeaderItem.forReference(jQueryReference));
		return dependencies;
	}
}
