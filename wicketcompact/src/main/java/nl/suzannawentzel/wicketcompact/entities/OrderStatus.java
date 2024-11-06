package nl.suzannawentzel.wicketcompact.entities;

public enum OrderStatus
{
	NEW ("New"),
	PREPARATION ("Preparing"),
	DONE ("Delivered");

	private final String text;

	OrderStatus(String text) { this.text = text; }

	public String getText()
	{
		return text;
	}
}
