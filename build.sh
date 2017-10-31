rm -rf bin
mkdir bin
javac -sourcepath src/main/java -d bin src/main/java/org/serkan/task/*.java
javac -cp :lib/*:bin:. -sourcepath src/test/java -d bin src/test/java/org/serkan/task/test/*.java
cd bin
java -cp ../lib/*:. org/serkan/task/test/TestRunner
