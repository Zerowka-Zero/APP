package ru.artem;

import java.util.LinkedList;
import java.util.Objects;


public record Question(String keyQuestion, LinkedList<String> questions, int answer) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (answer != question.answer) return false;
        if (!Objects.equals(keyQuestion, question.keyQuestion))
            return false;
        return Objects.equals(questions, question.questions);
    }

    @Override
    public int hashCode() {
        int result = keyQuestion != null ? keyQuestion.hashCode() : 0;
        result = 31 * result + (questions != null ? questions.hashCode() : 0);
        result = 31 * result + answer;
        return result;
    }

    @Override
    public String toString() {
        return "Question{" +
                "keyQuestion='" + keyQuestion + '\'' +
                ", questions=" + questions +
                ", answer=" + answer +
                '}';
    }
}