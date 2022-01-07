import React from 'react'
import { signInWithGoogle } from '../helpers/auth'
import { useForm } from "react-hook-form";
import { useDispatch, useSelector } from 'react-redux';
import { newUser, loading, loaded } from '../actions/authActions';
import { useHistory } from "react-router-dom";

export default function SignUp() {
    const { register, formState: { errors }, handleSubmit } = useForm();
    const dispatch = useDispatch();
    const isLoading = useSelector(state => state.auth.loading);
    const navigate = useHistory();

    const handleSignIn = (e) => {
        e.preventDefault();
        signInWithGoogle();
    }

    const onSubmit = (data) => {
        console.log(data);
        dispatch(loading());
        dispatch(newUser(data.email, data.password));
        dispatch(laoded());
    }

    const handleClick= (e) => {
        e.preventDefault();
        navigate.push("/signip")
    }

    return (
        <>
        <h2>New User</h2>
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
                <button className="button right mr" type="submit">
                    {isLoading ? "Wait" : "Confirm"}
                </button>
                <button className="button right mr"
                onClick={(e) => handleClick(e)}
                >
                    SignIn
                </button>
            </form>


        </>
    )
}
