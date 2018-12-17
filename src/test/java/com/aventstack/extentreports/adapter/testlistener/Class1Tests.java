package com.aventstack.extentreports.adapter.testlistener;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerAdapter;

@Listeners({ExtentITestListenerAdapter.class})
public class Class1Tests extends BaseDataProvider {
	
	@BeforeSuite
	public void BeforeSuite() {
		System.out.println("Before Suite");
		throw new SkipException("Skip Suite");
	}

	@Test(dataProvider = "Authentication", groups = { "functest", "checkintest" })
	public void passClass1(String user, String password) {
		Assert.assertTrue(true);
	}
	
	@Test(dataProvider = "Authentication")
	public void failClass1(String user, String password) {
		Assert.assertTrue(false);
	}
	
	@Test
	public void SkipClass1() {
		System.out.println("Inside Skip Test");
		throw new SkipException("Skip Class1 Test skipped");
	}
	
}
