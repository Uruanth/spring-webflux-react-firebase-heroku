package co.com.sofka.questions.usecases.user;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.User;
import co.com.sofka.questions.model.VotedAnswer;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.reposioties.UserRepository;
import co.com.sofka.questions.usecases.MapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Service
@Validated
public class RemoveVoteUseCase implements BiFunction<String, String, Mono<String>> {

    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;
    private final UseCaseGetUser getUser;
    private final MapperUtils mapper;

    public RemoveVoteUseCase(UserRepository userRepository, AnswerRepository answerRepository, UseCaseGetUser getUser, MapperUtils mapper) {
        this.userRepository = userRepository;
        this.answerRepository = answerRepository;
        this.getUser = getUser;
        this.mapper = mapper;
    }

    @Override
    public Mono<String> apply(String userId, String answersId) {
        return assignVote(userId, answersId)
                .flatMap(user -> getAnswersUser(user.getUserId()))
                .map(lista -> lista.stream()
                        .filter(answer -> answer.getAnswerId().equals(answersId))
                        .collect(Collectors.toList())
                )
                .map(lista -> lista.stream().findFirst().orElse(new VotedAnswer("-a", 0)))
                .flatMap(answer -> removeVote(answersId))
                .flatMap(answer -> removeVote(answer.getId()))
                .map(answer -> answer.getId())
                .switchIfEmpty(newAnswers(answersId, userId));
    }

    private Mono<List<VotedAnswer>> getAnswersUser(String userId) {
        return getUser.apply(userId).flatMap(user -> Mono.just(user.getVotedAnswersId()));
    }

    private Mono<Answer> getAnswersId(String answersId) {
        return answerRepository.findById(answersId);
    }

    private Mono<Answer> addVote(String answersId) {
        return getAnswersId(answersId)
                .flatMap(answer -> {
                    answer.setPosition(answer.getPosition() - 1);
                    return answerRepository.save(answer);
                });
    }

    private Mono<Answer> removeVote(String answersId) {
        return
                getAnswersId(answersId)
                        .flatMap(answer -> {
                            answer.setPosition(answer.getPosition() - 1);
                            return answerRepository.save(answer);
                        });
    }


    private Mono<String> newAnswers(String answerId, String userId) {
        return userRepository.findByUserId(userId)
                .flatMap(user -> {
                    var list = new ArrayList<VotedAnswer>();
                    user.getVotedAnswersId().stream().forEach(list::add);
                    if (list.contains(new VotedAnswer(answerId, -1))) {
                       return Mono.empty();
                    }
                    list.add(new VotedAnswer(answerId, -1));
                    user.setVotedAnswersId(list);
                    return userRepository.save(user);
                })
                .flatMap(user -> addVote(answerId))
                .map(answer -> answer.getId())
                .switchIfEmpty(Mono.defer(() -> {
                    throw new IllegalArgumentException("user not found");
                }));

    }


    public Mono<User> assignVote(String userId, String answerid) {
        return userRepository.findByUserId(userId)
                .flatMap(user -> {
                    var uu = user.getVotedAnswersId()
                            .stream()
                            .filter(votedAnswer -> votedAnswer.getAnswerId().equals(answerid))
                            .filter(votedAnswer -> votedAnswer.getVote()>-1)
                            .map(votedAnswer -> {
                                votedAnswer.setVote(-1);
                                return votedAnswer;
                            })
                            .collect(Collectors.toList());
                    return uu.size() > 0 ? userRepository.save(user) : Mono.empty();
                });
    }

}