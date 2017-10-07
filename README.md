# Wegobucks

A simple web app for a coffee shop.

## Getting started

To run this project on your local machine, you will need
* [Java](https://www.java.com/en/download/help/download_options.xml)
* [Postgresql](http://postgresguide.com/setup/install.html)
* [Maven](https://maven.apache.org/install.html)
* [NPM](https://docs.npmjs.com/cli/install)
* [Bower](https://bower.io/#install-bower)
* [Grunt](https://gruntjs.com/installing-grunt)

Clone this project to your local machine, and run start.sh <config.yaml file>. The app is accesible at localhost:8080.

## API

URL prefix for the api is /api. There are three types of API on this app.

### Drink
URL prefix is /drink.
```
/all : GET method. Return the price list.
/sizes : GET method. Return all drink sizes.
/types : GET method. Return all drink types.
```

### Order
URL prefix is /order.
```
/add : POST method. Add an order. Require name slug and size slug in JSON on the request body, i.e {"name": "espresso", "size": "tall"}.
/all : GET method, only authenticated user. Return all orders made.
/type/{type}: GET method, only authenticated user. Return orders grouped by the said type.
/size/{size}: GET method, only authenticated user. Return orders grouped by the said size.
```

### Authentication
URL prefix is /auth
```
/login : POST method. Acquire authentication token. Require name in JSON on the request body, i.e {"name": "admin"}.
```

## Example
You can access the app on https://wegobucks.herokuapp.com.

## Built with
* [Dropwizard](http://www.dropwizard.io/1.2.0/docs/)
* [Maven](https://maven.apache.org/)
* [React.js](https://reactjs.org/)

