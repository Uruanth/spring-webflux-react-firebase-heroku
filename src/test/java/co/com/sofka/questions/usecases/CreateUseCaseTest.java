package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.refEq;

class CreateUseCaseTest {

    QuestionRepository questionRepository;
    CreateUseCase usecase;


    @BeforeEach
    public void setup() {
        MapperUtils mapperUtils = new MapperUtils();
        questionRepository = Mockito.mock(QuestionRepository.class);
        usecase = new CreateUseCase(mapperUtils,questionRepository);
    }

    @Test
    public void addAnswerTest() {
        MapperUtils mapper = new MapperUtils();

        var question = new Question();
        question.setUserId("userTest");
        question.setQuestion("questionTest");
        question.setType("Tec");
        question.setCategory("Software");

        var questionReturn = new Question();
        questionReturn.setId("questionSave");
        questionReturn.setUserId("userTest");
        questionReturn.setQuestion("questionTest");
        questionReturn.setType("Tec");
        questionReturn.setCategory("Software");

        var questiondto = mapper.mapEntityToQuestion().apply(question);

        Mockito.when(questionRepository.save(Mockito.any(Question.class))).thenReturn(Mono.just(questionReturn));

        StepVerifier.create(usecase.apply(questiondto))
                .expectNext("questionSave")
                .verifyComplete();

        Mockito.verify(questionRepository).save(refEq(question));


    }

}