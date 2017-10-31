package org.serkan.task;

import java.io.IOException;

public class MainReporter {
	public static void main(String[] args) {
		
		try {
			
			InputParser inputParser = new InputParser(args);
			
			inputParser.getLogLines()
					   .stream()
					   .forEach(System.out::println); 
			
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
