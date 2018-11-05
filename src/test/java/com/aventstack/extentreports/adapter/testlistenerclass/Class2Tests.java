package com.aventstack.extentreports.adapter.testlistenerclass;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;

@Listeners({ExtentITestListenerClassAdapter.class})
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
