package nl.suzannawentzel.wicketcompact.resources;

import org.apache.wicket.request.resource.PackageResourceReference;

public class CafeOneTheme extends PackageResourceReference
{
	public static final CafeOneTheme INSTANCE = new CafeOneTheme();

	public CafeOneTheme() { super(CafeOneTheme.class, "cafeOne.css");	}

	public static CafeOneTheme get() {
		return INSTANCE;
	}
}
