package org.serkan.task;

import java.io.IOException;
import java.util.StringJoiner;

public class MainReporter {
	public static void main(String[] args) {
		
		try {
			InputParser inputParser = new InputParser(args);
			
			inputParser.getLogLines()
					   .stream()
					   .forEach(System.out::println); 
			
		} catch (IllegalArgumentException | IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(new StringJoiner(" ").add("Err: unexpected error").add(e.getMessage()));
		}
	}
}
