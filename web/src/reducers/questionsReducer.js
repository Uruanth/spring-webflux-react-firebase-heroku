import * as actions from '../actions/questionActions'
import * as answerActions from '../actions/answersActions'

export const initialState = {
  loading: false,
  hasErrors: false,
  questions: [],
  question: {},
  redirect: null,
  change: false
}



export default function questionsReducer(state = initialState, action) {
  // console.log("question reduccer", action);

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
        ...state, loading: true, change: true
      };
    case answerActions.REMOVE_VOTE_ANSWER:
      return {
        ...state, loading: true, change: true
      };

    case answerActions.UPDATE_QUESTION:
      return {
        ...state, question: action.payload, change: false
      };

    default:
      return state
  }
}