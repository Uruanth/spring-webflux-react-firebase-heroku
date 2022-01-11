package co.com.sofka.questions.usecases.user;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.collections.User;
import co.com.sofka.questions.model.VotedAnswer;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.reposioties.QuestionRepository;
import co.com.sofka.questions.reposioties.UserRepository;
import co.com.sofka.questions.usecases.AddAnswerUseCase;
import co.com.sofka.questions.usecases.GetUseCase;
import co.com.sofka.questions.usecases.MapperUtils;
import co.com.sofka.questions.utils.SendEmail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.ArgumentMatchers.refEq;

class RemoveVoteUseCaseTest {

    UserRepository userRepository;
    AnswerRepository answerRepository;
    UseCaseGetUser getCase;

    RemoveVoteUseCase usecase;

    @BeforeEach
    public void setup() {
        MapperUtils mapperUtils = new MapperUtils();
        userRepository = Mockito.mock(UserRepository.class);
        answerRepository = Mockito.mock(AnswerRepository.class);
        getCase = new UseCaseGetUser(userRepository, mapperUtils);
        usecase = new RemoveVoteUseCase(userRepository, answerRepository, getCase, mapperUtils);
    }

    @Test
    public void addAnswerTest() {
        MapperUtils mapper = new MapperUtils();

        var userId = "userId";
        var answerId = "answerId";

        var newUser = new User();
        newUser.setId(userId);
        newUser.setUserId("userFrontId");
        newUser.setName("Dairon");
        newUser.setImg("imagen");
        newUser.setEmail("email");

        var userdto = mapper.mapEntityToUser().apply(newUser);
        userdto.setVotedAnswersId(List.of(new VotedAnswer(answerId, 1)));

        var userReturn = mapper.mapperToUser().apply(userdto);

        userdto.setVotedAnswersId(List.of(new VotedAnswer(answerId, -1)));
        var userVoted = mapper.mapperToUser().apply(userdto);

        var answer = new Answer();
        answer.setId(answerId);
        answer.setUserId("userTest");
        answer.setQuestionId("questionTest");
        answer.setAnswer("ResponseTest");
        answer.setPosition(5);


        Mockito.when(userRepository.findByUserId(Mockito.any(String.class))).thenReturn(Mono.just(userReturn));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(Mono.just(userVoted));
        Mockito.when(answerRepository.save(Mockito.any(Answer.class))).thenReturn(Mono.just(answer));
        Mockito.when(answerRepository.findById(Mockito.any(String.class))).thenReturn(Mono.just(answer));

        StepVerifier.create(usecase.apply(userId,answerId))
                .expectNextMatches(a -> {
                    assert a.equals(answerId);
                    return true;
                })
                .verifyComplete();

        Mockito.verify(userRepository, Mockito.times(2)).findByUserId(userId);
        Mockito.verify(userRepository).save(refEq(userVoted));
        Mockito.verify(answerRepository, Mockito.times(2)).save(refEq(answer));
        Mockito.verify(answerRepository, Mockito.times(2)).findById(refEq(answerId));



    }

}