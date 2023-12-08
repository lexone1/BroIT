package com.globalsqa.common.element;

import java.util.List;

/**
 * An Interface for Table elements Created by Aliakei Pershyts on 31.03.2019.
 */
public interface BaseTableElement extends BasePageElement
{
	int getNumberOfRows();

	int getNumberOfColumns();

	List<String> getColumnValues();

	String getCellText(int rowIndex, int columnIndex);

	List<String> getRowText(int rowIndex);

	int getColumnIndexByHeader(String headerText);

	int getRowIndexByTextFromSpecifiedColumn(int columnIndex, String text);

	PageElement getCell(int rowIndex, int columnIndex);

	void refresh();

	PageElement getRow(int rowIndex);
}