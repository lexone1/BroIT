package com.globalsqa;

import com.globalsqa.page.BaseDataTable;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Collections2;

import io.cucumber.datatable.DataTable;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.text.WordUtils;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.junit.Assert;
import org.springframework.context.NoSuchMessageException;

import java.io.*;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Generic Utility static methods that are application/project independent
 *
 * @author Aliaksei Pershyts
 */
public class Utilities
{
	/**
	 *
	 */
	public static List<List<String>> convertDTMapsToDTLists(Map<String, String>... mapsList)
	{
		List<List<String>> rows = new ArrayList<>();
		if (mapsList.length > 0)
		{
			rows.add(Arrays.asList(mapsList[0].keySet().toArray(new String[0])));
			for (Map<String, String> tempMap : mapsList)
			{
				if (!tempMap.isEmpty())
				{
					List<String> row = new ArrayList<>();
					for (Map.Entry entry : tempMap.entrySet())
					{
						row.add((String) entry.getValue());
					}
					rows.add(row);
				}
			}
		}
		return rows;
	}

	/**
	 * Replace all tokens in data table
	 *
	 * @param table - cucumber data table
	 * @return {@link DataTable}
	 */
	public static DataTable replaceAllTokensInDataTable(DataTable table)
	{
		List<List<String>> newRows = new ArrayList<>();
		List<List<String>> actualRows = table.cells();
		for (List<String> actualRow : actualRows)
		{
			List<String> row = new ArrayList<>();
			for (String columnValue : actualRow)
			{
				if (StringUtils.isBlank(columnValue) || columnValue.equals(BaseConstants.DATATABLE_KEYWORD_EMPTY))
				{
					row.add(columnValue);
				}
				else
				{
					row.add(BaseDataTable.getValueFromCucumberString(columnValue));
				}
			}
			newRows.add(row);
		}
		return DataTable.create(newRows);
	}
}

