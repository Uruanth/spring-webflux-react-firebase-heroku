import React, { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { addVote, removeVote, updateQuestion } from '../actions/answersActions'



export const Answer = ({ answer }) => {

  const qid = useSelector(state => state.question.question.id);
  const change = useSelector(state => state.question.change);
  const uid = useSelector(state => state.auth.uid);
  const dispatch = useDispatch();





  useEffect(() => {
    dispatch(updateQuestion(qid));
  }, [change])

  
  const add_vote = (e) => {
    console.log()
    dispatch(addVote(uid, answer.answerId))


  }

  const remove_vote = (e) => {
    dispatch(removeVote(uid, answer.answerId));

  }

  return (
    <>
      <aside className="answer">
        <p>{answer.answer}
        </p>

        <div>

          <p className="Ranking">{answer.position > 0 ? "Ranking: " + answer.position : "Ranking: 0"}</p>
          {
            uid ?
              <>
                <div className="button btn-vote"
                  onClick={(e) => remove_vote(e)}
                >
                  -
                </div>
                <div className="button btn-vote"
                  onClick={(e) => add_vote(e)}
                >
                  +
                </div>
              </>
              :
              null
          }
        </div>
      </aside>
    </>
  )
}


