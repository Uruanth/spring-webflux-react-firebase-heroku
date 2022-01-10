import firebase from "firebase/app";
import "firebase/firestore";
import "firebase/auth";

const firebaseConfig = {
    apiKey: "AIzaSyDIJLJsmSAJzJOFnJTyjEJ7L7y3NZ3RQwo",
    authDomain: "question-app-dairon.firebaseapp.com",
    projectId: "question-app-dairon",
    storageBucket: "question-app-dairon.appspot.com",
    messagingSenderId: "623378003719",
    appId: "1:623378003719:web:59ee327231d046ccdc77a2"
};

const app = firebase.initializeApp(firebaseConfig);
const auth = firebase.auth();
export default auth;