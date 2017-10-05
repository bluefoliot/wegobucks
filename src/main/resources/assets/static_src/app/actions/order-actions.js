import * as types from './action-types';

export function updateOrderStatus(order, status){
    return{
        type: types.UPDATE_ORDER_STATUS,
        order,
        status
    };
}
