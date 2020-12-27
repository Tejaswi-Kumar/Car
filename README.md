# Car Recommendation System

The project is based on recommending the best set of cars to the user from the available list of cars.
The list of cars are scraped from a site and is stored in MongoDB.
The front end interface was designed using HTML and CSS. 
Data transfer from front end to backend and vice versa was managed by servlets.
The algorithm used was AHP(Analytic Hierarchy Process) to recommend the best set of cars to the user according to the preferences set by the users.
Java is used to run and connect the data from Mongodb and to process the preferences set by the user.
If the user isnt satisfied with the given set of cars recommended to them, they can reset and retry the recommendation process with the help of different preference settings.
