package nl.suzannawentzel.wicketcompact.categories;

import nl.suzannawentzel.wicketcompact.entities.Category;
import nl.suzannawentzel.wicketcompact.services.CategoryService;
import nl.suzannawentzel.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.ValidationError;

public class UniqueCategoryNameValidator implements org.apache.wicket.validation.IValidator<String>
{
	@Override
	public void validate(IValidatable<String> validatable)
	{
		final String givenCategoryName = validatable.getValue();
		final Category existingCategoryWithSameName = ServiceRegistry.get(CategoryService.class).getByName(givenCategoryName);
		if (existingCategoryWithSameName != null)
		{
			final ValidationError error = new ValidationError(this);
			error.setVariable("suggestedCategory", "Pastries");
			validatable.error(error);
		}
	}
}
