import * as types from '../actions/action-types';

const initialState={
    sales: []
};

const salesReducer = (state = initialState, action) => {
    switch(action.type){
        case types.UPDATE_SALES_REPORT:
            return Object.assign({}, state, {
                sales: action.sales
            });
    }
    return state;
}

export default salesReducer;
