package nl.suzannawentzel.wicketcompact.login;

import org.apache.wicket.model.IModel;

public class Credentials implements IModel<Credentials>
{
	private String username, password;

	@Override
	public Credentials getObject()
	{
		return this;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
}
