package game;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

	private String path;
	private File audioFile;
	private AudioInputStream audioInputStream; 
	private Clip clip;

	private FloatControl gainControl;
	private int volume;

	public Sound(String path, int volume) {
		this.path = path;
		this.volume = volume;
	}

	public void play() {
		try {
			audioFile = new File(path).getAbsoluteFile();
			audioInputStream = AudioSystem.getAudioInputStream(audioFile);
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);

			gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(volume);

			clip.start();

		} catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		clip.stop();
	}
}