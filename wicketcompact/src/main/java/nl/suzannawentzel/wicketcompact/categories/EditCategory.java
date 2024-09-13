package nl.suzannawentzel.wicketcompact.categories;

import nl.suzannawentzel.wicketcompact.LoadingIndicatorAjaxSubmitLink;
import nl.suzannawentzel.wicketcompact.SgFeedbackPanel;
import nl.suzannawentzel.wicketcompact.ValidationErrorFeedbackPanel;
import nl.suzannawentzel.wicketcompact.entities.Category;
import nl.suzannawentzel.wicketcompact.models.EntityModel;
import nl.suzannawentzel.wicketcompact.services.CategoryService;
import nl.suzannawentzel.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
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
		}
	};

	private FeedbackPanel validationFeedback;

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
		validationFeedback = new SgFeedbackPanel("validationFeedback");
		add(validationFeedback);
		add(form);
		form.add(new TextField<String>("name").add(new PropertyValidator<>()));
		form.add(new TextField<String>("imageUrl").add(new PropertyValidator<>()));
		form.add(new LoadingIndicatorAjaxSubmitLink("save") {

			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				super.onSubmit(target);
				findParent(ModalWindow.class).close(target);
			}

			@Override
			protected void onError(AjaxRequestTarget target) {
				super.onSubmit(target);
				target.add(EditCategory.this.validationFeedback);
			}
		});
	}

	void setCategory(Category category) {
		form.setModelObject(category);
	}
}
