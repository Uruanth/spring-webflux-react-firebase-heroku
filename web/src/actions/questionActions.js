import axios from 'axios'
import auth from '../service/firebase'


const URL_BASE = 'http://localhost:8080';

export const LOADING = 'LOADING'
export const LOADED_SUCCESS = 'LOADED_SUCCESS'
export const LOADED_FAILURE = 'LOADED_FAILURE'
export const UPDATE_IMG = 'UPDATE_IMG';
export const RESET_IMG = 'RESET_IMG';




export const loading = () => ({
    type: LOADING
})

export const success = payload => ({
    type: LOADED_SUCCESS,
    payload
});

export const failure = () => ({
    type: LOADED_FAILURE
})

export function fetchQuestions() {
    return async dispatch => {
        dispatch(loading())
        try {
            const response = await fetch(
                `${URL_BASE}/getAll`
            )
            const data = await response.json()
            dispatch(success({
                questions: data,
                redirect: null
            }))
        } catch (error) {
            dispatch(failure())
        }
    }
}

export function fetchOwnerQuestions(userId) {
    return async dispatch => {
        dispatch(loading())
        try {
            const response = await fetch(`${URL_BASE}/getOwnerAll/${userId}`)
            const data = await response.json()
            dispatch(success({
                questions: data,
                redirect: null
            }))
        } catch (error) {
            dispatch(failure())
        }
    }
}

export function fetchQuestion(id) {
    return async dispatch => {
        dispatch(loading())
        try {
            const response = await fetch(`${URL_BASE}/get/${id}`)
            const data = await response.json()
            dispatch(success({
                question: data,
                redirect: null
            }))
        } catch (error) {
            console.log("error")
            dispatch(failure())
        }
    }
}

export function postQuestion(question, navigate) {
    return async dispatch => {
        dispatch(loading())
        try {
            const response = await fetch(`${URL_BASE}/create`, {
                method: 'POST',
                mode: 'cors',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(question)
            })
            const id = await response.text()
            navigate.push(`/question/${id}`);

        } catch (error) {
            dispatch(failure())
        }
    }
}

export function deleteQuestion(id) {
    return async dispatch => {
        dispatch(loading())
        try {
            await fetch(`${URL_BASE}/delete/${id}`, {
                method: 'DELETE',
                mode: 'cors',
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            dispatch(success({
                redirect: `/list`
            }));
        } catch (error) {
            dispatch(failure())
        }
    }
}

export function postAnswer(answer) {


    return async dispatch => {

        dispatch(loading())
        try {
            let userId = answer.emailId;
            const response = await axios.get(`${URL_BASE}/user/${userId}`)
            const email = response.data.email;

            await fetch(`${URL_BASE}/add/${email}`,
                {
                    method: 'POST',
                    mode: 'cors',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(answer)
                }
            )
            dispatch(success({
                redirect: `/question/${answer.questionId}`
            }));
        } catch (error) {
            dispatch(failure())
        }
    }

}


export const updateImg = (userId) => async (dispatch, state) => {
    try {
        const response = await axios.get(`${URL_BASE}/user/${userId}`);
        var lista = state().question.questions;

        lista.filter(question => question.userId == userId)
            .forEach(question => question.img = response.data.img);

        dispatch({
            type: UPDATE_IMG,
            payload: {
                questions: lista,
                userId: userId
            }
        })
    } catch (error) {}
}

export const resetImg = () => (dispatch) => {
    dispatch({
        type: RESET_IMG
    })
}