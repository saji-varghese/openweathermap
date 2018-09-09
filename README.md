# Openweathermap

**Endpoints**

End point to retrieve weekly forecast for given city, this will show summary for next 5 days 
http://127.0.0.1:8080/weather/weekly/{city}

http://127.0.0.1:8080/weather/weekly/London

End point to retrieve forecast for given date and city. This will return forecast for a given date. 3 hrs interval
http://127.0.0.1:8080/weather/day/{city}/{date} ISO format YYYY-mm-dd

http://127.0.0.1:8080/weather/day/London/2018-09-14

**Minimum requirement to run**

JDK 1.8 or later
Maven 3.2+

**To run** 

mvn package && java -jar target/openweathermap-0.0.1-SNAPSHOT.jar

