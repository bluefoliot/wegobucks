import * as types from '../actions/action-types';

const initialState={
    status: false,
    order: ''
};

const orderReducer = (state = initialState, action) => {
    switch(action.type){
        case types.UPDATE_ORDER_STATUS:
            return Object.assign({}, state, {
                status: action.status,
                order: action.order
            });
    }
    return state;
}

export default orderReducer;
