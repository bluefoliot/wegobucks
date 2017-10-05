import * as types from './action-types';

export function updateDrinks(drinks){
    return{
        type: types.UPDATE_DRINKS,
        drinks
    };
}

export function updateSizes(sizes){
    return{
        type: types.UPDATE_SIZES,
        sizes
    };
}

export function updateDrinkTypes(drinkTypes) {
  return {
    type: types.UPDATE_DRINK_TYPES,
    drinkTypes
  };
}
