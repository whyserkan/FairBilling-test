package org.serkan.task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class InputParser {
	
	private List<String> lines = new ArrayList<String>();
	
	public InputParser(String[] args) throws IOException, IllegalArgumentException {
		if(args==null || args.length==0){
			throw new IllegalArgumentException("Err: Please add a file name parameter.");
		}
		
		try {
			lines  = Files.readAllLines(Paths.get(args[0]));	
		} catch (IOException e) {
			String message = new StringJoiner(" -> ").add("Err: Can not find the file").add(args[0]).toString();
			throw new IOException(message);
		}
		
		
		if(lines.isEmpty()) {
			throw new InputMismatchException("Err: This file is empty.");
		}
	}
	
	public int getLinesSize() {
		return this.lines.size();
	}
	
	public List<UserData> getLogLines() {
		List<LogLine> logLines = filterLines();
		
		if(logLines.isEmpty()) {
			throw new IllegalArgumentException("Err: File doesnt have any suitable data.");
		}
		
		LocalTime max = logLines.get(logLines.size()-1).getTime();
		
		LocalTime min = logLines.get(0).getTime();
		
		List<UserData> userDataList = logLines.stream()
											  .collect(Collectors.groupingBy(LogLine::getUserName))
											  .entrySet()
											  .stream()
											  .map((user) -> calculateUserData(user, max, min))
											  .collect(Collectors.toList());

		return userDataList;
	}
	
	private List<LogLine> filterLines() {
		return this.lines.stream()
				   .filter(LogLine::validateLine)
				   .map(LogLine::new)
				   .collect(Collectors.toList());
	}
	
	private UserData calculateUserData(Entry<String, List<LogLine>> userEntry, LocalTime max, LocalTime min) {
		  UserData userData = new UserData(userEntry.getKey());
		  
		  userEntry.getValue()
		  		   .stream()
		  		   .forEach(userData::push);
		  
		  userData.calculateLeftOvers(max, min);
		  
		  return userData;
	}
	
	
}
