package nl.suzannawentzel.wicketcompact.categories;

import nl.suzannawentzel.wicketcompact.entities.Category;
import nl.suzannawentzel.wicketcompact.services.CategoryService;
import nl.suzannawentzel.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.ValidationError;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueCategoryNameValidator implements ConstraintValidator<UniqueCategoryName, String>
{
	@Override
	public void initialize(UniqueCategoryName constraintAnnotation) {

	}

	@Override
	public boolean isValid(String givenCategoryName, ConstraintValidatorContext constraintValidatorContext) {
		for (Category category : ServiceRegistry.get(CategoryService.class).listAll()) {
			if (category.getName().equals(givenCategoryName)) {
				return false;
			}
		}
		return true;
	}
}
