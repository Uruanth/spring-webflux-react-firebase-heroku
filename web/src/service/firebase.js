import firebase from "firebase/app";
import "firebase/firestore";
import "firebase/auth";

const firebaseConfig = {
    apiKey: "AIzaSyCTjAmzYsUpiDugfMUKqvSgY0nHf-h7-XM",
    authDomain: "app-question-dairon.firebaseapp.com",
    projectId: "app-question-dairon",
    storageBucket: "app-question-dairon.appspot.com",
    messagingSenderId: "188121249227",
    appId: "1:188121249227:web:6e7c0a9fd3c3598b3d324e"
  };


const app = firebase.initializeApp(firebaseConfig);
const auth = firebase.auth();
export default auth;