import React, {useEffect } from 'react'
import { signInWithGoogle } from '../helpers/auth'
import { useForm } from "react-hook-form";
import { useDispatch } from 'react-redux';
import { loaded, loading, login_with_user } from '../actions/authActions';
import { useSelector } from 'react-redux';
import { useHistory } from "react-router-dom";

export default function SignIn() {
    const { register, formState: {errors}, handleSubmit } = useForm();
    const dispatch = useDispatch();
    const isLoading = useSelector(state => state.auth.loading);
    const navigate = useHistory();


    const handleSignIn = (e) => {
        e.preventDefault();
        signInWithGoogle();
    }

    const onSubmit = (data) => {
        dispatch(loading());
        dispatch(login_with_user(data.email, data.password));
        dispatch(loaded());
    }

    const handleClick= (e) => {
        e.preventDefault();
        navigate.push("/signup")
    }

    return (
        <>
        
            <form onSubmit={handleSubmit(onSubmit)}>
                <div>
                    <input type="text" placeholder="Email" 
                    {...register("email", {required: true, minLength: 5})}
                    />
                    <p className="error-input">{errors.email && "Invalid email"}</p>
                </div>
                <div>
                    <input type="password" placeholder="Password" 
                    {...register("password", {required: true, minLength: 5})}
                    />
                    <p className="error-input">{errors.password && "very short passwor"}</p>

                </div>
                <button className="button right"
                    onClick={(e) => handleSignIn(e)}>
                    Sign in with google
                </button>
                <button className="button right mr"
                    type="submit"
                    disabled={isLoading}
                >
                    {isLoading ? "Wait" : "Login"}
                </button>
                <button className="button right mr"
                onClick={(e) => handleClick(e)}
                >
                    SignUp
                </button>
            </form>


        </>


    );
}
