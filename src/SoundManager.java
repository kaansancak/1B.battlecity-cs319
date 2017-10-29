import javax.sound.sampled.AudioFormat;
import javax.swing.*;

public class SoundManager {
    private AudioFormat music;
    private JComboBox musicBox;
    private JSlider volumeBar;
    private int volume;
    private JCheckBox mutebackground;
    public SoundManager(){}
    public void changeVolume(int volume){}
    public void changeMusic(AudioFormat music){}
    public void mute(){}
    public void unmute(){}
}
