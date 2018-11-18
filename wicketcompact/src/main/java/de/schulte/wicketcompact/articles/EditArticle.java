package de.schulte.wicketcompact.articles;

import de.schulte.wicketcompact.EntityModel;
import de.schulte.wicketcompact.ValidationErrorFeedbackPanel;
import de.schulte.wicketcompact.entities.Article;
import de.schulte.wicketcompact.services.ArticleService;
import de.schulte.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.RangeValidator;

import java.time.LocalDate;

public class EditArticle extends Panel {

    private final Form<Article> form = new Form<Article>("form") {

        @Override
        protected void onSubmit() {
            super.onSubmit();
            ServiceRegistry.get(ArticleService.class).save(form.getModelObject());
            setResponsePage(ArticlesPage.class);
        }
    };

    private final TextField<String> nameField;

    public EditArticle(String id) {
        super(id);
        form.setModel(new CompoundPropertyModel<>(new EntityModel<>(ArticleService.class)));
        this.nameField = new TextField<>("name");
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        initializeForm();
        this.form.add(new ExternalLink("help", new Model<String>() {

            @Override
            public String getObject() {
                return "https://de.wikipedia.org/wiki/" + EditArticle.this.nameField.getModelObject();
            }
        }));
    }

    private void initializeForm() {
        add(form);
        add(new ValidationErrorFeedbackPanel("validationFeedback"));
        form.add(nameField.add(new PropertyValidator<>()));
        form.add(new DropDownChoice<>("category", new CategoryListModel(), new ChoiceRenderer<>("name", "id")).add(new PropertyValidator<>()));
        form.add(new TextArea<String>("description").add(new PropertyValidator<>()).setLabel(Model.of("Beschreibung")));
        form.add(new TextField<>("price").add(new PropertyValidator<>()).setLabel(Model.of("Preis")));
        form.add(new TextField<>("validFrom").setLabel(Model.of("Gültig ab")).add(RangeValidator.maximum(LocalDate.now().plusMonths(3))));
        form.add(new TextField<>("validTo").setLabel(Model.of("Gültig bis")).add(RangeValidator.minimum(LocalDate.now().plusDays(1))));
        form.add(new TextField<String>("imageUrl").add(new PropertyValidator<>()).setLabel(Model.of("Bild-URL")));
    }

    EditArticle setArticle(Article article) {
        this.form.setModelObject(article);
        return this;
    }

}
