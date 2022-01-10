package co.com.sofka.questions.usecases.user;

import co.com.sofka.questions.collections.User;
import co.com.sofka.questions.model.VotedAnswer;
import co.com.sofka.questions.reposioties.UserRepository;
import co.com.sofka.questions.usecases.MapperUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.refEq;

class UseCaseNewUserTest {

    UserRepository userRepository;
    MapperUtils mapper;
    UseCaseNewUser usecase;

    @BeforeEach
    public void setup() {
        userRepository = Mockito.mock(UserRepository.class);
        mapper = new MapperUtils();
        usecase = new UseCaseNewUser(userRepository, mapper);

    }

    @Test
    void newUserTest() {


        var user = new User();
        user.setUserId("idFront");
        user.setName("Dairon");
        user.setImg("imagen");
        user.setEmail("email");

        var userdto = mapper.mapEntityToUser().apply(user);
        var userSave = mapper.mapperToUser().apply(userdto);
        var userReturn = mapper.mapperToUser().apply(userdto);
        userReturn.setId("userId");

        Mockito.when(userRepository.findByUserId(Mockito.any(String.class))).thenReturn(Mono.empty());
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(Mono.just(userReturn));

        StepVerifier.create(usecase.apply(userdto))
                .expectNextMatches(userdt -> {
                    assert userdt.getId().equals("userId");
                    assert userdt.getUserId().equals("idFront");
                    assert userdt.getName().equals("Dairon");
                    assert userdt.getImg().equals("imagen");
                    assert userdt.getEmail().equals("email");

                    assert userdt.getQuestionsId().isEmpty();
                    assert userdt.getAnswersId().isEmpty();
                    assert userdt.getVotedQuestionsId().isEmpty();
                    assert userdt.getVotedAnswersId().isEmpty();

                    return true;
                })
                .verifyComplete();

        Mockito.verify(userRepository).findByUserId("idFront");
        Mockito.verify(userRepository).save(refEq(userSave));
    }

}