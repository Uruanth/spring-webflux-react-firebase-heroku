import React from 'react'
import { signInWithGoogle } from '../helpers/auth'
import { useForm } from "react-hook-form";
import { useDispatch, useSelector } from 'react-redux';
import { updateUser } from '../actions/authActions';
import { useHistory } from "react-router-dom";

export default function UpdateUser() {

    const { register, formState: { errors }, handleSubmit } = useForm();
    const dispatch = useDispatch();
    const isLoading = useSelector(state => state.auth.loading);
    const img = useSelector(state => state.auth.img);
    const navigate = useHistory();


    const onSubmit = async (data) => {

        let imgURL = data.imgURL.length > 1 ? data.imgURL : img;
        let name = data.name;

        dispatch(updateUser(name, imgURL));
        navigate.push("/home")

    }

    const handleClick = (e) => {
        e.preventDefault();
        navigate.push("/")
    }


    return (
        <section>
            <h2>Update user</h2>
            <form onSubmit={handleSubmit(onSubmit)}>
                <div>
                    <input type="text" placeholder="Name"
                        {...register("name", { required: true, minLength: 5 })}
                    />
                    <p className="error-input">{errors.email && "Invalid email"}</p>
                </div>
                <div>
                    <input type="text" placeholder="URL new img"
                        {...register("imgURL", { minLength: 5 })}
                    />
                    <p className="error-input">{errors.password && "very short passwor"}</p>

                </div>


                <button className="button right mr" type="submit">
                    Confirm
                </button>
                <button className="button right mr"
                    onClick={(e) => handleClick(e)}
                >
                    Cancel
                </button>
            </form>


        </section>
    )
}
