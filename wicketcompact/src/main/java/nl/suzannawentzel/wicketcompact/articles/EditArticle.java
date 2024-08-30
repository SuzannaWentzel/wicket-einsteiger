package nl.suzannawentzel.wicketcompact.articles;

import nl.suzannawentzel.wicketcompact.ValidationErrorFeedbackPanel;
import nl.suzannawentzel.wicketcompact.categories.CategoryListModel;
import nl.suzannawentzel.wicketcompact.entities.Article;
import nl.suzannawentzel.wicketcompact.entities.Category;
import nl.suzannawentzel.wicketcompact.models.EntityModel;
import nl.suzannawentzel.wicketcompact.services.ArticleService;
import nl.suzannawentzel.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.extensions.ajax.markup.html.AjaxEditableChoiceLabel;
import org.apache.wicket.extensions.ajax.markup.html.AjaxEditableLabel;
import org.apache.wicket.extensions.ajax.markup.html.AjaxEditableMultiLineLabel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.FormComponentUpdatingBehavior;
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

	private final TextField<String> nameField;

	private final MarkupContainer image;

	private final FormComponent<String> imageUrl;

	private final ExternalLink helpLink;

	public EditArticle(String id)
	{
		super(id);
		form.setModel(new CompoundPropertyModel<>(new EntityModel<>(ArticleService.class)));
		this.nameField = new TextField<>("name");
		helpLink = new ExternalLink("help", new Model<String>()
		{
			@Override
			public String getObject()
			{
				return "https://wikipedia.org/wiki/" + EditArticle.this.nameField.getModelObject();
			}
		});

		helpLink.setOutputMarkupId(true);

		nameField.add(new AjaxFormComponentUpdatingBehavior("change") {
			@Override
			protected void onUpdate(AjaxRequestTarget target)
			{
				target.add(helpLink);
			}
		});
		this.image = new WebMarkupContainer("image");
		this.image.add(new AttributeModifier("src", new Model<String>() {
			@Override
			public String getObject()
			{
				return imageUrl.getModelObject();
			}
		}));
		image.setOutputMarkupId(true);

		imageUrl = new TextField<String>("imageUrl").add(new PropertyValidator<>()).setLabel(Model.of("Image URL"));
		imageUrl.add(new AjaxFormComponentUpdatingBehavior("change") {
			@Override
			protected void onUpdate(AjaxRequestTarget target)
			{
				target.add(image);
			}
		});
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
		form.add(nameField.add(new PropertyValidator<>()));
		form.add(new AjaxEditableMultiLineLabel<>("description").setLabel(Model.of("Description")));
		form.add(new AjaxEditableChoiceLabel<>("category", null, new CategoryListModel(), new ChoiceRenderer<>("name", "id")));
		form.add(new AjaxEditableLabel<>("price").setLabel(Model.of("Price")));
		form.add(new AjaxEditableLabel<>("validFrom").setLabel(Model.of("Valid from")).add(RangeValidator.maximum(LocalDate.now().plusMonths(3))));
		form.add(new AjaxEditableLabel<>("validTo").setLabel(Model.of("Valid to")).add(RangeValidator.minimum(LocalDate.now().plusDays(1))));
		form.add(imageUrl);
		form.add(image);
		form.add(this.helpLink);
	}

	void setArticle(Article article) {
		form.setModelObject(article);
	}

}
