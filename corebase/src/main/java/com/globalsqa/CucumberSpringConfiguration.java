package com.globalsqa;

import org.springframework.test.context.ContextConfiguration;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(locations = { "classpath:cucumber.xml" })
public class CucumberSpringConfiguration
{
}
