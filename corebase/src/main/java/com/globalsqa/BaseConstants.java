package com.globalsqa;

/**
 * Created by U0160473 on 2/2/2015.
 *
 * @author U0160473 The ${PACKAGE_NAME} will implement the test automation functionality regarding base constants.
 * <p>
 * Copyright 2015: Thomson Reuters Global Resources. All Rights Reserved. Proprietary and Confidential
 * information of TRGR. Disclosure, Use or Reproduction without the written authorization of TRGR is prohibited
 */
public class BaseConstants
{
	public static final String APPLICATION_URL_KEY = "applicationURL";
	public static final String DATATABLE_KEYWORD_EMPTY = "EMPTY";
	public static final String DATATABLE_KEYWORD_RANDOM_ALPHABETIC = "RANDOM_ALPHABETIC_";
	public static final String DATATABLE_KEYWORD_RANDOM_NUMERIC = "RANDOM_NUMERIC_";
	public static final String DATATABLE_KEYWORD_RANDOM = "RANDOM_";
	public static final String DATATABLE_KEYWORD_GLOBAL = "GLOBAL_";
	public static final String DATATABLE_KEYWORD_PROPERTY = "PROPERTY_";
	public static final String[] KEYWORDS_LIST = new String[] { DATATABLE_KEYWORD_EMPTY, DATATABLE_KEYWORD_RANDOM_ALPHABETIC,
			DATATABLE_KEYWORD_RANDOM_NUMERIC, DATATABLE_KEYWORD_RANDOM, DATATABLE_KEYWORD_GLOBAL, DATATABLE_KEYWORD_PROPERTY, };

	public static final int NO_MATCH_FOUND = -1;
	public static final int MAX_WORD_LENGTH = 27;
	public static final String SCENARIO_TIMEOUT_GLOBAL_NAME = "currentScenarioTimeout";
	public static final String EMPTY_SOFT_ASSERT_PROCESS_GLOBAL_NAME = "emptySoftAssertProcess";
}
