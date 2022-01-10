package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.refEq;

class UpdateUseCaseTest {

    QuestionRepository questionRepository;
    UpdateUseCase usecase;


    @BeforeEach
    public void setup() {
        MapperUtils mapperUtils = new MapperUtils();
        questionRepository = Mockito.mock(QuestionRepository.class);
        usecase = new UpdateUseCase(mapperUtils, questionRepository);
    }

    @Test
    public void addAnswerTest() {
        MapperUtils mapper = new MapperUtils();

        var question = new Question();
        question.setId("questionSave");
        question.setUserId("userTest");
        question.setQuestion("otroDato");
        question.setType("CambioTyoe");
        question.setCategory("Software");

        var questionReturn = new Question();
        questionReturn.setId("questionSave");
        questionReturn.setUserId("userTest");
        questionReturn.setQuestion("otroDato");
        questionReturn.setType("CambioTyoe");
        questionReturn.setCategory("Software");

        var questiondto = mapper.mapEntityToQuestion().apply(question);

        Mockito.when(questionRepository.save(Mockito.any(Question.class))).thenReturn(Mono.just(questionReturn));

        StepVerifier.create(usecase.apply(questiondto))
                .expectNext("questionSave")
                .verifyComplete();

        Mockito.verify(questionRepository).save(refEq(question));


    }

}