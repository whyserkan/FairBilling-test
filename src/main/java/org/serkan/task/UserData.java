package org.serkan.task;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.StringJoiner;

public class UserData {
	private int sessionCount = 0;
	private int sessionTime = 0;
	private String userName = "";
	
	public UserData(String userName) {
		this.userName = userName;
	}
	
	private LinkedList<LogLine> userStack = new LinkedList<LogLine>();
	
	public void calculateLeftOvers(LocalTime max, LocalTime min) {
		while (!this.userStack.isEmpty()) {
			LogLine logline = this.userStack.pop();
			
		    if (logline.typeIsStart()) {
		    	sessionTime += logline.getTime().until(max, ChronoUnit.SECONDS);
		    }else{
		    	sessionTime += min.until(logline.getTime(), ChronoUnit.SECONDS);
		    }
		    
		    sessionCount++;
		}
	}

	public int getSessionCount() {
		return sessionCount;
	}

	public int getSessionTime() {
		return sessionTime;
	}

	public String getUserName() {
		return this.userName;
	}
	
	public LinkedList<LogLine> getUserStack() {
		return userStack;
	}
	
	public UserData push(LogLine logline) {
		boolean newEndCanBeCalculatedWithLastStart = !logline.typeIsStart() && !this.userStack.isEmpty() && this.userStack.peek().typeIsStart();
		
		if (newEndCanBeCalculatedWithLastStart) {
			LocalTime lastStart = this.userStack.pop().getTime();
	        
	        this.sessionTime += lastStart.until(logline.getTime(), ChronoUnit.SECONDS);
	        this.sessionCount++;
		} else {
			this.userStack.push(logline);	
		}
		return this;
	}
	
	public String toString(){
		return new StringJoiner(" ").add(this.userName)
									.add(String.valueOf(this.sessionCount))
									.add(String.valueOf(this.sessionTime))
									.toString();
	}
}
