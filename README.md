# stock-analytics

Notes:
This application is a fully functioning rest api that calculates data from quandl.

I used HashMaps for O(1) lookups.
This allows the runtime to be O(N).

I made the api key, api url (quandl) and the threshold for busy day volume configurable under application.properties.

I used BigDecimal since it is more precise and it is recommended to use this Data Type when dealing with currencies, especially when dividing (averages).
	Averages are returned, rounded to 2 decimal places.

Setup:
Open with Spring Tool Suite (a flavor of Eclipse). 
	I'm using 3.9.4.RELEASE version.
Install Lombok: https://projectlombok.org/, this is for the @Data, @Getter, @Setter, @Builder annotations.

Open STS

Click on File -> Import
Select existing maven projects.
Browse to "pricing" folder, and import pom.xml, click finish.

Right click the project, click Maven -> Update Project.
Right click the project, click Run As -> Maven Install.

Wait for dependencies to be downloaded to your .m2 repository.

Find the "Boot Dashboard" section in IDE.
Expand "local"
Right click "pricing" and click "(Re)Start"

Wait for app to start up.

Open up browser.
Type in: http://localhost:8080/coding-exercise

Or http://localhost:8080/monthly-average?stocks=COF,GOOG,MSFT&month-start=2017-01&month-end=2017-06

Both do the same thing.
