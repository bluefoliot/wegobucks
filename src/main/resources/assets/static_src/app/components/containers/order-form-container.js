import React from 'react';
import {connect} from 'react-redux';
import * as drinkApi from '../../api/drink-api';
import * as orderApi from '../../api/order-api';
import OrderForm from '../views/order-form';

const OrderFormContainer = React.createClass({
  getInitialState: function() {
      return {
        drink: '',
        drinkSlug: '',
        sizeSlug: '',
        price: ''
      };
  },

  componentDidMount: function(){
    this.props.dispatch(drinkApi.loadAllDrinks());
  },

  placeOrder: function() {
    this.props.dispatch(orderApi.placeOrder(this.state.drinkSlug, this.state.sizeSlug));
  },

  setDrink: function(event) {
    const drinkSlug = event.target.value;
    this.setState({drinkSlug: drinkSlug});
    this.setState({sizeSlug: ''});
    this.setState({price: ''});
    this.updateDrink(drinkSlug);
  },

  updateDrink: function(drinkSlug) {
    this.props.drinks.forEach(drink => {
      if(drink.slug==drinkSlug) {
        this.setState({drink: drink});
      }
    });
  },

  setSize: function(event) {
    const sizeSlug = event.target.value;
    this.setState({sizeSlug: sizeSlug});
    this.updateSize(sizeSlug);
  },

  updateSize: function(sizeSlug) {
    this.state.drink.priceList.forEach(price => {
      if(sizeSlug==price.sizeSlug) {
        this.setState({price: price.price});
      }
    });
  },

  render: function(){
    return (
      <OrderForm
        {...this.props}
        {...this.state}
        setDrink={this.setDrink}
        setSize={this.setSize}
        placeOrder={this.placeOrder} />
    );
  }
});

const mapStateToProps = store => {
    return {
        drinks: store.menuState.drinks,
        order: store.orderState.order,
        orderStatus: store.orderState.status
    };
}

export default connect(mapStateToProps)(OrderFormContainer);
