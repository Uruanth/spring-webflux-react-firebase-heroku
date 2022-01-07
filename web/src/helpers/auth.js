import auth from '../service/firebase'
import firebase from "firebase/app";


export function signup(email, password) {
    return auth.createUserWithEmailAndPassword(email, password);
};


export function signin(email, password) {
    return auth.signInWithEmailAndPassword(email, password);
};

export function signInWithGoogle() {
    const provider = new firebase.auth.GoogleAuthProvider();;
    return auth.signInWithPopup(provider);
};

