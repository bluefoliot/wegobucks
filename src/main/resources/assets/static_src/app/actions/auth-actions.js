import * as types from './action-types';

export function promptLogin(promptLogin){
    return{
        type: types.PROMPT_LOGIN,
        promptLogin
    };
}
