package uk.co.ultimaspin.pointless;

import javafx.animation.Transition;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 30/11/2013
 * Time: 17:46
 * To change this template use File | Settings | File Templates.
 */
public class MusicTransition extends Transition {

    private final MediaPlayer mediaPlayer;
    private AtomicBoolean isPlaying;
    private int cycleCount;

    public MusicTransition(MediaPlayer mediaPlayer, Duration duration, int cycleCount) {
        this.mediaPlayer = mediaPlayer;
        this.cycleCount = cycleCount;
        isPlaying = new AtomicBoolean(false);
        setCycleDuration(duration);
    }

    @Override
    protected void interpolate(double v) {
        if (!isPlaying.get()) {
            mediaPlayer.setCycleCount(cycleCount);
            mediaPlayer.seek(Duration.seconds(0));
            mediaPlayer.play();
            isPlaying.set(true);
        }

    }
}
