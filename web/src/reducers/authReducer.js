import * as actions from '../actions/authActions'
import auth from '../service/firebase'

export const INITIAL_STATE = {
  email: null,
  uid: null,
  img: null,
  name: null,
  loading: false
}

export default function authReducer(state = INITIAL_STATE, action) {

  switch (action.type) {
    case actions.LOGIN:
      const payload = action.payload;
      return {
        ...state, email: auth.currentUser.email,
          uid: payload.userId,
          img: payload.img,
          name: payload.name,
          loading: false
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
        ...state, uid: action.payload.userId, img: action.payload.img, email: auth.currentUser.email
      };
    case actions.UPDATE_USER:
      return {
        ...state,
          img: action.payload.img,
          name: action.payload.name,
          loading: false
      };

    case actions.ERROR:
      return state;

    default:
      return state;
  }
}