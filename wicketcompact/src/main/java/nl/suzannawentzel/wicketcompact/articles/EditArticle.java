package nl.suzannawentzel.wicketcompact.articles;

import nl.suzannawentzel.wicketcompact.ValidationErrorFeedbackPanel;
import nl.suzannawentzel.wicketcompact.categories.CategoryListModel;
import nl.suzannawentzel.wicketcompact.entities.Article;
import nl.suzannawentzel.wicketcompact.entities.Category;
import nl.suzannawentzel.wicketcompact.models.EntityModel;
import nl.suzannawentzel.wicketcompact.services.ArticleService;
import nl.suzannawentzel.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
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

	public EditArticle(String id)
	{
		super(id);
		form.setModel(new CompoundPropertyModel<>(new EntityModel<>(ArticleService.class)));
		this.nameField = new TextField<>("name");
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
		form.add(nameField.setRequired(true).setLabel(Model.of("Name")));
		form.add(new TextArea<String>("description").setRequired(true).setLabel(Model.of("Description")));
		form.add(new DropDownChoice<Category>("category", new CategoryListModel(), new ChoiceRenderer<Category>("name", "id")).add(new PropertyValidator<>()));
		form.add(new TextField<BigDecimal>("price").setRequired(true).setLabel(Model.of("Price")).add(new RangeValidator<>(BigDecimal.ZERO, new BigDecimal("20"))));
		form.add(new TextField<>("validFrom").setLabel(Model.of("Valid from")).add(new RangeValidator<>(
			LocalDate.now(), LocalDate.now().plusDays(365))));
		form.add(new TextField<>("validTo").setLabel(Model.of("Valid to")).add(new RangeValidator<>(LocalDate.now().plusDays(1),
			LocalDate.MAX)));
		form.add(new TextField<String>("imageUrl").setLabel(Model.of("Image URL")).setRequired(true).add(new UrlValidator()));
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
