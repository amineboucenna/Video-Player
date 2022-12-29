import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;

public class Controller implements Initializable {
    @FXML
    private AnchorPane myanchorpane;
    @FXML
    private MediaView mediaView;
    @FXML
    private CheckBox MuteCheckBox;
    @FXML
    private CheckBox RepeatCheckBox;
    @FXML
    private Slider TimeSlider;
    @FXML
    private Label TimeStart, TimeEnd;

    private File file;
    private MediaPlayer mediaPlayer;
    private Media media;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            TimeStart.setText(mediaPlayer.getStartTime().toString());
            TimeEnd.setText(mediaPlayer.getStopTime().toString());
            System.out.println("its playing");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SetTime(ActionEvent event) {

    }

    public void DoPlay(ActionEvent event) {
        mediaPlayer.play();
    }

    public void DoPause(ActionEvent event) {
        mediaPlayer.pause();
    }

    public void DoReset(ActionEvent event) {
        mediaPlayer.seek(new Duration(0.0));

    }

    public void AddDuration(ActionEvent event) {
        try {
            mediaPlayer.seek(mediaPlayer.getCurrentTime().add(new Duration(Duration.seconds(10.00).toSeconds())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeDuration(ActionEvent event) { // substracting current time by 10 seconds
        try {
            mediaPlayer.seek(mediaPlayer.getCurrentTime().subtract(new Duration(Duration.seconds(10.0).toSeconds())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Mute(ActionEvent event) {
        if (MuteCheckBox.isSelected())
            try {
                // the media player can be set to mute but no file is selected
                mediaPlayer.setMute(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        else
            mediaPlayer.setMute(false);
    }

    public void Repeat(ActionEvent event) {
        if (RepeatCheckBox.isSelected()) {
            mediaPlayer.setAutoPlay(true);
        } else {
            mediaPlayer.setAutoPlay(false);
        }
    }

    public void DoChooseFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a video file");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("MP4 file", "*.mp4"));
        Stage stage = (Stage) myanchorpane.getScene().getWindow();
        file = new File(fileChooser.showOpenDialog(stage).getAbsolutePath().toString());
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        Mute(null);
        Repeat(null);
        mediaView.setMediaPlayer(mediaPlayer);

    }
}
