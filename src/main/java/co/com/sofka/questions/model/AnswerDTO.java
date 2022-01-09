package co.com.sofka.questions.model;


import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.Optional;

public class AnswerDTO {
    @NotBlank(message = "Debe existir el userId para este objeto")
    private String userId;
    @NotBlank
    private String questionId;
    @NotBlank
    private String answer;

    private Integer position;

    private String answerId;

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public AnswerDTO() {

    }

    public AnswerDTO(@NotBlank String questionId, @NotBlank String userId, @NotBlank String answer, Integer position) {
        this.userId = userId;
        this.questionId = questionId;
        this.answer = answer;
        this.position = position;
    }

    public AnswerDTO(@NotBlank String questionId, @NotBlank String userId, @NotBlank String answer, Integer position, String answerId) {
        this.userId = userId;
        this.questionId = questionId;
        this.answer = answer;
        this.position = position;
        this.answerId = answerId;
    }

    public AnswerDTO(@NotBlank String questionId, @NotBlank String userId, @NotBlank String answer) {
        this.userId = userId;
        this.questionId = questionId;
        this.answer = answer;
    }

    public Integer getPosition() {
        return Optional.ofNullable(position).orElse(0);
    }

    public void setPosition(Integer position) {
        this.position = position;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerDTO answerDTO = (AnswerDTO) o;
        return Objects.equals(userId, answerDTO.userId) && Objects.equals(questionId, answerDTO.questionId) && Objects.equals(answer, answerDTO.answer) && Objects.equals(position, answerDTO.position) && Objects.equals(answerId, answerDTO.answerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, questionId, answer, position, answerId);
    }

    @Override
    public String toString() {
        return "AnswerDTO{" +
                "userId='" + userId + '\'' +
                ", questionId='" + questionId + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
