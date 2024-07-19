package nl.suzannawentzel.wicketcompact.categories;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { UniqueCategoryNameValidator.class })
@Documented
public @interface UniqueCategoryName
{
	String message() default "{unique.category.name}";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
}
