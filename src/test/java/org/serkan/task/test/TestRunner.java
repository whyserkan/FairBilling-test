package org.serkan.task.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(InputParserTest.class, LogLineTest.class);
		
		if (result.wasSuccessful()) {
			System.out.println("All the " + result.getRunCount()+" tests passed!");
			return;
		}
		
		result.getFailures()
		  .stream()
		  .forEach(System.out::println);		
	}
}


