package nl.suzannawentzel.wicketcompact.categories;

import nl.suzannawentzel.wicketcompact.ValidationErrorFeedbackPanel;
import nl.suzannawentzel.wicketcompact.entities.Category;
import nl.suzannawentzel.wicketcompact.models.EntityModel;
import nl.suzannawentzel.wicketcompact.services.CategoryService;
import nl.suzannawentzel.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

public class EditCategory extends Panel
{
	private final Form<Category> form = new Form<Category>("form")
	{
		@Override
		protected void onSubmit()
		{
			super.onSubmit();
			ServiceRegistry.get(CategoryService.class).save(form.getModelObject());
			setResponsePage(CategoriesPage.class);
		}
	};

	public EditCategory(String id)
	{
		super(id);
		form.setModel(new CompoundPropertyModel<>(new EntityModel<>(CategoryService.class)));
	}

	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		initializeForm();
	}

	private void initializeForm()
	{
		add(new ValidationErrorFeedbackPanel("validationFeedback"));
		add(form);
		form.add(new TextField<String>("name").add(new PropertyValidator<>()));
		form.add(new TextField<String>("imageUrl").add(new PropertyValidator<>()));
	}

	void setCategory(Category category) {
		form.setModelObject(category);
	}
}
