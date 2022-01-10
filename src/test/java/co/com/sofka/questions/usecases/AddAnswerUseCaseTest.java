package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.reposioties.QuestionRepository;
import co.com.sofka.questions.utils.SendEmail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.refEq;

class AddAnswerUseCaseTest {

    QuestionRepository questionRepository;
    AnswerRepository answerRepository;
    AddAnswerUseCase usecase;
    GetUseCase getCase;
    SendEmail sendEmail;

    @BeforeEach
    public void setup() {
        sendEmail = Mockito.mock(SendEmail.class);
        MapperUtils mapperUtils = new MapperUtils();
        questionRepository = Mockito.mock(QuestionRepository.class);
        answerRepository = Mockito.mock(AnswerRepository.class);
        getCase = new GetUseCase(mapperUtils, questionRepository, answerRepository);
        usecase = new AddAnswerUseCase(mapperUtils, getCase, answerRepository);
    }

    @Test
    public void addAnswerTest() {
        MapperUtils mapper = new MapperUtils();
        var answer = new Answer();
        answer.setUserId("userTest");
        answer.setQuestionId("questionTest");
        answer.setAnswer("ResponseTest");
        answer.setPosition(0);

        var answerdto = mapper.mapEntityToAnswer().apply(answer);
        var question = new Question();
        question.setId("questionId");
        question.setUserId("userTest");
        question.setQuestion("questionTest");
        question.setType("Tec");
        question.setCategory("Software");

        Mockito.when(questionRepository.findById(Mockito.any(String.class))).thenReturn(Mono.just(question));
        Mockito.when(answerRepository.save(Mockito.any(Answer.class))).thenReturn(Mono.just(answer));
        Mockito.when(answerRepository.findAllByQuestionId(Mockito.any(String.class))).thenReturn(Flux.empty());

        StepVerifier.create(usecase.apply(answerdto, "perrynikon@Gmail.com"))
                .expectNextMatches(questiondto -> {
                    assert questiondto.getId().equals("questionId");
                    assert questiondto.getUserId().equals("userTest");
                    assert questiondto.getQuestion().equals("questionTest");
                    assert questiondto.getType().equals("Tec");
                    assert questiondto.getCategory().equals("Software");
                    assert questiondto.getAnswers().contains(answerdto);
                    return true;
                })
                .verifyComplete();

        Mockito.verify(questionRepository).findById("questionTest");
        Mockito.verify(answerRepository).save(refEq(answer));


    }


}