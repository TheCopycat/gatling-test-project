[![Build Status](https://travis-ci.org/TheCopycat/gatling-test-project.svg?branch=master)](https://travis-ci.org/TheCopycat/gatling-test-project)

This is a sample Spring Boot/Maven Project with Gatling tests incorporated in build. For learning purpose.

# Run all with tests from Maven

This will start the jety server and then run the performance tests :

    mvn install
   
    
# Run from your IDE

It is faster than from Maven because your IDE already compiled all sources.

## Start the project

Run the main from ```com.worldline.formation.gatling.service.Application```

## Launch the gatling tests

Run the main from ```com.worldline.formation.gatling.service.FibonacciSimulation```
