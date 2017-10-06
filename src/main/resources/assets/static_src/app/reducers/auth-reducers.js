import * as types from '../actions/action-types';

const initialState={
    promptLogin: true
};

const authReducer = (state = initialState, action) => {
    switch(action.type){
        case types.PROMPT_LOGIN:
            return Object.assign({}, state, {
                promptLogin: action.promptLogin
            });
    }
    return state;
}

export default authReducer;
