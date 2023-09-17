package ru.artem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main extends Application {
    //комент для проверки
    private static final LinkedList<QuestionDTO> arr = new LinkedList<>();
    private static int currentQuestion = 1;
    private static final Label questionLabel = new Label();
    private static final Button questionButton_1 = new Button();
    private static final Button questionButton_2 = new Button();
    private static final Button questionButton_3 = new Button();
    private static final Button questionButton_4 = new Button();
    private static final LinkedList<Button> listButton = new LinkedList<>(List.of(questionButton_1, questionButton_2,
            questionButton_3, questionButton_4));
    private static final Button previosButton = new Button("<--- Предыдущий вопрос");
    private static final Button nextQuestionButton = new Button("Следующий вопрос --->");

    private static int correctAnswersCount = 0;
    private static Button finishButton = new Button("Завершить тестирование");

    public static void main(String[] args) {
        readQuestionsFromBinFile();
        inQuestion(currentQuestion);
        Application.launch();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Тест");
        nextQuestionButton.setOnAction(event -> showQuestion(nextQuestionButton));
        previosButton.setOnAction(event -> showQuestion(previosButton));
        questionButton_1.setOnAction(event -> checkQuestion(questionButton_1));
        questionButton_2.setOnAction(event -> checkQuestion(questionButton_2));
        questionButton_3.setOnAction(event -> checkQuestion(questionButton_3));
        questionButton_4.setOnAction(event -> checkQuestion(questionButton_4));
        HBox hBox = new HBox(5, previosButton, nextQuestionButton);
        VBox vBox = new VBox(5);
        vBox.getChildren().addAll(questionLabel, questionButton_1, questionButton_2, questionButton_3, questionButton_4,
                hBox);
        Scene scene = new Scene(vBox, 1200, 300);
        stage.setScene(scene);
        stage.show();

        finishButton.setOnAction(event -> finishTest());
        vBox.getChildren().add(finishButton);

    }
    private static void finishTest() {
        displayFinalResult();
        questionButton_1.setDisable(true); // Disable answer buttons
        questionButton_2.setDisable(true);
        questionButton_3.setDisable(true);
        questionButton_4.setDisable(true);
    }

    private static void inQuestion(int in) {
        colorDefaultButton();
        QuestionDTO questionDTO = arr.get(in);
        List<String> randQuestionDTO = questionDTO.getAnswers();
        Collections.shuffle(randQuestionDTO);
        questionLabel.setText(questionDTO.getIssueTitle());
        questionButton_1.setText(randQuestionDTO.get(0));
        questionButton_2.setText(randQuestionDTO.get(1));
        questionButton_3.setText(randQuestionDTO.get(2));
        questionButton_4.setText(randQuestionDTO.get(3));
    }

    private static void showQuestion(Button button) {
        if (button.equals(nextQuestionButton) && currentQuestion + 1 < arr.size()) {
            inQuestion(++currentQuestion);
        } else if (button.equals(previosButton) && currentQuestion - 1 > -1) {
            inQuestion(--currentQuestion);
        } else if (button.equals(finishButton)) {
            finishTest();
        }
    }


    private static void checkQuestion(Button buttonClick) {
        for (Button button : listButton) {
            if (button.getText().equals(arr.get(currentQuestion).getIssueTrue()) && buttonClick == button) {
                correctAnswersCount++;
            }
            colorButton(button, button.getText().equals(arr.get(currentQuestion).getIssueTrue()));
        }
    }

    private static void colorButton(Button in, boolean inB) {
        if (inB) {
            in.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        } else {
            in.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        }
    }

    private static void colorDefaultButton() {
        listButton.forEach(x -> x.setStyle("-fx-background-color: white; -fx-text-fill: black;"));
    }

    private static void readQuestionsFromBinFile() {
        try (FileInputStream fileIn = new FileInputStream("src/main/resources/questions.bin");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            while (true) {
                try {
                    QuestionDTO questionDTO = (QuestionDTO) in.readObject();
                    Main.arr.add(questionDTO);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.fillInStackTrace();
        }

    }

    private static void displayFinalResult() {
        questionLabel.setText("Тестирование завершено.");
        questionButton_1.setText("Правильных ответов: " + correctAnswersCount);
        questionButton_2.setText(""); // Clear other buttons
        questionButton_3.setText("");
        questionButton_4.setText("");
        previosButton.setDisable(true);
        nextQuestionButton.setDisable(true);
    }


}