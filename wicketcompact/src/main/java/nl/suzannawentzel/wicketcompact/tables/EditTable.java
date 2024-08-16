package nl.suzannawentzel.wicketcompact.tables;

import nl.suzannawentzel.wicketcompact.ValidationErrorFeedbackPanel;
import nl.suzannawentzel.wicketcompact.entities.Table;
import nl.suzannawentzel.wicketcompact.models.EntityModel;
import nl.suzannawentzel.wicketcompact.services.ServiceRegistry;
import nl.suzannawentzel.wicketcompact.services.TableService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

import java.util.Arrays;

public class EditTable extends Panel
{

	private final Form<Table> form = new Form<Table>("form") {
		@Override
		protected void onSubmit() {
			super.onSubmit();
			ServiceRegistry.get(TableService.class).save(form.getModelObject());
			setResponsePage(TablesPage.class);
		}
	};

	public EditTable(String id) {
		super(id);
		form.setModel(new CompoundPropertyModel<>(new EntityModel<>(TableService.class)));
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
		form.add(new TextField<String>("name").setRequired(true));
		form.add(new CheckBox("orderableElectronically"));
		addSeatCountChoiceToForm();
	}

	private void addSeatCountChoiceToForm() {
		final RadioGroup<Integer> radioGroup = new RadioGroup<>("seatCount");
		radioGroup.setRenderBodyOnly(false);
		final ListView<Integer>
			choices = new ListView<Integer>("choices", Arrays.asList(1,2,3,4,5,6,7,8))
		{
			@Override
			protected void populateItem(ListItem<Integer> listItem)
			{
				listItem.add(new Label("seatCountLabel", listItem.getModelObject()));
				listItem.add(new Radio<>("seatCountValue", listItem.getModel(), radioGroup));
			}
		};
		form.add(radioGroup);
		radioGroup.add(choices);
	}

	void setTable(Table table) {
		form.setModelObject(table);
	}
}
