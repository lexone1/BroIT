package com.globalsqa.page;


import io.cucumber.datatable.DataTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Builder for cucumber DataTable structure
 */
public class DataTableBuilder
{

	private final ArrayList<List<String>> data;

	/**
	 * Init data storage
	 */
	public DataTableBuilder()
	{
		data = new ArrayList<>();
	}

	/**
	 * Add header for data structure
	 *
	 * @param values values
	 * @return DataTableBuilder
	 */
	public DataTableBuilder header(final String... values)
	{
		return row(values);
	}

	public DataTableBuilder header(final List<String> listOfData)
	{
		return row(listOfData);
	}

	/**
	 * Add row for data structure
	 *
	 * @param values values
	 * @return DataTableBuilder
	 */
	public DataTableBuilder row(final String... values)
	{
		this.addRow(values);
		return this;
	}

	/**
	 * Add row for data structure
	 *
	 * @param listOfData list values
	 * @return DataTableBuilder
	 */
	public DataTableBuilder row(final List<String> listOfData)
	{
		this.data.add(listOfData);
		return this;
	}

	/**
	 * Create new DataTable structure with data
	 *
	 * @return DataTable
	 */
	public DataTable build()
	{
		return DataTable.create(data);
	}

	private void addRow(final String... values)
	{
		data.add(Arrays.asList(values));
	}

}
