# atg-automation

1. This project is used to automate web application of ATG and API testing of Petstore using Java, Selenium Web Driver,
   Rest assured and Maven
2. Configurable properties have been defined in the application.properties file
3. It can be tested by running
    1. “mvn test” command in the project main folder where pom.xml is present
    2. “mvn surefire-report:report” command in the project main folder where pom.xml is present
       1. Path to the unit test report is “/target/site/surefire-report.html” 
    3. “MainApplication” class which will schedule the cron job for triggering the tests
4. Check the solution document in doc directory for all details