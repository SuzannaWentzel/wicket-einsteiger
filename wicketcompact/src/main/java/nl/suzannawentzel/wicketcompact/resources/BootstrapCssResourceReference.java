package nl.suzannawentzel.wicketcompact.resources;

import org.apache.wicket.request.resource.PackageResourceReference;

public class BootstrapCssResourceReference extends PackageResourceReference
{
	public static final BootstrapCssResourceReference INSTANCE = new BootstrapCssResourceReference();

	public BootstrapCssResourceReference() {
		super(BootstrapCssResourceReference.class, "bootstrap.css");
	}

	public static BootstrapCssResourceReference get() {
		return INSTANCE;
	}
}
