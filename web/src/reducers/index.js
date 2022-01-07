// import { combineReducers } from 'redux'
import questionsReducer from './questionsReducer';
import authReducer from './authReducer';

import {
    createStore,
    combineReducers,
    compose,
    applyMiddleware
} from 'redux'
import thunk from 'redux-thunk'


const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

const rootReducer = combineReducers({
    question: questionsReducer,
    auth: authReducer
})

// export default rootReducer;

export default function generateStore() {
     return createStore(rootReducer,
        composeEnhancers(applyMiddleware(thunk)))

}