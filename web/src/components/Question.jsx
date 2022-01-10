import React, { useEffect } from 'react'
import { Link } from 'react-router-dom'

import { useDispatch, useSelector } from 'react-redux';
import { updateImg } from '../actions/questionActions';


export const Question = ({ question, excerpt, onDelete }) => {

  const dispatch = useDispatch();
  const img = useSelector(state => state.question.img);

  useEffect(() => {
    dispatch(updateImg(question.userId));
  }, [])


  return (
    <article className={excerpt ? 'question-excerpt' : 'question'}>
      <div className="block-il">
        <img src={img || question.img || "/imagenes/user.png"} className="img-user" />
      </div>
      <div className="block-il">
        <h2>{question.question}</h2>
        <p onClick={()=>handleCategory(question.category)}>{question.category}  - <small>{question.type}</small></p>

        {onDelete && (
          <button className="button right" onClick={() => onDelete(question.id)}>DELETE</button>
        )}
        {excerpt && (
          <Link to={`/question/${question.id}`} className="button">
            View Question
          </Link>
        )}
      </div>
    </article>
  )
}
