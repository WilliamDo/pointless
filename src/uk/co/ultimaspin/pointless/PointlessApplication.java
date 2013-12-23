package uk.co.ultimaspin.pointless;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import uk.co.ultimaspin.pointless.quiz.Question;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 29/11/2013
 * Time: 19:25
 * To change this template use File | Settings | File Templates.
 */
public class PointlessApplication extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final double INITIAL_BAR_HEIGHT = 450;

    private static final double GAME_START = 100;

    private final Rectangle rectangle = new Rectangle(100, INITIAL_BAR_HEIGHT);
    private final Rectangle box = new Rectangle(100, INITIAL_BAR_HEIGHT);
    private final Label scoreLabel = new Label("" + Math.round(GAME_START));
    private final VBox scoreBar = new VBox();

    private double currentBarHeight = INITIAL_BAR_HEIGHT;
    private MediaPlayer mediaPlayer;

    private BorderPane borderPane = new BorderPane();


    public static void main(String[] args) throws URISyntaxException {
        URL resource = PointlessApplication.class.getClassLoader().getResource("countdown.mp3");
        if (new File(resource.toURI().getPath()).exists()) {
            System.out.println("Found the music");
        } else {
            System.out.println("No music!");
        }
        launch(args);


    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pointless");

        // Title bar
        Text title = new Text();
        title.setText("Pointless");
        title.setFont(Font.font("null", FontWeight.BOLD, 80));
        title.setTextAlignment(TextAlignment.CENTER);

        FlowPane topPane = new FlowPane();
        topPane.setAlignment(Pos.CENTER);
        topPane.getChildren().add(title);

        // Score bar
        scoreBar.setAlignment(Pos.BOTTOM_CENTER);
//        scoreBar.setStyle("-fx-background-color: darkblue;");
//        scoreBar.setId("pointless-background");
        scoreBar.setId("pointless-exp");

        scoreLabel.setFont(Font.font("null", FontWeight.BOLD, 48));
        scoreLabel.setStyle("-fx-text-fill: #FFFFFF");

        FlowPane scoreLabelWrapper = new FlowPane();
        scoreLabelWrapper.setAlignment(Pos.CENTER);
        scoreLabelWrapper.getChildren().add(scoreLabel);

        rectangle.setId("bar");
        FlowPane canvas = new FlowPane();
        canvas.getChildren().add(rectangle);
        canvas.setMinSize(100, INITIAL_BAR_HEIGHT);
        canvas.setAlignment(Pos.BOTTOM_CENTER);
        canvas.setVgap(100);
        StackPane stack = new StackPane();

        box.setFill(Color.ALICEBLUE);
        stack.getChildren().addAll(box, canvas);
        VBox.setMargin(stack, new Insets(0, 0, 50, 0));
        scoreBar.getChildren().addAll(scoreLabelWrapper, stack);

        setUpMusic();

        borderPane.setCenter(scoreBar);

        Scene scene = new Scene(borderPane, WIDTH, HEIGHT);
        scene.getStylesheets().add("game.css");
        primaryStage.setScene(scene);
        primaryStage.show();

        new Admin(this);

    }

    public void animate(double end, double animationInterval, double delay) {
        double start = GAME_START;

        double duration = animationInterval * (start - end);
        if (duration == 0) {
            duration = 1; // Hack to force the animation
        }

        PauseTransition pause = new PauseTransition();
        pause.setDuration(Duration.seconds(delay));

        if (end < GAME_START) {

            Duration millis = Duration.millis(duration);
            HeightTransition ht = new HeightTransition(rectangle, end * 100 / start, millis);
            NumberTransition numberTransition = new NumberTransition(scoreLabel, Math.round(start), Math.round(end), millis);
            MusicTransition musicTransition = new MusicTransition(mediaPlayer, millis, Transition.INDEFINITE);
            ParallelTransition pt = new ParallelTransition(ht, numberTransition, musicTransition);
            pt.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mediaPlayer.pause();
            }
        });

            MusicTransition endMusic = new MusicTransition(setUpEndMusic(), Duration.millis(2000), 1);
            FlashScreenTransition flashScreenTransition = new FlashScreenTransition(scoreBar, rectangle, Duration.millis(300), "lightgreen", FlashScreenTransition.FlashType.CORRECT);
            flashScreenTransition.setAutoReverse(true);
            flashScreenTransition.setCycleCount(9);

            ParallelTransition endTransition = new ParallelTransition(endMusic, flashScreenTransition);

            SequentialTransition st = new SequentialTransition(pause, pt, endTransition);
            st.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    resetColor();
                }
            });

            st.play();

        } else {
            MusicTransition endMusic = new MusicTransition(setUpEndMusic(), Duration.millis(2000), 1);
            FlashScreenTransition flashScreenTransition = new FlashScreenTransition(scoreBar, rectangle, Duration.millis(300), "orangered", FlashScreenTransition.FlashType.INCORRECT);
            flashScreenTransition.setAutoReverse(true);
            flashScreenTransition.setCycleCount(9);

            LabelTransition labelTransition = new LabelTransition(scoreLabel, Duration.millis(2000));
            ParallelTransition endTransition = new ParallelTransition(endMusic, labelTransition, flashScreenTransition);
            SequentialTransition sequentialTransition = new SequentialTransition(pause, endTransition);
            sequentialTransition.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    rectangle.setId("barred");

                }
            });
            sequentialTransition.play();
        }





    }

    private void resetColor() {
        scoreBar.setStyle("-fx-background-color: radial-gradient(center 50% 50%, radius 100%, mediumblue, black);");
    }

    private MediaPlayer setUpEndMusic() {
        URL resource = PointlessApplication.class.getClassLoader().getResource("end2.mp3");
        Media endSong = new Media(resource.toString());
        return new MediaPlayer(endSong);
    }

    private void setUpMusic() {
        URL resource = PointlessApplication.class.getClassLoader().getResource("countdown3.mp3");
        Media media = new Media(resource.toString());
        mediaPlayer = new MediaPlayer(media);
    }

    public void reset() {
        rectangle.setHeight(currentBarHeight);
        rectangle.setId("bar");
        scoreLabel.setText("" + Math.round(GAME_START));

    }

    public void bigger() {
        rectangle.setHeight(rectangle.getHeight() + 50);
        box.setHeight(box.getHeight() + 50);
        currentBarHeight = box.getHeight();
    }

    public void smaller() {
        rectangle.setHeight(rectangle.getHeight() - 50);
        box.setHeight(box.getHeight() - 50);
        currentBarHeight = box.getHeight();
    }

    public void wider() {
        rectangle.setWidth(rectangle.getWidth() + 25);
        box.setWidth(box.getWidth() + 25);
    }

    public void narrower() {
        rectangle.setWidth(rectangle.getWidth() - 25);
        box.setWidth(box.getWidth() - 25);
    }

    public void displayQuestion(Question question) {
        Label label = new Label(question.getQuestion());
        label.setFont(Font.font("null", FontWeight.BOLD, 48));
        label.setWrapText(true);
        label.setMaxWidth(700);
        label.setStyle("-fx-text-fill: #FFFFFF");

        FlowPane container = new FlowPane();
        container.getChildren().add(label);
        container.setAlignment(Pos.CENTER);

        VBox vBox = new VBox();
        vBox.getChildren().add(container);
        vBox.setId("pointless-exp");
        vBox.setAlignment(Pos.CENTER);
        borderPane.setCenter(vBox);
    }

    public void displayScoreBar() {
        borderPane.setCenter(scoreBar);
    }

}
