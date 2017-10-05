import { combineReducers } from 'redux';
import menuReducer from './menu-reducers';
import orderReducer from './order-reducers';
import salesReducer from './sales-reducers';


var reducers = combineReducers({
      menuState: menuReducer,
      orderState: orderReducer,
      salesState: salesReducer
});

export default reducers;
