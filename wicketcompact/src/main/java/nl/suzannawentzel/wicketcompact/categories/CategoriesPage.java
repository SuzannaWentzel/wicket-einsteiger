package nl.suzannawentzel.wicketcompact.categories;

import nl.suzannawentzel.wicketcompact.BaseEntitiesPage;
import nl.suzannawentzel.wicketcompact.SuccessFeedbackPanel;
import nl.suzannawentzel.wicketcompact.ValidationErrorFeedbackPanel;
import nl.suzannawentzel.wicketcompact.entities.Category;
import nl.suzannawentzel.wicketcompact.models.EntityModel;
import nl.suzannawentzel.wicketcompact.services.CategoryService;
import nl.suzannawentzel.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class CategoriesPage extends BaseEntitiesPage
{
	private DataView<Category> categories;
	private final SortableDataProvider<Category, String> dataProvider;
	private ModalWindow dialog;
	private EditCategory editCategoryPanel;
	private final AjaxLink<Void> createCategory;
	private WebMarkupContainer categoriesParent;

	public CategoriesPage(PageParameters parameters)
	{
		super(parameters);
		dataProvider = new CategoriesDataProvider();
		categoriesParent = new WebMarkupContainer("categoriesParent");
		add(categoriesParent);

		categories = new DataView<Category>("categories", dataProvider)
		{
			@Override
			protected void populateItem(Item<Category> listItem)
			{
				final Category category = listItem.getModelObject();
				listItem.add(new Label("name"));

				final AttributeAppender srcAppender = new AttributeAppender("src", new PropertyModel<>(new EntityModel<>(category, CategoryService.class), "imageUrl"));
				listItem.add(new WebMarkupContainer("image").add(srcAppender));
				listItem.add(new AjaxLink<Void>("modifyCategory") {
					@Override
					public void onClick(AjaxRequestTarget target)
					{
						editCategoryPanel.setCategory(category);
						dialog.setTitle("Edit Category");
						dialog.show(target);
					}
				});
			}
		};

		categories.setOutputMarkupPlaceholderTag(true);

		createCategory = new AjaxLink<Void>("createCategory")
		{
			@Override
			public void onClick(AjaxRequestTarget target)
			{
				editCategoryPanel.setCategory(new Category());
				dialog.setTitle("Add Category");
				dialog.show(target);
			}
		};
	}

	@Override
	protected IPageable getPageable() {
		return categories;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		categories.setItemsPerPage(3);
		categoriesParent.add(categories);
		categoriesParent.setOutputMarkupPlaceholderTag(true);
		categoriesParent.add(new OrderByBorder<>("orderByName", "name", this.dataProvider));

		this.dialog = new ModalWindow("dialog");
		this.editCategoryPanel = new EditCategory(this.dialog.getContentId());
		this.dialog.setContent(editCategoryPanel);
		add(dialog);

		dialog.setWindowClosedCallback(new ModalWindow.WindowClosedCallback()
		{
			@Override
			public void onClose(AjaxRequestTarget target)
			{
				target.add(categoriesParent);
			}
		});

		add(createCategory);
	}

}
