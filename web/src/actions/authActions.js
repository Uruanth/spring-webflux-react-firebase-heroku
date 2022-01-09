import {
    signin,
    signup
} from '../helpers/auth'

import auth from '../service/firebase'
import axios from 'axios'
// Constantes
const URL_BASE = 'http://localhost:8080';

export const LOGIN = 'LOGIN';
export const LOGOUT = 'LOGOUT';

export const LOADING = 'LOADING';
export const LOADED = 'LOADED';
export const LOGIN_WITH_USER = 'LOGIN_WITH_USER';
export const ERROR = 'ERROR';
export const SIGNUP = 'SIGNUP';
export const NEW_USER = 'NEW_USER';




//Acciones

export const login = () => async (dispatch) => {
    let user = {
        "userId": auth.W,
        "name": auth.currentUser.displayName || auth.currentUser.email,
        "img": auth.currentUser.photoURL || "./imagen.jpg"
    }

    try {
        let data;
        await axios.post(`${URL_BASE}/user`, user)
            .then((response) => {
                console.log("response2", response);
               data = response.data;
            })

            dispatch({
                type: LOGIN,
                payload: data
            });

    } catch (error) {
        console.log("error", error)
    }

}

// export const login = (email, uid) => ({
//     type: LOGIN,
//     payload: {
//         email,
//         uid
//     }
// });

export const logout = () => ({
    type: LOGOUT
});


export const loading = () => ({
    type: LOADING
})

export const loaded = () => ({
    type: LOADED
});

export const error = () => ({
    type: ERROR
})

export const login_with_user = async (email, password) => {

    let action = {
        type: ERROR
    };
    await signin(email, password)
        .then(response => {
            action = {
                type: LOGIN_WITH_USER,
                payload: {
                    email,
                    password
                }
            };
        })
    return action;

}

export const newUser = (email, password) => async (dispatch) => {
    let data;
    try {
        await signup(email, password)
            .then(response => {
                console.log(response.user.email);
                action = {
                    type: SIGNUP,
                    payload: {
                        email,
                        password
                    }
                };
            });
        dispatch({
            type: SIGNUP,
        })
    } catch (error) {}
}

export const newUserDB = () => async (dispatch) => {
    let user = {
        "userId": auth.W,
        "name": auth.currentUser.displayName || auth.currentUser.email,
        "img": auth.currentUser.photoURL || "./imagen.jpg"
    }

    try {
        let data;
        await axios.post(`${URL_BASE}/user`, user)
            .then((response) => {
                console.log("response2", response);
               data = response.data;
            })

            dispatch({
                type: NEW_USER,
                payload: data
            });

    } catch (error) {
        console.log("error", error)
    }

    console.log(user);
}