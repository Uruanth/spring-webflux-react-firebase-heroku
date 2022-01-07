import {
    signin,
    signup
} from '../helpers/auth'

// Constantes
export const LOGIN = 'LOGIN';
export const LOGOUT = 'LOGOUT';

export const LOADING = 'LOADING';
export const LOADED = 'LOADED';
export const LOGIN_WITH_USER = 'LOGIN_WITH_USER';
export const ERROR = 'ERROR';
export const SIGNUP = 'SIGNUP';





//Acciones

export const login = (email, uid) => ({
    type: LOGIN,
    payload: {
        email,
        uid
    }
});

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
            console.log("error");
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

export const newUser = async (email, password) => {
    console.log('SignUp', email, password);

    let action = {
        type: ERROR
    };
    try {
        await signup(email, password)
            .then(response => {
                console.log("then");
                action = {
                    type: SIGNUP,
                    payload: {
                        email,
                        password
                    }
                };
            })

    } catch (error) {

    }
    console.log(action);
    return action;


}