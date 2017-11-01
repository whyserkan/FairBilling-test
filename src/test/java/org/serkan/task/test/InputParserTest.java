package org.serkan.task.test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.InputMismatchException;
import static org.junit.Assert.*;

import org.junit.Test;
import org.serkan.task.InputParser;

public class InputParserTest {
	
	   private static final String TEST_FILE_NAME = "file_for_testing.txt";
	
	   @Test (expected=IllegalArgumentException.class)
	   public void test_if_non_existing_file_parameter_throws_error() throws IOException
	   {
		   String[] emptyArgs = new String[0];
		   InputParser inputParser = new InputParser(emptyArgs);
	   }
	   
	   @Test (expected=IOException.class)
	   public void test_if_not_found_throw_exception() throws IOException
	   {
		   String[] files = new String[1];
		   files[0] = "this_file_is_not_here";
		   InputParser inputParser = new InputParser(files);
	   }	
	   
	  /* @Test (expected=InputMismatchException.class)
	   public void test_if_file_is_empty_throw_exception() throws IOException
	   {
		   createEmptyFile();
		   
		   String[] files = new String[1];
		   files[0] = TEST_FILE_NAME;
		   InputParser inputParser = new InputParser(files);
		   
		   removeFile();
	   }
	   
	   @Test 
	   public void test_example_test_file_has_correct_line_number() throws IOException
	   {
		   String[] lines = {
				   "line 0",
				   "line 1",
				   "line 2",
				   "line 3"
		   };
		   
		   writeToFile(lines);
		   
		   String[] files = new String[1];
		   files[0] = TEST_FILE_NAME;
		   InputParser inputParser = new InputParser(files);
		   
		   assertEquals(4, inputParser.getLinesSize()); 
		   
		   removeFile();
	   }*/
	   
	   @Test (expected=IllegalArgumentException.class)
	   public void test_example_test_file_has_incorrect_data_throws_exception() throws IOException
	   {
		   String[] lines = {
				   "line 0",
				   "line 1",
				   "line 2",
				   "line 3",
				   ""
		   };
		   
		   writeToFile(lines);
		   
		   String[] files = new String[1];
		   files[0] = TEST_FILE_NAME;
		   InputParser inputParser = new InputParser(files);
		   
		   inputParser.getLogLines(); 
		   
		   removeFile();
	   }   
	   
	   private void writeToFile(String[] lines) throws IOException {
		   Files.write(Paths.get(TEST_FILE_NAME), Arrays.asList(lines), Charset.defaultCharset(), StandardOpenOption.CREATE);
	   }
	   
	   private void createEmptyFile() throws IOException{
		   Files.write(Paths.get(TEST_FILE_NAME), new byte[0], StandardOpenOption.CREATE);
	   }
	   
	   private void removeFile() throws IOException {
		   Files.deleteIfExists(Paths.get(TEST_FILE_NAME));
	   }
}
