Talking Clock Coding Challenge

Dwaipayana Ray

Features
Provide time in Human friendly format with the help of a 1.REST Service 2.CLI

Request URL for REST service -
http://localhost:8080/talking-clock?time=HH:mm - for a custom time http://localhost:8080/talking-clock - for system time

To run the CLI Application
1.In a terminal/command prompt run 'java -jar talkingClock-<>.jar CLI' 
2.It will prompt 'Enter time in HH:mm format or press enter to display the current time:' 
3.Either provide a time and press enter or just press enter to get the system time.

Rest Example : 
Successful REST Request - GET http://localhost:8080/talking-clock?time=10:30 Response - { "value": "half past ten" }
Unsuccessful REST Request - GET http://localhost:8080/talking-clock?time=40:30 Response - { "status": 400,
    "message": "Invalid time format entered. Please provide a valid time in HH:mm format(00:00 - 23:59). }
