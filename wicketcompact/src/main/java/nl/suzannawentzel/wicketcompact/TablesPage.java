package nl.suzannawentzel.wicketcompact;

import nl.suzannawentzel.wicketcompact.entities.Article;
import nl.suzannawentzel.wicketcompact.entities.Table;
import nl.suzannawentzel.wicketcompact.models.EntityModel;
import nl.suzannawentzel.wicketcompact.services.ServiceRegistry;
import nl.suzannawentzel.wicketcompact.services.TableService;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.Arrays;

public class TablesPage extends BaseEntitiesPage
{
	private final DataView<Table> tables;

	private final Form<Table> form = new Form<Table>("form") {
		@Override
		protected void onSubmit() {
			super.onSubmit();
			form.setVisible(false);
			ServiceRegistry.get(TableService.class).save(form.getModelObject());
			form.success("Table was added!");
		}
	};

	public TablesPage(PageParameters parameters)
	{
		super(parameters);
		IDataProvider<Table> dataProvider = new TablesDataProvider();
		this.tables = new DataView<Table>("tables", dataProvider)
		{
			@Override
			protected void populateItem(Item<Table> item)
			{
				Table table = item.getModelObject();
				item.add(new Label("name",table.getName()));
				item.add(new Label("seats", table.getSeatCount()));
				item.add(new Label("orderableElectronically", table.getOrderableElectronically()));
			}
		};
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
//		tables.setItemsPerPage(4);
		add(tables);
		add(new Link<String>("newTable") {
			@Override
			public void onClick() {
				form.setVisible(true);
				form.setModelObject(new Table());
			}
		});
		initializeForm();
	}

	private void initializeForm() {
		form.setVisible(false);
		add(form);
		add(new ValidationErrorFeedbackPanel("validationFeedback"));
		add(new SuccessFeedbackPanel("successFeedback"));
		form.setModel(new CompoundPropertyModel<>(new EntityModel<>(TableService.class)));
		form.add(new TextField<String>("name").setRequired(true));
		form.add(new CheckBox("orderableElectronically"));
		addSeatCountChoiceToForm();
	}

	private void addSeatCountChoiceToForm() {
		final RadioGroup<Integer> radioGroup = new RadioGroup<>("seatCount");
		radioGroup.setRenderBodyOnly(false);
		final ListView<Integer> choices = new ListView<Integer>("choices", Arrays.asList(1,2,3,4,5,6,7,8))
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

	@Override
	protected IPageable getPageable() {
		return this.tables;
	}
}
