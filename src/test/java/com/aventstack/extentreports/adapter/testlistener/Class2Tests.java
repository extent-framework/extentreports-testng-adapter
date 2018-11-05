package com.aventstack.extentreports.adapter.testlistener;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerAdapter;

@Listeners({ExtentITestListenerAdapter.class})
public class Class2Tests extends BaseDataProvider {

	@Test
	public void passClass2() {
		Assert.assertTrue(true);
	}
	
	@Test(groups = { "functest", "checkintest" })
	public void failClass2() {
		Assert.assertTrue(false);
	}
	
}
