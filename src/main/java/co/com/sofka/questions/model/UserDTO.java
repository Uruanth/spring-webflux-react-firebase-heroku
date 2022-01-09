package co.com.sofka.questions.model;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDTO {
    private String id;
    @NotBlank
    private String userId;
    @NotBlank
    private String name;
    @NotBlank
    private String img;
    private List<String> questionsId;
    private List<String> answersId;
    private List<String> votedQuestionsId;
    private List<VotedAnswer> votedAnswersId;

    public UserDTO() {
    }

    public UserDTO(@NotBlank(message = "user not found") String id, @NotBlank String userId, @NotBlank String name, @NotBlank String img) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.img = img;
    }

    public UserDTO(@NotBlank(message = "user not found") String id, @NotBlank String userId, @NotBlank String name, @NotBlank String img, List<String> questionsId, List<String> answersId, List<String> votedQuestionsId, List<VotedAnswer> votedAnswersId) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.img = img;
        this.questionsId = questionsId;
        this.answersId = answersId;
        this.votedQuestionsId = votedQuestionsId;
        this.votedAnswersId = votedAnswersId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<String> getQuestionsId() {
        return Optional.ofNullable(questionsId).orElse(new ArrayList<>());
    }

    public void setQuestionsId(List<String> questionsId) {
        this.questionsId = questionsId;
    }

    public List<String> getAnswersId() {
        return Optional.ofNullable(answersId).orElse(new ArrayList<>());
    }

    public void setAnswersId(List<String> answersId) {
        this.answersId = answersId;
    }

    public List<String> getVotedQuestionsId() {
        return Optional.ofNullable(votedQuestionsId).orElse(new ArrayList<>());
    }

    public void setVotedQuestionsId(List<String> votedQuestionsId) {
        this.votedQuestionsId = votedQuestionsId;
    }

    public List<VotedAnswer> getVotedAnswersId() {
        return Optional.ofNullable(votedAnswersId).orElse(new ArrayList<>());
    }

    public void setVotedAnswersId(List<VotedAnswer> votedAnswersId) {
        this.votedAnswersId = votedAnswersId;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", questionsId=" + questionsId +
                ", answersId=" + answersId +
                ", votedQuestionsId=" + votedQuestionsId +
                ", votedAnswers=" + votedAnswersId +
                '}';
    }
}
