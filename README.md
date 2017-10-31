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
```
/drinks : GET method. Return the price list.
/drink-sizes : GET method. Return all drink sizes.
/drink-types : GET method. Return all drink types.
```

### Order
```
/orders : POST method. Add an order. Require name slug and size slug in JSON on the request body, i.e {"name": "espresso", "size": "tall"}.
/orders?type={type}&size={size} : GET method, only authenticated user. Return all orders, use filter as needed.
```
## Example
You can access the app on https://wegobucks.herokuapp.com.

## Built with
* [Dropwizard](http://www.dropwizard.io/1.2.0/docs/)
* [Maven](https://maven.apache.org/)
* [React.js](https://reactjs.org/)

