package com.aventstack.extentreports.adapter.testlistener;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerAdapter;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ExtentITestListenerAdapter.class})
public class DescriptionTests extends BaseDataProvider {

	@Test
	public void passClass2() {
		Assert.assertTrue(true);
	}

	@Test(description = "This is a test description")
	public void passClass2Description() {
		Assert.assertTrue(true);
	}

	@Test(groups = { "functest", "checkintest" })
	public void failClass2() {
		Assert.assertTrue(false);
	}

	@Test(groups = { "functest", "checkintest" }, description = "This is a test description")
	public void failClass2Description() {
		Assert.assertTrue(false);
	}
	
}
