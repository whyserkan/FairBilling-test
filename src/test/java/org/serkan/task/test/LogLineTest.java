package org.serkan.task.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.serkan.task.LogLine;

public class LogLineTest {
	   @Test 
	   public void test_if_validate_line_returns_false()
	   {
		   assertFalse("wrong type", LogLine.validateLine("14:02:03 ALICE99 Startxx"));
		   assertFalse("wrong date", LogLine.validateLine("25:02:03 ALICE99 Start"));
		   assertFalse("wrong white space", LogLine.validateLine("14:02:03  ALICE99 Start"));
		   assertFalse("extra white space", LogLine.validateLine("14:02:03 ALI CE99 Start"));
		   assertFalse("wrong type 2", LogLine.validateLine("14:02:03 ALICE99 END"));
	   }
	   
	   @Test 
	   public void test_if_validate_line_returns_true()
	   {
		   assertTrue("Error parsing line", LogLine.validateLine("14:02:03 ALICE99 Start"));
		   assertTrue("Error parsing line", LogLine.validateLine("14:02:03 XXXX End"));
	   } 
	   
	   @Test 
	   public void test_if_validate_parsed_successfully()
	   {
		   assertEquals("14:02:03 ALICE99 Start", new LogLine("14:02:03 ALICE99 Start").toString());
	   }  	   
}
