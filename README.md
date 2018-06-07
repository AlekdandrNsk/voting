# voting [![Codacy Badge](https://api.codacy.com/project/badge/Grade/0836fe0522a44c9bb98c353221e9cf24)](https://www.codacy.com/app/AlekdandrNsk/voting?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=AlekdandrNsk/voting&amp;utm_campaign=Badge_Grade)

Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.

The task is:

Build a voting system for deciding where to have lunch.

2 types of users: admin and regular users
Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
Menu changes each day (admins do the updates)
Users can vote on which restaurant they want to have lunch at
Only one vote counted per user
If user votes again the same day:
If it is before 11:00 we asume that he changed his mind.
If it is after 11:00 then it is too late, vote can't be changed
Each restaurant provides new menu each day.

As a result, provide a link to github repository.

It should contain the code and README.md with API documentation and curl commands to get data for voting and vote.


##Admin
#### get All Users
`curl -s http://localhost:8080/votingsystem/rest/admin/users --user admin@gmail.com:admin`
#### get User 100001
`curl -s http://localhost:8080/votingsystem/rest/admin/users/100001 --user admin@gmail.com:admin`
#### create User
'curl -s -X POST -d "{"name": "User77","email": "user77@yandex.ru","enabled": true,"password": "password","roles": ["ROLE_USER"]}" -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votingsystem/rest/admin/users --user admin@gmail.com:admin'
#### delete User 100000
`curl -s -X DELETE http://localhost:8080/votingsystem/rest/admin/users/100000 --user admin@gmail.com:admin`
#### get UserbyEmail   admin@gmail.com
`curl -s http://localhost:8080/votingsystem/rest/admin/users/by?email=admin@gmail.com --user admin@gmail.com:admin`
#### update User
'curl -s -X PUT -d http://localhost:8080/votingsystem/rest/admin/users/100000' -i -u 'admin@gmail.com:admin' -X PUT -H 'Content-Type: application/json;charset=UTF-8' -d '{"id" : 100000,"name" : "UpdatedName","email" : "user1@yandex.ru","password" : "password", "registered": "2018-06-07T13:21:32.637+0000","roles": ["ROLE_ADMIN","ROLE_USER"]}'
#### get All Restaurants
`curl -s http://localhost:8080/votingsystem/rest/admin/restaurants --user admin@gmail.com:admin`
#### get Restaurant 100002
`curl -s http://localhost:8080/votingsystem/rest/admin/restaurants/100002 --user admin@gmail.com:admin`
#### create Restaurant
`curl -s -H 'Content-Type:application/json;charset=UTF-8' -X POST -d "{"name": "Restaurant3"}"  http://localhost:8080/votingsystem/rest/admin/restaurants --user admin@gmail.com:admin`
#### update Restaurant100002
`curl -s -X PUT -d ' "id": 100003,"name": "RestaurantNew"' -H 'Content-Type: application/json' http://localhost:8080/votingsystem/rest/admin/restaurants/100002 --user admin@gmail.com:admin`
#### get Dish 100015
`curl -s http://localhost:8080/votingsystem/rest/admin/dishes/100015 --user admin@gmail.com:admin`
#### delete Dish 100015
`curl -s -X DELETE http://localhost:8080/votingsystem/rest/admin/dishes/100015 --user admin@gmail.com:admin`
#### create Dish
`curl -s -H 'Content-Type:application/json;charset=UTF-8' -X POST -d ""name": "soup1","restaurant":{"id": 100003,"name": "Restaurant1"},"price": 3.99,"date": "2018-06-07""  http://localhost:8080/votingsystem/rest/admin/dishes --user admin@gmail.com:admin`
#### update Dish100012
`curl -s -X PUT -d '{"id": 100012,"name": "coffee2","restaurant":{"id": 100003,"name": "Restaurant2"},"price": 333.99,"date": "2018-06-07"}' -H 'Content-Type: application/json' http://localhost:8080/votingsystem/rest/admin/dishes/100012 --user admin@gmail.com:admin`

##User
#### get AuthorizedUser
`curl -s http://localhost:8080/votingsystem/rest/profile --user user@yandex.ru:password`
#### delete AuthorizedUser
`curl -s -X DELETE http://localhost:8080/votingsystem/rest/profile --user user@yandex.ru:password`
#### update AuthorizedUser
`curl -s -X PUT -d '{"id": 100000,"name": "NewUser","email": "user@yandex.ru","enabled": true,"password": "password","registered": "2018-06-07T13:21:32.637+0000","roles": ["ROLE_USER"]}' -H 'Content-Type: application/json' http://localhost:8080/votingsystem/rest/profile --user user@yandex.ru:password`
#### getAllVotesByDay
`curl -s http://localhost:8080/votingsystem/rest/votes/?date=2018-06-07 --user user@yandex.ru:password`
#### create Vote
`curl -s -H 'Content-Type:application/json;charset=UTF-8' -X POST -d "{"date": "2018-06-07","restaurant":{"id": 100002,"name": "Restaurant1"}}"  http://localhost:8080/votingsystem/rest/votes --user user@yandex.ru:password`
#### update Vote100017
`curl -s -X PUT -d '{"id": 100017,"user": null,"date": "2018-06-07","restaurant":{"id": 100002,"name": "Restaurant1"}}' -H 'Content-Type: application/json' http://localhost:8080/votingsystem/rest/votes/100017 --user user@yandex.ru:password`
#### getAllDishesByDay
`curl -s http://localhost:8080/votingsystem/rest/dishes/?date=2018-06-07 --user user@yandex.ru:password`
