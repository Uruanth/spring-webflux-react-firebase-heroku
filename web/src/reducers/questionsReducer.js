import * as actions from '../actions/questionActions'
import * as answerActions from '../actions/answersActions'

export const initialState = {
  loading: false,
  hasErrors: false,
  questions: [],
  question: {},
  redirect: null,
  img: null,
  change: false
}



export default function questionsReducer(state = initialState, action) {

  switch (action.type) {
    case actions.LOADING:
      return {
        ...state, loading: true
      };
    case actions.LOADED_SUCCESS:
      return {
        ...state, ...action.payload, loading: false, hasErrors: false
      };
    case actions.LOADED_FAILURE:
      return {
        ...state, loading: false, hasErrors: true
      };
    case answerActions.ADD_VOTE_ANSWER:
      return {
        ...state, loading: false, change: true
      };
    case answerActions.REMOVE_VOTE_ANSWER:
      return {
        ...state, loading: false, change: true
      };

    case answerActions.UPDATE_QUESTION:
      return {
        ...state, question: action.payload, change: false, loading: false
      };
    case actions.UPDATE_IMG:
      return {
        ...state, questions: action.payload.questions
      };
    case answerActions.UPDATE_IMG_ANSWER:
      return {
        ...state, img: action.payload, loading: false
      };
    case actions.RESET_IMG:
      return {
        ...state, img: null, loading: false
      };
    default:
      return {...state, loading: false}
  }
}