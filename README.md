# Trips

## Introduction

Model-View-Controler(MVC) application for managing a trasportation enterprise developed using Java and MySql. You can perform CRUD operations to the database for all entities in the program: Employees, Passengers, Luggage, Buses, Cities, Trips, Reservations and also perform screener type search on all available data. All comboboxes are updated performing queries to the database every time a new entry is created. 

![image](https://user-images.githubusercontent.com/93751091/150813724-70efcf94-ddd2-48e4-b371-2bd82d8cb43a.png)

## Configuration

To execute this project follow these steps:

- Clone the repository
- Open the reposiory in the IDE (Preferably NetBeans)
- Create the database in MySql (In the image below MySQL Workbench is being used for this purpose). Just copy ALL the content on file named "dataBaseCreation.sql" which can be found in the following route in the project: Trips/src/utils

![image](https://user-images.githubusercontent.com/93751091/150815569-00f7b688-8744-4ddf-bf46-6ccb90542246.png)
![image](https://user-images.githubusercontent.com/93751091/150815823-99bd67f2-5f6f-4b6e-ac6f-738adfc404e6.png)


- The Class "ConnectionDB" in package "utils" uses a .json file to read the data and then performs the data injection for the connection to the database. For this, open file "db_credentials.json" and insert your data base information:

![image](https://user-images.githubusercontent.com/93751091/150816272-3204ec39-14f2-40a5-950d-68fa2cf00568.png)

- Finally, execute the program in the IDE.

## Interfaces

![image](https://user-images.githubusercontent.com/93751091/150821242-c30fc8b0-b610-478b-a28e-4518dbe835a6.png)

![image](https://user-images.githubusercontent.com/93751091/150821291-299967e8-d21f-44f2-89d4-4cf07d3112e2.png)

![image](https://user-images.githubusercontent.com/93751091/150821373-b71ca3d7-196c-48ca-836d-713454d4c58c.png)

![image](https://user-images.githubusercontent.com/93751091/150821426-29985af5-3427-4472-a504-1e8d75969d06.png)

![image](https://user-images.githubusercontent.com/93751091/150821484-d94ebfca-45fe-46b7-a21c-66671dbb3a3f.png)

![image](https://user-images.githubusercontent.com/93751091/150821556-8110d2de-d584-413b-87aa-1dbc696da7d6.png)

![image](https://user-images.githubusercontent.com/93751091/150821610-b5a19a03-6db1-4d7f-adc0-9353035fb335.png)




