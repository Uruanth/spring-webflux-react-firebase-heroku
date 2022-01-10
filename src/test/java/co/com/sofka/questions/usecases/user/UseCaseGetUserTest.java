package co.com.sofka.questions.usecases.user;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.User;
import co.com.sofka.questions.model.VotedAnswer;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.reposioties.UserRepository;
import co.com.sofka.questions.usecases.MapperUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.refEq;

class UseCaseGetUserTest {

    UserRepository userRepository;
    UseCaseGetUser usecase;

    @BeforeEach
    public void setup() {
        MapperUtils mapperUtils = new MapperUtils();
        userRepository = Mockito.mock(UserRepository.class);
        usecase = new UseCaseGetUser(userRepository, mapperUtils);
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

        Mockito.when(userRepository.findByUserId(Mockito.any(String.class))).thenReturn(Mono.just(userReturn));

        StepVerifier.create(usecase.apply(userId))
                .expectNextMatches(userdt -> {
                    assert userdt.getId().equals(userId);
                    assert userdt.getUserId().equals("userFrontId");
                    assert userdt.getName().equals("Dairon");
                    assert userdt.getImg().equals("imagen");
                    assert userdt.getEmail().equals("email");
                    assert userdt.getVotedAnswersId().contains(new VotedAnswer(answerId, 1));
                    return true;
                })
                .verifyComplete();

        Mockito.verify(userRepository, Mockito.times(1)).
                findByUserId(userId);


    }
}