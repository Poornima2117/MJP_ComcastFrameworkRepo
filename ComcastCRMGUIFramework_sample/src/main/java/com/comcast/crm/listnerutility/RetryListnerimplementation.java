package com.comcast.crm.listnerutility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListnerimplementation implements IRetryAnalyzer{

	int count=0;
	int limtcount =5;
	public boolean retry(ITestResult result) {
		if(count<limtcount) {
			count++;
			return 
					true;
		}
		return false;
	}

}
