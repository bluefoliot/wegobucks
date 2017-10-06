import * as authActions from '../actions/auth-actions';

import axios from 'axios';

const urlPrefix = '/api';

export function login(name) {
  return dispatch => {
    axios.post(urlPrefix+'/auth/login', {name: name}).then(
      response => dispatch(authActions.promptLogin(false))
    ).catch(
      response => dispatch(authActions.promptLogin(true))
    );
  }
}
