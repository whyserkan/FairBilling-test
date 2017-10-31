package org.serkan.task;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class LogLine {
	private static final String LINE_PATTERN = "^\\d\\d:\\d\\d:\\d\\d \\S+ (Start|End)$";
	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	
	private LocalTime time;
	
	private String userName;
	
	private Type logType;
	
	private enum Type {
		End,
		Start
	};
	
	public static boolean validateLine(String line) {
		if(!Pattern.matches(LINE_PATTERN, line)) {
			return false;
		}
		try {
			LocalTime.parse(split(line)[0], DATE_FORMAT);	
		} catch (DateTimeParseException e) {
			return false;
		}
		
		return true;
	} 
	
	private static String[] split(String line) {
		String[] tokens = new String[3];
		StringTokenizer st = new StringTokenizer(line, " ");
		
		tokens[0] = st.nextToken();
		tokens[1] = st.nextToken();
		tokens[2] = st.nextToken();
		
		return tokens;
	}
	
	public LogLine(String line) {
		String[] tokens = LogLine.split(line);
		
		this.time = LocalTime.parse(split(line)[0], DATE_FORMAT);
		this.userName = tokens[1];
		this.logType = tokens[2].equals("End") ? Type.End : Type.Start;
	}
	
	private String TypeToString (Type type) {
		return type==Type.End ? "End" : "Start";
	}
	
	public String toString() {
		StringJoiner sj = new StringJoiner(" ");
		
		return sj.add(this.time.toString())
				 .add(this.userName)
				 .add(TypeToString(this.logType))
				 .toString();
	}

	public LocalTime getTime() {
		return time;
	}

	public String getUserName() {
		return userName;
	}

	public boolean typeIsStart() {
		return this.logType == Type.Start;
	}
}
