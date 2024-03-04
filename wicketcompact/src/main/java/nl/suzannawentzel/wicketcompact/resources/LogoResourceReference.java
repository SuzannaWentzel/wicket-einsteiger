package nl.suzannawentzel.wicketcompact.resources;

import org.apache.wicket.request.resource.ContextRelativeResourceReference;

/**
 * Such a construct can be used to include a resource in Java, add the resource to /webapp.
 * NOTE: currently this file isn't used.
 */
public class LogoResourceReference extends ContextRelativeResourceReference
{
	public LogoResourceReference()
	{
		super("coffee.png");
	}
}
