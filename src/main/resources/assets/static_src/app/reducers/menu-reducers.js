import * as types from '../actions/action-types';

const initialState={
    drinks: [],
    sizes: [],
    drinkTypes: []
};

const menuReducer = (state = initialState, action) => {
    switch(action.type){
        case types.UPDATE_DRINKS:
            return Object.assign({}, state, {
                drinks: action.drinks
            });
        case types.UPDATE_SIZES:
            return Object.assign({}, state, {
                sizes: action.sizes
            });
        case types.UPDATE_DRINK_TYPES:
            return Object.assign({}, state, {
                drinkTypes: action.drinkTypes
            });
    }
    return state;
}

export default menuReducer;
