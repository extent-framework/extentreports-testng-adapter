package com.aventstack.extentreports.adapter.testlistenerclass;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;

@Listeners({ExtentITestListenerClassAdapter.class})
public class Class1Tests extends BaseDataProvider {

	static {
		System.setProperty("extent.reporter.avent.start", "true");
		System.setProperty("extent.reporter.avent.config", "src/test/resources/com/aventstack/adapter/avent-config.xml");
	}
	
	@Test(dataProvider = "Authentication", groups = { "functest", "checkintest", "a:Anshoo", "d:sgs9+" })
	public void passClass1(String user, String password) {
		Assert.assertTrue(true);
	}
	
	@Test(dataProvider = "Authentication")
	public void failClass1(String user, String password) {
		Assert.assertTrue(false);
	}
	
}
