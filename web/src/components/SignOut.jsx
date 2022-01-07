import React from 'react'
import { useDispatch } from 'react-redux';
import { logout } from '../actions/authActions';
import auth from '../service/firebase'

export default function SignOut() {
    const dispatch = useDispatch();

    return (
        auth.currentUser && (
            <button
                className="button right"
                onClick={() => {
                    dispatch(logout())
                    auth.signOut();
                }}
            >
                Sign out
            </button>
        )
    );
}