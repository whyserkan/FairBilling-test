* How to compile and run:
	1 - Just to compile and run MainReport class:
	
		Go to root folder
		
		To compile :
			javac -sourcepath src/main/java -d bin src/main/java/org/serkan/task/*.java
			
		To call with example testFiles.txt:
			java -cp bin org.serkan.task.MainReporter resources/testFiles.txt
		
	2 - To compile with running the tests
		
		Go to root folder
		
			chmod 700 build.sh #make build executable if needed
			sh build.sh
			
* Error messages: 
	- If no file name given : 'Err: Please add a file name parameter.'
	- If file not found : 'Err: Can not find the file -> <file_name>'
	- If file is empty : 'Err: This file is empty.'			
	- If file doesn't have any readble data : 'Err: File doesnt have any suitable data.' 		