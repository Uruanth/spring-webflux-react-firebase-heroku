package co.com.sofka.questions.usecases.user;

import co.com.sofka.questions.model.UserDTO;
import co.com.sofka.questions.reposioties.UserRepository;
import co.com.sofka.questions.usecases.MapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class UseCaseNewUser implements Function<UserDTO, Mono<UserDTO>> {
    private final UserRepository userRepository;
    private final MapperUtils mapper;

    public UseCaseNewUser(UserRepository userRepository, MapperUtils mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public Mono<UserDTO> apply(UserDTO userDTO) {
        return userRepository.findByUserId(userDTO.getUserId())
                .map(mapper.mapEntityToUser())
                .switchIfEmpty(userRepository
                        .save(mapper.mapperToUser().apply(userDTO))
                        .map(mapper.mapEntityToUser()));

        /*
         return userRepository.save(mapper.mapperToUser().apply(userDTO))
                .map(mapper.mapEntityToUser());
         */
    }
}
