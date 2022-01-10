import axios from "axios";

//Constantes 
// const URL_BASE = 'http://localhost:8080';
const URL_BASE = 'https://app-question-dairon.herokuapp.com';


export const UPDATE_IMG_ANSWER = 'UPDATE_IMG_ANSWER';
export const ADD_VOTE_ANSWER = 'ADD_VOTE_ANSWER';
export const REMOVE_VOTE_ANSWER = 'REMOVE_VOTE_ANSWER';
export const prueba = 'prueba';
export const UPDATE_QUESTION = 'UPDATE_QUESTION';
const ERROR = 'ERROR';



//Acciones

export const addVote = (userId, answerId) => async (dispatch) => {
    try {
        const response = await axios.get(`${URL_BASE}/vote/${userId}/${answerId}`);
        dispatch({
            type: ADD_VOTE_ANSWER,
            payload: response
        })
    }catch (error) {}
}

export const removeVote = (userId, answerId) => async (dispatch) => {
    try {
        const response = await axios.get(`${URL_BASE}/remvoeVote/${userId}/${answerId}`);
        dispatch({
            type: REMOVE_VOTE_ANSWER,
            payload: response
        })
    }catch (error) {}
}


export const updateQuestion = (id) => async (dispatch) => {
    try {
        const response = await axios.get(`${URL_BASE}/get/${id}`);
        
        dispatch({
            type: UPDATE_QUESTION,
            payload: response.data
        })
    } catch (error) {
        
    }
}

export const updateImg = (userId) => async (dispatch) => {


    try {
        const response = await axios.get(`${URL_BASE}/user/${userId}`);
        dispatch({
            type: UPDATE_IMG_ANSWER,
            payload: response.data.img
        })
    }catch (error) {
        console.log("error", error)
    }
}
