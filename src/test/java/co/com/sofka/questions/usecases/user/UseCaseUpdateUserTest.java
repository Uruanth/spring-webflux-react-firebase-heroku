package co.com.sofka.questions.usecases.user;

import co.com.sofka.questions.collections.User;
import co.com.sofka.questions.reposioties.UserRepository;
import co.com.sofka.questions.usecases.MapperUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.refEq;

class UseCaseUpdateUserTest {

    UserRepository userRepository;
    MapperUtils mapper;
    UseCaseUpdateUser usecase;

    @BeforeEach
    public void setup() {
        userRepository = Mockito.mock(UserRepository.class);
        mapper = new MapperUtils();
        usecase = new UseCaseUpdateUser(userRepository, mapper);

    }

    @Test
    void newUserTest() {


        var user = new User();
        user.setId("userId");
        user.setUserId("idFront");
        user.setName("Dairon");
        user.setImg("imagen");
        user.setEmail("email");

        var userUpdate = new User();
        userUpdate.setId("userId");
        userUpdate.setUserId("idFront");
        userUpdate.setName("otroNombre");
        userUpdate.setImg("otraImagen");
        userUpdate.setEmail("email");

        var userdto = mapper.mapEntityToUser().apply(user);
        var userFind = mapper.mapperToUser().apply(userdto);
        var userTemporal = mapper.mapEntityToUser().apply(userUpdate);
        var userReturn = mapper.mapperToUser().apply(userTemporal);

        Mockito.when(userRepository.findByUserId(Mockito.any(String.class))).thenReturn(Mono.just(userFind));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(Mono.just(userReturn));

        StepVerifier.create(usecase.apply(userTemporal))
                .expectNextMatches(userdt -> {
                    assert userdt.getId().equals("userId");
                    assert userdt.getUserId().equals("idFront");
                    assert userdt.getName().equals("otroNombre");
                    assert userdt.getImg().equals("otraImagen");
                    assert userdt.getEmail().equals("email");

                    assert userdt.getQuestionsId().isEmpty();
                    assert userdt.getAnswersId().isEmpty();
                    assert userdt.getVotedQuestionsId().isEmpty();
                    assert userdt.getVotedAnswersId().isEmpty();

                    return true;
                })
                .verifyComplete();

        Mockito.verify(userRepository).findByUserId("idFront");
        Mockito.verify(userRepository).save(refEq(userReturn));


    }
}