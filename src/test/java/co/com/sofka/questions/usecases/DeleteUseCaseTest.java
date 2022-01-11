package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.refEq;

class DeleteUseCaseTest {
    QuestionRepository questionRepository;
    AnswerRepository answerRepository;
    DeleteUseCase usecase;


    @BeforeEach
    public void setup() {
        questionRepository = Mockito.mock(QuestionRepository.class);
        answerRepository = Mockito.mock(AnswerRepository.class);
        usecase = new DeleteUseCase(answerRepository, questionRepository);
    }

    @Test
    public void deleteAnswerTest() {
        var id = "deleteId";

        Mockito.when(questionRepository.deleteById(Mockito.any(String.class))).thenReturn(Mono.empty());
        Mockito.when(answerRepository.deleteByQuestionId(Mockito.any(String.class))).thenReturn(Mono.empty());

        StepVerifier.create(usecase.apply(id))
                .verifyComplete();

        Mockito.verify(questionRepository).deleteById(refEq(id));
        Mockito.verify(answerRepository).deleteByQuestionId(refEq(id));


    }

}