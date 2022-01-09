package co.com.sofka.questions.collections;

import co.com.sofka.questions.model.VotedAnswer;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class User {

    @Id
    private String id;
    private String userId;
    private String name;
    private String img;
    private List<String> questionsId;
    private List<String> answersId;
    private List<String> votedQuestionsId;
    private List<VotedAnswer> votedAnswersId;

    public User() {

    }

    public User(String id, String userId, String name, String img, List<String> questionsId, List<String> answersId, List<String> votedQuestionsId, List<VotedAnswer> votedAnswersId) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.img = img;
        this.questionsId = questionsId;
        this.answersId = answersId;
        this.votedQuestionsId = votedQuestionsId;
        this.votedAnswersId = votedAnswersId;
    }

    public User(String userId, String name, String img) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.img = img;
        this.questionsId  = new ArrayList<>();
        this.answersId  = new ArrayList<>();
        this.votedQuestionsId  = new ArrayList<>();
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
