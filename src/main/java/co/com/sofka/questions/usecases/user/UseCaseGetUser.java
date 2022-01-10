package co.com.sofka.questions.usecases.user;

import co.com.sofka.questions.model.UserDTO;
import co.com.sofka.questions.reposioties.UserRepository;
import co.com.sofka.questions.usecases.MapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service @Validated
public class UseCaseGetUser implements Function<String, Mono<UserDTO>> {

    private final UserRepository repository;
    private final MapperUtils mapper;

    public UseCaseGetUser(UserRepository repository, MapperUtils mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Mono<UserDTO> apply(String id) {
        return repository.findByUserId(id)
                .map(mapper.mapEntityToUser())
                .switchIfEmpty(Mono.defer(() -> {
                    throw new IllegalStateException("user not found");
                }))
                .onErrorStop();
        /*
         */
    }


}
