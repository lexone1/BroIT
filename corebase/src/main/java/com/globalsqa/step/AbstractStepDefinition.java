package com.globalsqa.step;

import com.globalsqa.Globals;
import com.globalsqa.SoftAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.globalsqa.common.PropertiesManager;
import com.globalsqa.common.TestConfiguration;
import com.globalsqa.page.BaseByFactory;

/**
 * Implements the test automation functionality regarding Step Definition.
 *
 * @author Aliaksei Pershyts
 */
public class AbstractStepDefinition
{
	@Autowired
	protected PropertiesManager properties;

	@Autowired
	protected BaseByFactory by;

	@Autowired
	protected TestConfiguration testConfiguration;

	@Autowired
	protected SoftAssert softAssert;

	@Autowired
	protected Globals globals;
}
