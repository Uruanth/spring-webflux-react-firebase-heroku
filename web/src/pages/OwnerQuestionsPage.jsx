import React, { useEffect } from 'react'
import { useSelector, useDispatch, connect } from 'react-redux'

import { fetchOwnerQuestions, deleteQuestion } from '../actions/questionActions'
import { Question } from '../components/Question'



const OwnerQuestionsPage = () => {
    const questions = useSelector(state => state.question)
    const auth = useSelector(state => state.auth)

    const dispatch2 = useDispatch()
    useEffect(() => {
        dispatch2(fetchOwnerQuestions(auth.uid))
    }, [dispatch2, auth.uid]);



    const onDelete = (id) => {
        dispatch2(deleteQuestion(id))
    }


    const renderQuestions = () => {
        if (questions.loading) return <p>questions.loading questions...</p>
        if (questions.hasErrors) return <p>Unable to display questions.</p>

        return questions && questions.questions.map(question => <Question
            key={question.id}
            question={question}
            excerpt onDelete={onDelete} />)
    }

    return (
        <section>
            <h1>Questions</h1>
            {renderQuestions()}
        </section>
    )
}

export default OwnerQuestionsPage;
