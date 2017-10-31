* How to compile and run:
	1 - Just to compile and run MainReport class:
	
		in the root folder
		to compile :
			javac -sourcepath src/main/java -d bin src/main/java/org/serkan/task/*.java
			
		to call: 
			cd bin
			java org/serkan/task/MainReporter <filepath for eaxmple ../resouces/testFiles.txt>
		
	2 - To compile with running the tests
		in the root folder :
			chmod 700 build.sh #make build executable
			sh build.sh
			