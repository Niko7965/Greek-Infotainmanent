import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

import java.io.File;
import java.io.IOException;


public class SoundController {
    Long currentFrame;
    Clip clip;

    String status;

    AudioInputStream audioInputStream;
    String filePath;

    public SoundController(String filePath)
            throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {
        this.filePath = filePath;
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);


    }

    public void play() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        clip.stop();
        clip.close();
        resetAudioStream();
        clip.start();

        status = "play";
    }

    public void resetAudioStream() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        clip.open(audioInputStream);

    }



}
