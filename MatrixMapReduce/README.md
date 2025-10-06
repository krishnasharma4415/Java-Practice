$env:HADOOP_CLASSPATH = "lib\*"; 
mkdir -Force classes,output; 
javac -source 1.8 -target 1.8 -classpath $env:HADOOP_CLASSPATH -d classes src\MatrixAddition.java; 
jar -cvf output\MatrixAddition.jar -C classes .; 
Remove-Item classes\* -Force; 
javac -source 1.8 -target 1.8 -classpath $env:HADOOP_CLASSPATH -d classes src\MatrixMultiplication.java; 
jar -cvf output\MatrixMultiplication.jar -C classes .; 
Remove-Item classes\* -Force; javac -source 1.8 -target 1.8 -classpath $env:HADOOP_CLASSPATH -d classes src\MatrixTranspose.java; 
jar -cvf output\MatrixTranspose.jar -C classes .