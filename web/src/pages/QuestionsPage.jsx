import React, { useEffect, useState } from 'react'
import { connect, useDispatch, useSelector } from 'react-redux'

import { fetchQuestions, resetImg } from '../actions/questionActions'
import { Question } from '../components/Question'


const QuestionsPage = ({ dispatch, loading, questions, hasErrors }) => {
    const dispat = useDispatch();
    const ques = useSelector(state => state.question.questions)
    const [lista, setLista] = useState(ques);


    const handleList = (e) => {
        e.preventDefault();
        let search = e.target.value;
        let list = questions
        if (search.length > 0) {
            list = ques.filter(question => question.category.includes(search.toUpperCase()))
        }
        setLista(list)
    }



    useEffect(() => {
        dispat(resetImg());
        dispatch(fetchQuestions());
        setLista(ques);
    }, [dispatch, ques.length]);

    const renderQuestions = () => {
        if (loading) return <p>Loading questions...</p>
        if (hasErrors) return <p>Unable to display questions.</p>

        if (lista.length > 0) return lista.map(question => <Question key={question.id} question={question} excerpt />)
        return questions.map(question => <Question key={question.id} question={question} excerpt />)
    }

    return (
        <section>
            <h1>Questions</h1>
            <div>
                <input type="text" placeholder="Category" className="input-group-text"
                    onChange={(e) => handleList(e)} />
            </div>
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
