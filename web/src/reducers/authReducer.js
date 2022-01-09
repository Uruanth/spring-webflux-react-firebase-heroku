import * as actions from '../actions/authActions'
import auth from '../service/firebase'

export const INITIAL_STATE = {
  email: null,
  uid: null,
  img: null,
  loading: false
}

export default function authReducer(state = INITIAL_STATE, action) {
  console.log("auth reduccer", action);
  switch (action.type) {

    case actions.LOGIN:
      const payload = action.payload;
      return {
        ...state, email: auth.currentUser.email, uid: payload.userId, img: payload.img, loading: false
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

    case actions.NEW_USER:

      return {
         ...state ,uid: action.payload.userId, img: action.payload.img, email: auth.currentUser.email
      };

    case actions.ERROR:
      return state;

    default:
      return state;
  }
}