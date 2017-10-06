import { combineReducers } from 'redux';
import menuReducer from './menu-reducers';
import orderReducer from './order-reducers';
import salesReducer from './sales-reducers';
import authReducer from './auth-reducers';


var reducers = combineReducers({
      menuState: menuReducer,
      orderState: orderReducer,
      salesState: salesReducer,
      authState: authReducer
});

export default reducers;
