/*import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.SourceDataLine;

import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;

public class SoundManager {

    private final int BUFFER_SIZE = 128000;
    private AudioFormat music;
    private File soundFile;
    private AudioInputStream audioStream;
    private SourceDataLine sourceLine;
    private ComboBox


    private ComboBox<String> musicBox;

    private int volume;

    private final CheckBox mute;
    private final CheckBox unmute;
    private Slider volumeBar;

    private Button backToMenu;

    public SoundManager(AudioFormat music){
        this.music = music;

        initSettings( mute, unmute);
        initSlider( volumeBar);
    }

    private void initCheckBox(CheckBox mute, CheckBox unmute) {
        mute = new CheckBox("Mute");
        unmute = new CheckBox("Unmute");

        mute.setSelected(true);
        unmute.setSelected(true);

        mute.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov,
                                Boolean old_val, Boolean new_val) {
                System.out.println(mute.isSelected());
                mute();
            }
        });
        unmute.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov,
                                Boolean old_val, Boolean new_val) {
                System.out.println(unmute.isSelected());
                unmute();
            }
        });
    }

    private void initSlider( Slider volumeBar)
    {
        volumeBar = new Slider();
        volumeBar.setMin(0);
        volumeBar.setMax(100);
        volumeBar.setValue(50);

        volumeBar.setShowTickMarks(false);
        volumeBar.setShowTickLables(true);
        volumeBar.setMajorTickUnit(50);
        volumeBar.setBlockIncrement(10);

        volumeBar.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                changeVolume( new_val);
            }
        });

    }

    public void mute(){
        volume = 0;
        changeVolume( volume);
    }
    public void unmute(){}

    public void changeVolume(int volume){
        this.volume = volume;
        volumeBar.setValue( volume);

    }
    public void changeMusic(AudioFormat music){}

}*///