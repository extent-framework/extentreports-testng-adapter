package com.aventstack.extentreports.adapter.testlistener;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerAdapter;

@Listeners({ExtentITestListenerAdapter.class})
public class Class1Tests extends BaseDataProvider {

	@Test(dataProvider = "Authentication", groups = { "functest", "checkintest" })
	public void passClass1(String user, String password) {
		Assert.assertTrue(true);
	}
	
	@Test(dataProvider = "Authentication")
	public void failClass1(String user, String password) {
		Assert.assertTrue(false);
	}
	
}
