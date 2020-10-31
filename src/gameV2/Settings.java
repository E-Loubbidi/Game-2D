package gameV2;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Settings {

	public static double getRandom(double min, double max){
	    double random = (Math.random()*((max-min)+1))+min;
	    return random;
	}
	
	public static Clip startSound() {
		try {
			File soundFile = new File("sounds/Starlight.wav");
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			try {
				Clip clip = AudioSystem.getClip();
				clip.open(audioIn);
				clip.start();
				return clip;
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Clip gameSound() {
		try {
			File soundFile = new File("sounds/Alone Against Enemy.wav");
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			try {
				Clip clip = AudioSystem.getClip();
				clip.open(audioIn);
				clip.start();
				return clip;
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Clip gameOverSound() {
		try {
			File soundFile = new File("sounds/gameOver.wav");
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			try {
				Clip clip = AudioSystem.getClip();
				clip.open(audioIn);
				clip.start();
				return clip;
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void shootSound() {
		try {
			File soundFile = new File("sounds/Explosion+1.wav");
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			try {
				Clip clip = AudioSystem.getClip();
				clip.open(audioIn);
				clip.start();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void boomSound() {
		try {
			File soundFile = new File("sounds/Explosion+3.wav");
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			try {
				Clip clip = AudioSystem.getClip();
				clip.open(audioIn);
				clip.start();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void healthSound() {
		try {
			File soundFile = new File("sounds/health.wav");
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			try {
				Clip clip = AudioSystem.getClip();
				clip.open(audioIn);
				clip.start();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
