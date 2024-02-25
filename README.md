# Alfa Bank Online Exchange Rates
***
## Why was this project created?
The project makes it easier and more convenient to track Alfa Bank's exchange rates in real time.
## How does the service work?
***
The service directly receives information about currency rates and the most frequent exchanges made directly from the Alfa Bank API, which responds to your request in the form of a JSON array.
***
## How to start the service?
To start the service, you need to launch it on your computer and go to this endpoint:
````
localhost:8080/api/v1/exchange_rate
````
At the moment, it is also possible to request a specific currency name, but the result will be a hard-coded JSON object
````
localhost:8080/api/v1/exchange_rate/rates?name=USD
````
For example, a given URL request will receive a response in the form of a hard-coded JSON object called “USD”.
***
## SonarCloud
+ [![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=kirshunya_Java-Rest-Service&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=kirshunya_Java-Rest-Service)
+ [![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=kirshunya_Java-Rest-Service&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=kirshunya_Java-Rest-Service)
+ [![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=kirshunya_Java-Rest-Service&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=kirshunya_Java-Rest-Service)

[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=kirshunya_Java-Rest-Service)](https://sonarcloud.io/summary/new_code?id=kirshunya_Java-Rest-Service)