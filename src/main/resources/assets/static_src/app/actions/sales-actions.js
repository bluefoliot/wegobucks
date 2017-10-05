import * as types from './action-types';

export function updateSalesReport(sales){
    return{
        type: types.UPDATE_SALES_REPORT,
        sales
    };
}
