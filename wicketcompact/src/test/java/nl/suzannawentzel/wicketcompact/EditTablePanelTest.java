package nl.suzannawentzel.wicketcompact;

import nl.suzannawentzel.wicketcompact.entities.Table;
import nl.suzannawentzel.wicketcompact.tables.EditTable;
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
}
