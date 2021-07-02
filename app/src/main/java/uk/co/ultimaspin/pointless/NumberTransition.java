package uk.co.ultimaspin.pointless;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 29/11/2013
 * Time: 21:48
 * To change this template use File | Settings | File Templates.
 */
public class NumberTransition extends Transition {

    private final Label label; // TODO Make this more generic, don't use a label
    private final long startNumber;
    private final long endNumber;

    public NumberTransition(Label label, long startNumber, long endNumber, Duration duration) {
        this.label = label;
        this.startNumber = startNumber;
        this.endNumber = endNumber;
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
    }

    @Override
    protected void interpolate(double v) {
        double deducted = v * (startNumber - endNumber);

        double current = startNumber - deducted;
        this.label.setText("" + Math.round(current));
    }
}
