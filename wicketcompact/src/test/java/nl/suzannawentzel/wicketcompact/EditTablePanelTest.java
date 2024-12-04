package nl.suzannawentzel.wicketcompact;

import nl.suzannawentzel.wicketcompact.entities.Table;
import nl.suzannawentzel.wicketcompact.tables.EditTable;
import org.apache.wicket.util.tester.FormTester;
import org.junit.Before;
import org.junit.Test;

public class EditTablePanelTest extends BasePageWithoutLoginTest
{
	@Before
	public void setupComponentUnderTest() {
		EditTable componentUnderTest = new EditTable("editTable");
		componentUnderTest.setTable(new Table("Test-table", 4));
		tester.startComponentInPage(componentUnderTest);
	}

	@Test
	public void tableNameIsEditable() {
		tester.assertVisible("editTable:form:name");
		tester.assertEnabled("editTable:form:name");
		tester.assertModelValue("editTable:form:name", "Test-table");
	}

	@Test
	public void showsBarcodeWhenOrderableElectronically() {
		tester.assertInvisible("editTable:form:qrCode");
		final FormTester formTester = tester.newFormTester("editTable:form");
		formTester.setValue("orderableElectronically", true);
		tester.executeAjaxEvent("editTable:form:orderableElectronically", "click");
		tester.assertVisible("editTable:form:qrCode");
	}
}
