package nl.suzannawentzel.wicketcompact;

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

public class SgSession extends WebSession
{
	private String userLoggedIn;

	public SgSession(Request request)
	{
		super(request);
	}

	public void setUserLoggedIn(final String username) {
		userLoggedIn = username;
	}

	public Boolean isUserLoggedIn()
	{
		return userLoggedIn != null && !userLoggedIn.isEmpty();
	}

	public String getUserLoggedIn() {
		return userLoggedIn;
	}
}
