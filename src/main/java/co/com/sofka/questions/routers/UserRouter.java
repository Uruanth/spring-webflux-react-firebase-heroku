package co.com.sofka.questions.routers;

import co.com.sofka.questions.model.UserDTO;
import co.com.sofka.questions.usecases.user.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> createUser(UseCaseNewUser usecase) {
        return route(
                POST("/user").and(accept(MediaType.APPLICATION_JSON)),
                request ->

                        request.bodyToMono(UserDTO.class)
                                .flatMap(newUserDTO -> usecase.apply(newUserDTO))
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))



        );
    }


    @Bean
    public RouterFunction<ServerResponse> updateUser(UseCaseUpdateUser usecase) {
        return route(
                POST("/user/update").and(accept(MediaType.APPLICATION_JSON)),
                request ->

                        request.bodyToMono(UserDTO.class)
                                .flatMap(newUserDTO -> usecase.apply(newUserDTO))
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))



        );
    }



    @Bean
    public RouterFunction<ServerResponse> getUserByUserId(UseCaseGetUser usecase) {
        return route(
                GET("/user/{userId}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                usecase.apply(request.pathVariable("userId")),
                                UserDTO.class
                        ))

        );

    }

    @Bean
    public RouterFunction<ServerResponse> votedAnswer(VotedAnswerUseCase usecase) {
        return route(
                GET("/vote/{userId}/{answerId}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                usecase.apply(request.pathVariable("userId"),
                                        request.pathVariable("answerId")),
                                String.class
                        ))
                        .onErrorResume(error -> ServerResponse.badRequest().build())
        );
    }

    @Bean
    public RouterFunction<ServerResponse> removeVote(RemoveVoteUseCase usecase) {
        return route(
                GET("/remvoeVote/{userId}/{answerId}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                usecase.apply(request.pathVariable("userId"),
                                        request.pathVariable("answerId")),
                                String.class

                        ))
        );
    }


}
