package ru.artem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class Main extends Application {
    private static final LinkedList<Question> arr = ReaderTxt.formatter();
    private static int currentQuestion = 0;
    private static final Label questionLabel = new Label();
    private static final Button questionButton_1 = new Button();
    private static final Button questionButton_2 = new Button();
    private static final Button questionButton_3 = new Button();
    private static final Button questionButton_4 = new Button();
    private static final LinkedList<Button> listButton = new LinkedList<>(List.of(questionButton_1, questionButton_2,
            questionButton_3, questionButton_4));
    private static final Button previosButton = new Button("<--- Предыдущий вопрос");
    private static final Button nextQuestionButton = new Button("Следующий вопрос --->");
    public static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Тест"); // заголовок окна

        nextQuestionButton.setOnAction(event -> showQuestion(nextQuestionButton));
        previosButton.setOnAction(event -> showQuestion(previosButton));
        questionButton_1.setOnAction(event -> checkQuestion(questionButton_1, 1));
        questionButton_2.setOnAction(event -> checkQuestion(questionButton_2, 2));
        questionButton_3.setOnAction(event -> checkQuestion(questionButton_3, 3));
        questionButton_4.setOnAction(event -> checkQuestion(questionButton_4, 4));

        HBox hBox = new HBox(5  ,previosButton, nextQuestionButton);


        VBox vBox = new VBox(5); // расстояние между всеми элементами
        vBox.getChildren().addAll(questionLabel, questionButton_1, questionButton_2, questionButton_3, questionButton_4,
                hBox);

        stage.setScene(new Scene(vBox, 1200, 300)); // разрешение при запуске
        stage.show();
        inQuestion(currentQuestion); // запускается метод для показа первого вопроса
    }

    // выводит вопрос, согласно полученному значению
    private static void inQuestion(int in) {
        colorDefaultButton();
        Question question = arr.get(in);
        questionLabel.setText(question.keyQuestion());
        questionButton_1.setText(question.questions().get(0));
        questionButton_2.setText(question.questions().get(1));
        questionButton_3.setText(question.questions().get(2));
        questionButton_4.setText(question.questions().get(3));
    }
    // проверяет условие, какая кнопка использована и не выходит ли за предел массива

    private static void showQuestion(Button button) {
        if (button.equals(nextQuestionButton) && currentQuestion + 1 < arr.size()) {
            inQuestion(++currentQuestion);
        } else if (button.equals(previosButton) && currentQuestion - 1 > -1) {
            inQuestion(--currentQuestion);
        }
    }
    // добавляет к тексту кнопки правильный или неправильный ответ
    private static void checkQuestion(Button inButton, int inInt) {
        for (Button button : listButton) {
            colorButton(button, false);
        }
        if (inInt == arr.get(currentQuestion).answer()) {
            colorButton(inButton, true);
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

}