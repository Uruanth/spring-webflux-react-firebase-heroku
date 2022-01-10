package co.com.sofka.questions.collections;

import co.com.sofka.questions.model.VotedAnswer;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Document
public class User {

    @Id
    private String id;
    @NotBlank
    @Indexed(unique = true)
    private String userId;
    @NotBlank
    private String name;
    @NotBlank
    private String img;
    @NotBlank
    private String email;
    private List<String> questionsId;
    private List<String> answersId;
    private List<String> votedQuestionsId;
    private List<VotedAnswer> votedAnswersId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User() {

    }

    public User(String id, @NotBlank String userId, @NotBlank String name, @NotBlank String img, @NotBlank String email, List<String> questionsId, List<String> answersId, List<String> votedQuestionsId, List<VotedAnswer> votedAnswersId) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.img = img;
        this.email = email;
        this.questionsId = questionsId;
        this.answersId = answersId;
        this.votedQuestionsId = votedQuestionsId;
        this.votedAnswersId = votedAnswersId;
    }

    public User(@NotBlank String userId, @NotBlank String name, @NotBlank String img) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.img = img;
        this.questionsId = new ArrayList<>();
        this.answersId = new ArrayList<>();
        this.votedQuestionsId = new ArrayList<>();
        this.votedAnswersId = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", questionsId=" + questionsId +
                ", answersId=" + answersId +
                ", votedQuestionsId=" + votedQuestionsId +
                ", votedAnswersId=" + votedAnswersId +
                '}';
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
        return questionsId;
    }

    public void setQuestionsId(List<String> questionsId) {
        this.questionsId = questionsId;
    }

    public List<String> getAnswersId() {
        return answersId;
    }

    public void setAnswersId(List<String> answersId) {
        this.answersId = answersId;
    }

    public List<String> getVotedQuestionsId() {
        return votedQuestionsId;
    }

    public void setVotedQuestionsId(List<String> votedQuestionsId) {
        this.votedQuestionsId = votedQuestionsId;
    }

    public List<VotedAnswer> getVotedAnswersId() {
        return votedAnswersId;
    }

    public void setVotedAnswersId(List<VotedAnswer> votedAnswersId) {
        this.votedAnswersId = votedAnswersId;
    }
}
