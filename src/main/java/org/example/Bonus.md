Bonus tema- implementare clasa pentru pasageri cu risc si pentru rute

In bonus m-am gandit la realizarea unei clase "Route" care sa contina informatii privind ruta 
aeronavei, precum si o clasa "Passenger" care sa contina informatii despre pasageri cu risc.
In ceea ce priveste clasa "Route", aceasta contine informatii despre aeroportul de plecare,
aeroportul de sosire, distanta dintre acestea, dar si timpul estimat de zbor. Aceasta clasa va 
contine o medota care decide la alocarea unei aeronave, daca aceasta poate efectua ruta sau nu, 
pe baza modelului aeronavei si a distantei dintre aeroporturi. De asemenea, aceasta clasa va
contine si o metoda care va calcula timpul estimat de zbor, pe baza distantei si a vitezei, dar si 
a conditiilor meteo. 

In ceea ce priveste clasa "Passenger", aceasta contine informatii despre pasageri cu risc, precum
numele, prenumele, varsta, dar si daca acestia sunt sau nu in situatie de urgenta. Aceasta clasa va
contine o metoda care va decide daca un pasager cu risc poate sau nu sa calatoreasca cu o anumita
aeronava, pe baza modelului aeronavei si a situatiei de urgenta a pasagerului. De asemenea, aceasta
clasa va contine si o metoda care va calcula costul total al calatoriei, pe baza costului biletului
si a costului suplimentar pentru pasagerii cu risc. In cadrul acestei metode, se va verifica daca 
pasagerul poate sau nu sa calatoreasca cu aeronava sau nu, pe baza lungimii calatoriei si a afectiunilor
medicale posibile ale pasagerului. De exemplu un pasager cu riscuri cardiace nu va putea calatori cu
aeronava pe distante lungi, deoarece acesta ar putea avea nevoie de ingrijiri medicale de urgenta.

Astfel in inputul proiectului se vor adauga comenzi precum:
- addRoute <departureAirport> <arrivalAirport> <distance> <estimatedTime>   
- addPassenger <firstName> <lastName> <age> <riskSituation>
- addPassengerToAirplane <airplaneId> <passengerId>
- calculateTotalCost <passengerId> <routeId>
- calculateEstimatedTime <routeId>
- checkIfAirplaneCanFlyRoute <airplaneId> <routeId>
- checkIfPassengerCanFlyAirplane <passengerId> <airplaneId>

Asadar in cadrul bonusului am realizat doua clase aditionale care sa aduca un plus de functionalitate
temei, acestea fiind "Route" si "Passenger". Acestea vor fi folosite in cadrul temei pentru a gestiona
mai eficient aeronavele si pasagerii cu risc, dar si pentru a oferi informatii suplimentare despre acestea.
```