import React, { useEffect } from 'react'
import { useSelector } from 'react-redux'
import { Link } from 'react-router-dom'
import auth from '../service/firebase'




export const PublicNavbar = () => (
  <nav>
    <section>
      <Link to="/">Home</Link>
      <Link to="/questions">Questions</Link>
      <Link to="/signup">Signup</Link>
    </section>
  </nav>
)



export const PrivateNavbar = () => {

  const user = useSelector(state => state.auth);


  return (
    <nav>
      <section>
        <Link to="/">Home</Link>
        <Link to="/questions">Questions</Link>
        <Link to="/new">New</Link>
        <Link to="/list">List</Link>
        <Link to="/update">Update user</Link>
      </section>
      <img src={user.img || auth.currentUser.photoURL || "imagenes/user.png"} className="img-user" />
      <span className="fs-3 name-user">{user.name || "Usuario"}</span>
    </nav>
  )
}
