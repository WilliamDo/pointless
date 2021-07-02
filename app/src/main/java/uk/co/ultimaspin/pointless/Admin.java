package uk.co.ultimaspin.pointless;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import uk.co.ultimaspin.pointless.quiz.Answer;
import uk.co.ultimaspin.pointless.quiz.Question;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 30/11/2013
 * Time: 20:45
 * To change this template use File | Settings | File Templates.
 */
public class Admin {

    final TextField textField = new TextField();
    final PointlessApplication app;
    final Quiz quiz;
    private final ComboBox<Answer> comboBox;
    private final Label questionLabel;
    private final Button startButton;
    private final Button startAnswerButton;
    private final Slider speedSlider;
    private final Slider delaySlider;

    public Admin(PointlessApplication app) {
        this.quiz = new QuizBuilder().sampleQuiz();
        this.app = app;

        // Admin console
        speedSlider = new Slider();
        speedSlider.setMin(0);
        speedSlider.setMax(500);
        speedSlider.setValue(300);
        speedSlider.setShowTickLabels(true);
        speedSlider.setShowTickMarks(true);
        speedSlider.setMajorTickUnit(100);
        speedSlider.setMinorTickCount(50);

        delaySlider = new Slider();
        delaySlider.setMin(0);
        delaySlider.setMax(10);
        delaySlider.setValue(3);
        delaySlider.setShowTickLabels(true);
        delaySlider.setShowTickMarks(true);
        delaySlider.setMajorTickUnit(1);
//        delaySlide.setMinorTickCount(50);

        startButton = new Button("Start");
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (validateText()) {
                    double target = Double.parseDouble(textField.getText());

                    startAnimation(target);
                } else {
                    // TODO How to alert this error
                }
            }
        });

        Button resetButton = new Button("Reset");
        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                resetButtons();
            }
        });

        Button bigger = new Button("+");
        bigger.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Admin.this.app.bigger();
            }
        });

        Button smaller = new Button("-");
        smaller.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Admin.this.app.smaller();
            }
        });


        // Admin console
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(20);
        grid.setPadding(new Insets(0, 10, 0, 10));

        grid.add(new Label("Score: "), 0, 0);
        grid.add(textField, 1, 0);
        grid.add(startButton, 2, 0);
        grid.add(resetButton, 3, 0);

        grid.add(new Label("Speed: "), 0, 1);
        GridPane.setConstraints(speedSlider, 1, 1, 3, 1);
        grid.add(speedSlider, 1, 1);

        grid.add(new Label("Delay: "), 0, 2);
        GridPane.setConstraints(delaySlider, 1, 1, 3, 1);
        grid.add(delaySlider, 1, 2);

        grid.add(new Label("Height: "), 0, 3);
        grid.add(smaller, 1, 3);
        grid.add(bigger, 2, 3);


        Button wider = new Button("+");
        wider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Admin.this.app.wider();
            }
        });

        Button narrower = new Button("-");
        narrower.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Admin.this.app.narrower();
            }
        });

        grid.add(new Label("Width: "), 0, 4);
        grid.add(narrower, 1, 4);
        grid.add(wider, 2, 4);


        Button questionButton = new Button("Question");
        questionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Admin.this.app.displayQuestion(quiz.currentQuestion());
            }
        });

        Button showScoreButton = new Button("Bar");
        showScoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Admin.this.app.displayScoreBar();
            }
        });

        Button nextQuestionButton = new Button("Next Question");
        nextQuestionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Question question = quiz.nextQuestion();
                updateAnswers(question);
                Admin.this.app.displayQuestion(question);
            }
        });

        startAnswerButton = new Button("Select Answer");
        startAnswerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int score = getAnswerScore();
                startAnimation(score);
            }
        });


        grid.add(new Label("Question: "), 0, 5);
        questionLabel = new Label();
        questionLabel.setMaxWidth(400);
        questionLabel.setWrapText(true);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        GridPane.setConstraints(scrollPane, 1, 1, 3, 1);
        scrollPane.setContent(questionLabel);
        grid.add(scrollPane, 1, 5);

        grid.add(nextQuestionButton, 3, 6);
        grid.add(startAnswerButton, 2, 6);

        Button wrongAnswerButton = new Button("Wrong Answer");
        wrongAnswerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                startAnimation(100);
            }
        });
        grid.add(wrongAnswerButton, 2, 7);

        grid.add(new Label("Display: "), 0, 8);
        grid.add(questionButton, 2, 8);
        grid.add(showScoreButton, 3, 8);


        comboBox = new ComboBox<Answer>();
        comboBox.setPrefWidth(200);
        grid.add(comboBox, 1, 6);


        VBox vBox = new VBox();
        vBox.getChildren().add(grid);

        FlowPane flowPaneContainer = new FlowPane();
        flowPaneContainer.getChildren().addAll(vBox);
        flowPaneContainer.setAlignment(Pos.CENTER);

        Stage adminStage = new Stage();
        adminStage.setTitle("Quiz Master Control Panel");
        adminStage.setScene(new Scene(flowPaneContainer, 600, 600));
        adminStage.sizeToScene();
        adminStage.show();

    }

    private void startAnimation(double target) {
        this.app.animate(target, 501 - speedSlider.getValue(), delaySlider.getValue());
        startButton.setDisable(true);
        startAnswerButton.setDisable(true);
    }

    private boolean validateText() {

        String input = textField.getText();

        try {
            double val = Double.parseDouble(input);
            return 0 <= val && val <= 100; // TODO Move hard-coded numbers
        } catch (Exception e) {
            return false;
        }
    }

    private void updateAnswers(Question question) {
        ObservableList<Answer> answers = FXCollections.observableArrayList(question.getAnswers());
        comboBox.setItems(answers);
        questionLabel.setText(question.getQuestion());
    }

    private int getAnswerScore() {
        Answer value = comboBox.getValue();
        return value.getScore();
    }

    private void resetButtons() {
        Admin.this.app.reset();
        startButton.setDisable(false);
        startAnswerButton.setDisable(false);
    }

}
