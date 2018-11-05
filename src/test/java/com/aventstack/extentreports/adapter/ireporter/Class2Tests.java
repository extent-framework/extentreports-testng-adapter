package com.aventstack.extentreports.adapter.ireporter;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.testng.listener.ExtentIReporterSuiteClassListenerAdapter;

@Listeners({ExtentIReporterSuiteClassListenerAdapter.class})
public class Class2Tests {

	@Test
	public void passClass2() {
		Assert.assertTrue(true);
	}
	
	@Test
	public void failClass2() {
		Assert.assertTrue(false);
	}
	
}
