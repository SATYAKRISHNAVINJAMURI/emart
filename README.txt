For Gradle run the following commands to compile & execute

1) Place input.txt in the build\libs folder after building the project

gradlew clean build to build the project
java -jar <path_to emart.jar>/emart.jar


NOTE: 
1) emart.jar is usuall in the build\libs folder of root project directory place the input.txt in this folder.
2) You can either use "gradle clean build" or "gradlew clean build" for gradle build.


For Gradle run the following command to execute unit tests

gradle test or gradlew test
