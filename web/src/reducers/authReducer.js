import * as actions from '../actions/authActions'

export const INITIAL_STATE = {
  email: null,
  uid: null,
  loading: false
}

export default function authReducer(state = INITIAL_STATE, action) {

  console.log(action)
  switch (action.type) {

    case actions.LOGIN:
      const payload = action.payload;
      return {
        ...state, email: payload.email, uid: payload.uid, loading: false
      };

    case actions.LOGOUT:
      return INITIAL_STATE;

    case actions.LOADING:
      return {
        ...state, loading: true
      };

    case actions.LOADED:
      return {
        ...state, loading: false
      };

    case actions.ERROR:
      console.log("actions.ERROR");
      return INITIAL_STATE;

    default:
      return state;
  }
}