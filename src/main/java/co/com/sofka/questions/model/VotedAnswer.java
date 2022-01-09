package co.com.sofka.questions.model;

import java.util.Objects;

public class VotedAnswer {
    private String answerId;
    private Integer vote;

    public VotedAnswer(String answerId, Integer vote) {
        this.answerId = answerId;
        this.vote = vote;
    }

    public VotedAnswer() {
        this.vote = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotedAnswer that = (VotedAnswer) o;
        return Objects.equals(answerId, that.answerId) && Objects.equals(vote, that.vote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answerId, vote);
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }

    @Override
    public String toString() {
        return "VotedAnswer{" +
                "answerId='" + answerId + '\'' +
                ", vote=" + vote +
                '}';
    }
}
