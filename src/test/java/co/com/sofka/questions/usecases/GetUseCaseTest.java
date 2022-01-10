package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.refEq;

class GetUseCaseTest {
    QuestionRepository questionRepository;
    AnswerRepository answerRepository;
    GetUseCase usecase;


    @BeforeEach
    public void setup() {
        MapperUtils mapperUtils = new MapperUtils();
        questionRepository = Mockito.mock(QuestionRepository.class);
        answerRepository = Mockito.mock(AnswerRepository.class);
        usecase = new GetUseCase(mapperUtils, questionRepository, answerRepository);
    }

    @Test
    public void addAnswerTest() {
        var questionId = "questionId";
        MapperUtils mapper = new MapperUtils();


        var answer = new Answer();
        answer.setId("answerId");
        answer.setUserId("userTest");
        answer.setQuestionId("questionTest");
        answer.setAnswer("ResponseTest");
        answer.setPosition(0);

        var answerdto = mapper.mapEntityToAnswer().apply(answer);
        var questionReturn = new Question();
        questionReturn.setId(questionId);
        questionReturn.setUserId("userTest");
        questionReturn.setQuestion("questionTest");
        questionReturn.setType("Tec");
        questionReturn.setCategory("Software");


        Mockito.when(questionRepository.findById(Mockito.any(String.class))).thenReturn(Mono.just(questionReturn));
        Mockito.when(answerRepository.findAllByQuestionId(Mockito.any(String.class))).thenReturn(Flux.just(answer));

        StepVerifier.create(usecase.apply(questionId))
                .expectNextMatches(questionDTO -> {

                    assert questionDTO.getId().equals(questionId);
                    assert questionDTO.getUserId().equals("userTest");
                    assert questionDTO.getQuestion().equals("questionTest");
                    assert questionDTO.getType().equals("Tec");
                    assert questionDTO.getCategory().equals("Software");
                    assert questionDTO.getAnswers().contains(answerdto);

                    return true;
                })
                .verifyComplete();

        Mockito.verify(questionRepository).findById(refEq(questionId));
        Mockito.verify(answerRepository).findAllByQuestionId(refEq(questionId));


    }

}