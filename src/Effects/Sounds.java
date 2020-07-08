package Effects;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Sounds {

    public static MediaPlayer playSound(String filename, double volume, boolean repeat) {
        return playSound(filename, volume, repeat, 0, MediaPlayer.INDEFINITE);
    }

    public static MediaPlayer playSound(String filename, double volume, boolean repeat, double start, double end) {
        Media media = new Media(Sounds.class.getResource("/Sounds/" + filename).toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnReady(() -> {
            mediaPlayer.setStartTime(Duration.seconds(start));
            if (end == MediaPlayer.INDEFINITE) {
                mediaPlayer.setStopTime(media.getDuration());
            }
            else {
                mediaPlayer.setStopTime(Duration.seconds(end));
            }
            if (repeat) {
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            }
            mediaPlayer.setVolume(volume);
            mediaPlayer.play();
        });
        return mediaPlayer;
    }
}
