package nl.suzannawentzel.wicketcompact.login;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CredentialsValidator implements ConstraintValidator<CredentialsValidatorAnnotation, Credentials>
{
	private static final String USERNAME = "user";
	private static final String PASSWORD = "veryStrongPassword";

	@Override
	public void initialize(CredentialsValidatorAnnotation constraintAnnotation)
	{
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(Credentials credentials,
		ConstraintValidatorContext constraintValidatorContext)
	{
		return credentials.getUsername().equals(USERNAME) && credentials.getPassword().equals(PASSWORD);
	}
}
