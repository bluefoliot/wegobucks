import * as menuActions from '../actions/menu-actions';
import axios from 'axios';

const urlPrefix = '/api';

export function loadAllDrinks() {
  return dispatch => {
    axios.get(urlPrefix+'/drinks').then(
      response => dispatch(menuActions.updateDrinks(response.data))
    );
  }
}

export function loadAllSizes() {
  return dispatch => {
    axios.get(urlPrefix+'/drink-sizes').then(
      response => dispatch(menuActions.updateSizes(response.data))
    );
  }
}

export function loadAllTypes() {
  return dispatch => {
    axios.get(urlPrefix+'/drink-types').then(
      response => dispatch(menuActions.updateDrinkTypes(response.data))
    );
  }
}
