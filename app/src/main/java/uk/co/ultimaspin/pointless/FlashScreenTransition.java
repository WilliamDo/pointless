package uk.co.ultimaspin.pointless;

import javafx.animation.Transition;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 15/12/2013
 * Time: 11:22
 * To change this template use File | Settings | File Templates.
 */
public class FlashScreenTransition extends Transition {


    final VBox vBox;
    final String cssColor;
    final Shape shape;
    final FlashType flashType;


    public enum FlashType {
        CORRECT, INCORRECT
    }

    public FlashScreenTransition(VBox vBox, Shape shape, Duration duration, String cssColor, FlashType flashType) {
        this.vBox = vBox;
        this.cssColor = cssColor;
        this.shape = shape;
        this.flashType = flashType;
        setCycleDuration(duration);
    }

    @Override
    protected void interpolate(double v) {
        if (flashType == FlashType.CORRECT) {
            flashCorrect(v);
        } else {
            flashWrong(v);
        }

    }

    private void flashCorrect(double v) {
        if (v > 0.5) {
//            vBox.setStyle("-fx-background-color: rgba(0,0,0,0);");
            vBox.setStyle("-fx-background-color: radial-gradient(center 50% 50%, radius 100%, mediumblue, black);");
            if (shape != null) {
                shape.setId("bargreen");
            }
        } else {
            vBox.setStyle("-fx-background-color: radial-gradient(center 50% 50%, radius 100%, " + cssColor + ", black);");
            if (shape != null) {
                shape.setId("bar");
            }
        }
    }

    private void flashWrong(double v) {
        if (v > 0.5) {
//            vBox.setStyle("-fx-background-color: rgba(0,0,0,0);");
            vBox.setStyle("-fx-background-color: radial-gradient(center 50% 50%, radius 100%, mediumblue, black);");
            if (shape != null) {
                shape.setId("barred");
            }
        } else {
            vBox.setStyle("-fx-background-color: radial-gradient(center 50% 50%, radius 100%, " + cssColor + ", black);");
            if (shape != null) {
                shape.setId("bar");
            }
        }
    }
}
