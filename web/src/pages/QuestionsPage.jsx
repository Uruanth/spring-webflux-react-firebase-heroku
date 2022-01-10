import React, { useEffect } from 'react'
import { connect, useDispatch } from 'react-redux'

import { fetchQuestions, resetImg } from '../actions/questionActions'
import { Question } from '../components/Question'


const QuestionsPage = ({ dispatch, loading, questions, hasErrors }) => {
    const dispat = useDispatch();

    
    useEffect(() => {
        dispat(resetImg());
        dispatch(fetchQuestions())
    }, [dispatch])

    const renderQuestions = () => {
        if (loading) return <p>Loading questions...</p>
        if (hasErrors) return <p>Unable to display questions.</p>

        return questions.map(question => <Question key={question.id} question={question} excerpt />)
    }

    return (
        <section>
            <h1>Questions</h1>
            {renderQuestions()}
        </section>
    )
}

const mapStateToProps = state => ({
    loading: state.question.loading,
    questions: state.question.questions,
    hasErrors: state.question.hasErrors,
})

export default connect(mapStateToProps)(QuestionsPage)
