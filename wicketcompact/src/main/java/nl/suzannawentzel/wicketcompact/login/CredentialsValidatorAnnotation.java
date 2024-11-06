package nl.suzannawentzel.wicketcompact.login;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {CredentialsValidator.class})
@Documented
public @interface CredentialsValidatorAnnotation
{
	String message() default "{credentials.invalid}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
