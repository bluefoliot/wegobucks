import * as orderActions from '../actions/order-actions';
import * as salesActions from '../actions/sales-actions';
import * as authActions from '../actions/auth-actions';
import axios from 'axios';

const urlPrefix = '/api';

export function placeOrder(drinkSlug, sizeSlug) {
  return dispatch => {
    axios.post(urlPrefix+'/order/add', {
      drink: drinkSlug,
      size: sizeSlug
    }).then(
      response => dispatch(orderActions.updateOrderStatus(response.data, 'success'))
    ).catch(
      response => dispatch(orderActions.updateOrderStatus('', 'fail'))
    );
  }
}

export function loadAllSales() {
  return dispatch => {
    axios.get(urlPrefix+'/order/all').then(
      response => {
        if(response.status === 401) {
          dispatch(authActions.promptLogin(true))
        } else {
          dispatch(authActions.promptLogin(false))
          dispatch(salesActions.updateSalesReport(response.data))
        }
      }
    );
  }
}

export function loadSalesBySize(size) {
  return dispatch => {
    axios.get(urlPrefix+'/order/size/'+size).then(
      response => {
        if(response.status === 401) {
          dispatch(authActions.promptLogin(true))
        } else {
          dispatch(authActions.promptLogin(false))
          dispatch(salesActions.updateSalesReport(response.data))
        }
      }
    );
  }
}

export function loadSalesByType(type) {
  return dispatch => {
    axios.get(urlPrefix+'/order/type/'+type).then(
      response => {
        if(response.status === 401) {
          dispatch(authActions.promptLogin(true))
        } else {
          dispatch(authActions.promptLogin(false))
          dispatch(salesActions.updateSalesReport(response.data))
        }
      }
    );
  }
}
