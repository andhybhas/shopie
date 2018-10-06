# dokumentation shopie

Status build: 

[![CircleCI](https://circleci.com/gh/robihidayat/shopie.svg?style=svg)](https://circleci.com/gh/robihidayat/shopie)


### Deployment 


##### Check out from github 

at this point, let's we clone repository and build dependencies
  
```sh
    $ git clone https://github.com/robihidayat/shopie.git
    $ cd shopie
    $ mvn clean package -DskipTests
```

we will skip unit test, because we not yet build postgres 

##### Build with docker-compose

this point, we will create images spring-boot and images database postgres with docker-compose. 

```sh
    $ docker-compose up --build
```


just it, and will deploy. check http://localhost:8080


### Documentation Api

This project was integrated with ci/cd CircleCI and Heroku, so we can access https://shopie.herokuapp.com, 
This project use swagger for documentation api. 

heroku:

https://shopie.herokuapp.com/swagger-ui.html#!/

localhost: 

http://localhost:8080/swagger-ui.html#!/


### Author

**Robi Hidayat**

* [github/robihidayat](https://github.com/robihidayat)

### License

Copyright © 2017, [Robi Hidayat](https://github.com/robihidayat).
Released under the [MIT License](LICENSE).

