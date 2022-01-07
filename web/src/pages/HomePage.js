import React from 'react'
import { Link } from 'react-router-dom'

const HomePage = ({children}) => (
  <section>
    <h1>Home</h1>
    <p>welcome to the question and answer app.</p>
    <div>
      {children}
    </div>
    <Link to="/questions" className="button">
      View Questions
    </Link>
  
  </section>
)
export default HomePage
