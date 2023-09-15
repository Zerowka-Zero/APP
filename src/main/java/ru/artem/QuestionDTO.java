package ru.artem;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Objects;

public class QuestionDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String issueTitle;
    private LinkedList<String> answers;
    private String issueTrue;

    public QuestionDTO(String issueTitle, LinkedList<String> answers, String issueTrue) {
        this.issueTitle = issueTitle;
        this.answers = answers;
        this.issueTrue = issueTrue;
    }

    public String getIssueTitle() {
        return issueTitle;
    }

    public void setIssueTitle(String issueTitle) {
        this.issueTitle = issueTitle;
    }

    public LinkedList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(LinkedList<String> answers) {
        this.answers = answers;
    }

    public String getIssueTrue() {
        return issueTrue;
    }

    public void setIssueTrue(String issueTrue) {
        this.issueTrue = issueTrue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionDTO that = (QuestionDTO) o;

        if (!Objects.equals(issueTitle, that.issueTitle)) return false;
        return Objects.equals(issueTrue, that.issueTrue);
    }

    @Override
    public int hashCode() {
        int result = issueTitle != null ? issueTitle.hashCode() : 0;
        result = 31 * result + (issueTrue != null ? issueTrue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "QuestionDTO{" +
                "issueTitle='" + issueTitle + '\'' +
                ", answers=" + answers +
                ", issueTrue='" + issueTrue + '\'' +
                '}';
    }
}
