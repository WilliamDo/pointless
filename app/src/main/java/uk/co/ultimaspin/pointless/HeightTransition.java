package uk.co.ultimaspin.pointless;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 29/11/2013
 * Time: 23:03
 * To change this template use File | Settings | File Templates.
 */
public class HeightTransition extends Transition {

    private final Rectangle shape;
    private final double startHeight;
    private final double targetHeight;


    public HeightTransition(Rectangle shape, double targetPercentage, Duration duration) {

        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
        this.shape = shape;

        this.targetHeight = targetPercentage;
        this.startHeight = shape.getHeight();

    }

    @Override
    protected void interpolate(double v) {

        double unit = startHeight / 100;

        double deducted = v * (100 - targetHeight) * unit;
        double currentHeight = startHeight - deducted;
        shape.setHeight(currentHeight);


    }
}
