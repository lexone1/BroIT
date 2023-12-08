package com.globalsqa.page;

import com.globalsqa.*;
import com.globalsqa.common.PropertiesManager;
import com.globalsqa.common.TestConfiguration;
import com.globalsqa.common.exception.BaseException;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.globalsqa.BaseConstants.KEYWORDS_LIST;

/**
 * Custom wrapper around Cucumber {@link DataTable} objects that provides some additional functionality.
 */
public class BaseDataTable implements Iterable<DataTable>
{
	private DataTable dataTable;
	private List<Map<String, String>> maps;
	public final static String KEYWORDS_PATTERN = "\\+(.+?)\\+";

	/**
	 * Initializing constructor.
	 *
	 * @param dataTable
	 */
	public BaseDataTable(DataTable dataTable)
	{
		this.dataTable = dataTable;
		this.maps = dataTable.asMaps(String.class, String.class);;
	}

	/**
	 * Return cucumber data table structure
	 *
	 * @return cucumber data structure
	 */
	public DataTable getDataTable()
	{
		return this.dataTable;
	}

	/**
	 * Returns the number of rows of data present in this object.
	 *
	 * @return
	 */
	public int getNumberOfRows()
	{
		return maps.size();
	}

	/**
	 * Returns the value for the specified header from the data table. Returns null if no matching header is found.
	 *
	 * @param header
	 * @return
	 */
	public String getValue(String header)
	{
		return getValue(0, header);
	}

	/**
	 * Returns the value for the specified row and header from the data table. Returns null if no matching header is
	 * found.
	 *
	 * @param rowIndex
	 * @param header
	 * @return
	 */
	public String getValue(int rowIndex, String header)
	{
		String result = getRawValue(rowIndex, header);
		return getValueFromCucumberString(result);
	}

	/**
	 * Returns the raw value for the specified header and row from the data table. Value is not processing through the
	 * ValueFromCucumberString method.
	 *
	 * @param rowIndex
	 * @param header
	 * @return
	 */
	public String getRawValue(int rowIndex, String header)
	{
		return maps.get(rowIndex).get(header);
	}

	/**
	 * Returns the raw value for the specified header from the data table. The row specified as 0 (first) by default.
	 * Value is not processing through the ValueFromCucumberString method.
	 *
	 * @param header
	 * @return
	 */
	public String getRawValue(String header)
	{
		return getRawValue(0, header);
	}

	/**
	 * Returns the column values for the specified column name in the BaseDataTable.
	 *
	 * @param columnName specified column name in the BaseDataTable
	 * @return {@link List<String>} all values from specified column
	 */
	public List<String> getColumnValues(String columnName)
	{
		List<String> columnValues = new ArrayList<>();
		for (DataTable table : this)
		{
			String value = new BaseDataTable(table).getValue(columnName);
			columnValues.add(value);
		}
		return columnValues;
	}

	/**
	 * Returns the list values specified in data table (formated form globals)
	 *
	 * @param table cucumber data table
	 * @param row row index
	 * @return list values
	 */
	public static List<String> getListValuesFromDataTable(DataTable table, int row)
	{
		List<String> result = new ArrayList<>();
		List<String> dataTableValues = table.cells().get(row);
		for (String value : dataTableValues)
		{
			result.add(getValueFromCucumberString(value));
		}
		return result;
	}

	/**
	 * Extracts value from cucumber string with special keywords handling
	 *
	 * @param cucumberString text from cucumber
	 * @return value extracted from cucumber text
	 */
	public static String getValueFromCucumberString(String cucumberString)
	{
		// sanitize nulls and blank strings to null
		if (StringUtils.isBlank(cucumberString))
		{
			cucumberString = null;
		}
		// else check for some special flags and substitute their values
		else
		{
			if (cucumberString.equals(BaseConstants.DATATABLE_KEYWORD_EMPTY))
			{
				cucumberString = "";
			}
			if (StringUtils.indexOfAny(cucumberString, KEYWORDS_LIST) > -1)
			{
				cucumberString = parseAnyKeywords(cucumberString);
			}
			if (cucumberString.startsWith(BaseConstants.DATATABLE_KEYWORD_RANDOM_ALPHABETIC))
			{
				cucumberString = parseRandomAlphabetic(cucumberString);
			}
			else if (cucumberString.startsWith(BaseConstants.DATATABLE_KEYWORD_RANDOM_NUMERIC))
			{
				cucumberString = parseRandomNumeric(cucumberString);
			}
			else if (cucumberString.startsWith(BaseConstants.DATATABLE_KEYWORD_RANDOM))
			{
				cucumberString = parseRandom(cucumberString);
			}
			else if (cucumberString.startsWith(BaseConstants.DATATABLE_KEYWORD_GLOBAL))
			{
				cucumberString = parseGlobal(cucumberString);
			}
			else if (cucumberString.startsWith(BaseConstants.DATATABLE_KEYWORD_PROPERTY))
			{
				cucumberString = parseProperty(cucumberString);
			}
		}
		return cucumberString;
	}

	@Override
	public Iterator<DataTable> iterator()
	{
		return new BaseDataTableIterator();
	}

	class BaseDataTableIterator implements Iterator<DataTable>
	{

		private int currentIndex = 0;

		@Override
		public boolean hasNext()
		{
			return BaseDataTable.this.maps.size() > currentIndex;
		}

		@Override
		public DataTable next()
		{
			final Map<String, String> dataRecord = BaseDataTable.this.maps.get(currentIndex);
			currentIndex++;
			return DataTable.create(Utilities.convertDTMapsToDTLists(dataRecord));
		}
	}

	@Override
	public String toString()
	{
		DataTable updatedDataTable = Utilities.replaceAllTokensInDataTable(dataTable);
		return updatedDataTable.toString();
	}

	/**
	 * Generates random alphabetic string according to provided values.
	 *
	 * @param cucumberString text from cucumber
	 * @return {@link String} value extracted from cucumber text
	 */
	private static String parseRandomAlphabetic(String cucumberString)
	{
		int length = Integer.valueOf(StringUtils.split(cucumberString, '_')[2]);
		StringBuilder stringBuilder = new StringBuilder();
		while (length > BaseConstants.MAX_WORD_LENGTH + 1)
		{
			stringBuilder.append(RandomStringUtils.randomAlphabetic(BaseConstants.MAX_WORD_LENGTH)).append(" ");
			length -= BaseConstants.MAX_WORD_LENGTH + 1;
		}
		stringBuilder.append(RandomStringUtils.randomAlphabetic(length));
		return StringUtils.capitalize(stringBuilder.toString());
	}

	/**
	 * Generates random numeric string according to provided values.
	 *
	 * @param cucumberString text from cucumber
	 * @return {@link String} value extracted from cucumber text
	 */
	private static String parseRandomNumeric(String cucumberString)
	{
		return RandomStringUtils.randomNumeric(Integer.valueOf(StringUtils.split(cucumberString, '_')[2]));
	}

	/**
	 * Generates random string according to provided values.
	 *
	 * @param cucumberString text from cucumber
	 * @return {@link String} value extracted from cucumber text
	 */
	private static String parseRandom(String cucumberString)
	{
		TestConfiguration testConfiguration = (TestConfiguration) ApplicationContextProvider.getApplicationContext()
				.getBean("testConfiguration");
		cucumberString = RandomStringUtils.randomAlphanumeric(
				Integer.valueOf(StringUtils.split(cucumberString, '_')[1]));
		return testConfiguration.getCapitalizeRandom() ? StringUtils.capitalize(cucumberString) : cucumberString;
	}

	/**
	 * Parses the globals string according to provided values.
	 *
	 * @param cucumberString text from cucumber
	 * @return {@link String} value extracted from cucumber text
	 */
	private static String parseGlobal(String cucumberString)
	{
		int start = BaseConstants.NO_MATCH_FOUND;
		int end = BaseConstants.NO_MATCH_FOUND;
		String backupForLog = cucumberString;
		Globals globals = (Globals) ApplicationContextProvider.getApplicationContext().getBean("globals");
		if (cucumberString.contains("{") && cucumberString.contains("}"))
		{
			String range = StringUtils.substringBetween(cucumberString, "{", "}");
			start = Integer.parseInt(range.split(",")[0].trim());
			end = Integer.parseInt(range.split(",")[1].trim());
			cucumberString = cucumberString.replaceAll("[{].*?[}]", "");
		}
		if (cucumberString.contains("[") && cucumberString.contains("]"))
		{
			String indexString = StringUtils.substringBetween(cucumberString, "[", "]");
			List<String> resultList = globals.get(StringUtils.substringBetween(cucumberString,
					BaseConstants.DATATABLE_KEYWORD_GLOBAL, "["));
			int index;
			if (indexString.contains("last"))
			{
				index = resultList.size() - 1 - (indexString.contains("-") ? Integer.parseInt(
						indexString.replace("last-", "")) : 0);
			}
			else
			{
				index = Integer.parseInt(indexString);
			}
			cucumberString = resultList.get(index);
		}
		else
		{
			cucumberString = globals.get(StringUtils.split(cucumberString, '_')[1]);
		}
		if (start > BaseConstants.NO_MATCH_FOUND)
		{
			cucumberString = cucumberString.substring(start, end);
		}
		if (cucumberString == null)
		{
			throw new BaseException("Global " + backupForLog + " is null");
		}
		return cucumberString;
	}

	/**
	 * Parses the property string according to provided values.
	 *
	 * @param cucumberString text from cucumber
	 * @return {@link String} value extracted from cucumber text
	 */
	private static String parseProperty(String cucumberString)
	{
		int start = BaseConstants.NO_MATCH_FOUND;
		int end = BaseConstants.NO_MATCH_FOUND;
		PropertiesManager properties = (PropertiesManager) ApplicationContextProvider.getApplicationContext()
				.getBean("propertiesManager");
		if (cucumberString.contains("{") && cucumberString.contains("}"))
		{
			String range = StringUtils.substringBetween(cucumberString, "{", "}");
			start = Integer.parseInt(range.split(",")[0].trim());
			String secondaryElement = range.split(",")[1].trim();
			if (secondaryElement.isEmpty())
			{
				end = Integer.MAX_VALUE;
			}
			else
			{
				end = Integer.parseInt(secondaryElement);
			}

			cucumberString = cucumberString.replaceAll("[{].*?[}]", "");
		}
		cucumberString = getValueFromCucumberString(
				properties.get(cucumberString.replace(BaseConstants.DATATABLE_KEYWORD_PROPERTY, "")));
		if (start > BaseConstants.NO_MATCH_FOUND)
		{
			if (end == Integer.MAX_VALUE)
			{
				cucumberString = cucumberString.substring(start, cucumberString.length());
			}
			else
			{
				cucumberString = cucumberString.substring(start, end);
			}
		}
		return cucumberString;
	}

	/**
	 * Parses all found keywords using keywords pattern.
	 *
	 * @param cucumberString text from cucumber
	 * @return {@link String} value extracted from cucumber text
	 */
	private static String parseAnyKeywords(String cucumberString)
	{
		// for parsing value from cucumber where GLOBALS are used inline
		// please use '+' sign as global parameter delimeter
		final Pattern pattern = Pattern.compile(KEYWORDS_PATTERN);
		final Matcher matcher = pattern.matcher(cucumberString);
		List<String> foundKeywords = new ArrayList<>();
		while (matcher.find())
		{
			foundKeywords.add(matcher.group(1));
		}

		if (foundKeywords.size() > 0)
		{
//			logger.debug("Inline keywords found: " + Arrays.toString(foundKeywords.toArray()));
			for (String keyword : foundKeywords)
			{
				cucumberString = cucumberString.replaceFirst(KEYWORDS_PATTERN,
						Matcher.quoteReplacement(getValueFromCucumberString(keyword)));
			}
//			logger.debug("Final string with replaced keywords: " + cucumberString);
		}
		return cucumberString;
	}

	/**
	 * Parses date and modifies it according to provided values and by calendar set
	 *
	 * @param date date
	 * @param pattern pattern parser
	 * @param cucumberString cucumber string
	 * @param calendar calendar
	 * @return
	 */
//	private static Date modifyDate(Date date, String pattern, String cucumberString, int calendar)
//	{
//		// check for moving datetime forwards or backwards (by minutes)
//		Pattern p = Pattern.compile(pattern);
//		Matcher m = p.matcher(cucumberString);
//		if (m.find())
//		{
//			boolean positive = positiveCheck.contains(m.group(1));
//			int adjustment = Integer.parseInt(m.group(2));
//			if (cucumberString.contains(BaseConstants.DATATABLE_KEYWORD_BUSINESS_DAYS))
//			{
//				date = DateUtilities.moveDateBusinessDays(date, (positive ? adjustment : -adjustment));
//			}
//			else if (cucumberString.contains(BaseConstants.DATATABLE_KEYWORD_CANT_LAND_DAYS))
//			{
//				date = DateUtilities.moveDateCantLandDays(date, (positive ? adjustment : -adjustment));
//			}
//			else
//			{
//				date = DateUtilities.moveDate(date, calendar, (positive ? adjustment : -adjustment));
//			}
//		}
//		return date;
//	}

//	/**
//	 * Adds postfix to property and extracts its value.
//	 *
//	 * @param propertyPostfix - postfix for properties
//	 * @param keywordPrefix - BaseDataTable keyword prefix
//	 * @param cucumberString - cucumber string
//	 * @return {@link String} result cucumber string
//	 */
//	private static String getFromPropertyWithPostfix(String propertyPostfix, String keywordPrefix,
//			String cucumberString)
//	{
//		if (propertyPostfix.isEmpty())
//		{
//			cucumberString = cucumberString.replace(keywordPrefix, BaseConstants.DATATABLE_KEYWORD_PROPERTY);
//		}
//		else
//		{
//			StringBuilder builder = new StringBuilder(BaseConstants.DATATABLE_KEYWORD_PROPERTY);
//			cucumberString = cucumberString.replaceFirst(keywordPrefix, "");
//			String[] keyWords = new String[] { "_minus", "_plus", "_multiply", "{" };
//			int position = StringUtils.indexOfAny(cucumberString, keyWords);
//			if (position != BaseConstants.NO_MATCH_FOUND)
//			{
//				builder.append(cucumberString.substring(0, position));
//				builder.append(propertyPostfix);
//				builder.append(cucumberString.substring(position, cucumberString.length()));
//			}
//			else
//			{
//				builder.append(cucumberString);
//				builder.append(propertyPostfix);
//			}
//			cucumberString = builder.toString();
//		}
//		return getValueFromCucumberString(cucumberString);
//	}

//	/**
//	 * Parses string for commands represented as "_plus" and return map of position + command represented as "+" map
//	 * will be sorted by position due to the treemap nature
//	 *
//	 * @param initialString inputString
//	 * @return map of position + math command
//	 */
//	static private Map<Integer, String> getAllIndexes(String initialString)
//	{
//		Map<Integer, String> map = new TreeMap<>();
//		for (MathOperations command : MathOperations.values())
//		{
//			StringUtils.indexOfAny(initialString, command.getName());
//			int index = initialString.indexOf(command.getName());
//			while (index >= 0)
//			{
//				map.put(index, command.getRepresentation());
//				index = initialString.indexOf(command.getName(), index + 1);
//			}
//		}
//		return map;
//	}
}
