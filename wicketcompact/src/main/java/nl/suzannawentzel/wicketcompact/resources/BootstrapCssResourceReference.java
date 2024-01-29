package nl.suzannawentzel.wicketcompact.resources;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.PackageResource;
import org.apache.wicket.request.resource.PackageResourceReference;

import java.util.List;

public class BootstrapCssResourceReference extends PackageResourceReference
{
	public static final BootstrapCssResourceReference INSTANCE = new BootstrapCssResourceReference();

	public BootstrapCssResourceReference() {
		super(BootstrapCssResourceReference.class, "bootstrap.css");
	}

	public static BootstrapCssResourceReference get() {
		return INSTANCE;
	}

	@Override
	public List<HeaderItem> getDependencies() {
		final List<HeaderItem> dependencies = super.getDependencies();
		dependencies.add(JavaScriptHeaderItem.forReference(BootstrapJsResourceReference.get()));
		return dependencies;
	}
}
