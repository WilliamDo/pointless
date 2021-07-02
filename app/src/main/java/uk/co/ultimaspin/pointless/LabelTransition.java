package uk.co.ultimaspin.pointless;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 16/12/2013
 * Time: 21:30
 * To change this template use File | Settings | File Templates.
 */
public class LabelTransition extends Transition {
    private final Label label; // TODO Make this more generic, don't use a label

    public LabelTransition(Label label, Duration duration) {
        this.label = label;
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
    }

    @Override
    protected void interpolate(double v) {
        this.label.setText("X");
    }
}
