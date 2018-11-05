package com.aventstack.extentreports.adapter.testlistener;

import org.testng.annotations.DataProvider;

public abstract class BaseDataProvider {

	@DataProvider(name = "Authentication")
	public static Object[][] credentials() {
		return new Object[][] { { "testuser_1", "Test@123" }, { "testuser_2", "Test@123" }};
	}
	
}
