package nl.suzannawentzel.wicketcompact.articles;

import nl.suzannawentzel.wicketcompact.ValidationErrorFeedbackPanel;
import nl.suzannawentzel.wicketcompact.categories.CategoryListModel;
import nl.suzannawentzel.wicketcompact.entities.Article;
import nl.suzannawentzel.wicketcompact.entities.Category;
import nl.suzannawentzel.wicketcompact.models.EntityModel;
import nl.suzannawentzel.wicketcompact.services.ArticleService;
import nl.suzannawentzel.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.extensions.ajax.markup.html.AjaxEditableChoiceLabel;
import org.apache.wicket.extensions.ajax.markup.html.AjaxEditableLabel;
import org.apache.wicket.extensions.ajax.markup.html.AjaxEditableMultiLineLabel;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.RangeValidator;
import org.apache.wicket.validation.validator.UrlValidator;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EditArticle extends Panel
{
	private final Form<Article> form = new Form<Article>("form") {
		@Override
		protected void onSubmit() {
			super.onSubmit();
			ServiceRegistry.get(ArticleService.class).save(form.getModelObject());
			setResponsePage(ItemsPage.class);
		}
	};

	private final AjaxEditableLabel<String> nameField;

	public EditArticle(String id)
	{
		super(id);
		form.setModel(new CompoundPropertyModel<>(new EntityModel<>(ArticleService.class)));
		this.nameField = new AjaxEditableLabel<>("name");
	}

	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		initializeForm();
	}

	private void initializeForm() {
		add(form);
		add(new ValidationErrorFeedbackPanel("validationFeedback"));
		form.add(nameField);
		form.add(new AjaxEditableMultiLineLabel<>("description").setLabel(Model.of("Description")));
		form.add(new AjaxEditableChoiceLabel<>("category", null, new CategoryListModel(), new ChoiceRenderer<>("name", "id")));
		form.add(new AjaxEditableLabel<>("price").setLabel(Model.of("Price")));
		form.add(new AjaxEditableLabel<>("validFrom").setLabel(Model.of("Valid from")).add(RangeValidator.maximum(LocalDate.now().plusMonths(3))));
		form.add(new AjaxEditableLabel<>("validTo").setLabel(Model.of("Valid to")).add(RangeValidator.minimum(LocalDate.now().plusDays(1))));
		form.add(new AjaxEditableLabel<>("imageUrl").setLabel(Model.of("Image URL")));
		form.add(new ExternalLink("help", new Model<String>() {
			@Override
			public String getObject()
			{
				return "https://de.wikipedia.org/wiki/" + EditArticle.this.nameField.getModelObject();
			}
		}));
	}

	void setArticle(Article article) {
		form.setModelObject(article);
	}

}
