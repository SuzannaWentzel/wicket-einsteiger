package nl.suzannawentzel.wicketcompact.services;

import nl.suzannawentzel.wicketcompact.entities.Table;

public class TableService extends BaseService<Table> {

	public TableService() {
		final Table table1 = new Table("Table 1", 4);
		final Table table2 = new Table("Table 2", 4);
		final Table table3 = new Table("Table 3", 6);
		final Table table4 = new Table("Table 4", 12);
		final Table table5 = new Table("Table 5", 8);
		final Table table6 = new Table("Table 6", 4);
		final Table table7 = new Table("Table 7", 4);
		final Table table8 = new Table("Table 8", 6);
		final Table table9 = new Table("Table 9", 12);
		final Table table10 = new Table("Table 10", 8);

		save(table1);
		save(table2);
		save(table3);
		save(table4);
		save(table5);
		save(table7);
		save(table8);
		save(table9);
		save(table10);
		save(table6);
	}
}
