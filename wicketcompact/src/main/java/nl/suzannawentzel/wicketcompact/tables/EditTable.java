package nl.suzannawentzel.wicketcompact.tables;

import nl.suzannawentzel.wicketcompact.ValidationErrorFeedbackPanel;
import nl.suzannawentzel.wicketcompact.entities.Table;
import nl.suzannawentzel.wicketcompact.models.EntityModel;
import nl.suzannawentzel.wicketcompact.services.ServiceRegistry;
import nl.suzannawentzel.wicketcompact.services.TableService;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

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

	private AjaxCheckBox orderableElectronically;
	private AttributeModifier dataAttributeModifier;
	private Component qrCode;



	public EditTable(String id) {
		super(id);
		form.setModel(new CompoundPropertyModel<>(new EntityModel<>(TableService.class)));
		this.dataAttributeModifier = new AttributeModifier("data", new Model<String>() {
			@Override
			public String getObject() {
				final Long id = EditTable.this.form.getModelObject().getId();
				return id != null ? String.format("https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=http://localhost:9999/table/%d", id) : null;
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
		form.add(new TextField<String>("name").add(new PropertyValidator<>()));
		addQRCodeToForm();
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

	private void addQRCodeToForm() {
		orderableElectronically = new AjaxCheckBox("orderableElectronically")
		{
			@Override
			protected void onUpdate(AjaxRequestTarget target)
			{
				target.add(EditTable.this.qrCode);
			}
		};
		orderableElectronically.add(new PropertyValidator<>());
		form.add(orderableElectronically);

		qrCode = new WebMarkupContainer("qrCode") {
			@Override
			protected void onConfigure()
			{
				super.onConfigure();
				final Boolean shouldShow = EditTable.this.orderableElectronically.getModelObject();
				this.setVisible(shouldShow == null ? false : shouldShow);
			}
		}.setOutputMarkupPlaceholderTag(true);

		this.form.add(this.qrCode);
		this.qrCode.add(dataAttributeModifier);
	}

	public void setTable(Table table) {
		form.setModelObject(table);
	}
}
