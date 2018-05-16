package com.hard.audio;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class AudioPlayer {
    private Clip clip;

    public AudioPlayer(String path) {
        Class<?> clazz = getClass();
        InputStream inputStream = clazz.getResourceAsStream(path);

        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(inputStream);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AudioFormat baseFormat = audioInputStream.getFormat();
        AudioFormat decodeFormat = new AudioFormat(
                AudioFormat.Encoding.PCM_SIGNED,
                baseFormat.getSampleRate(),
                16,
                baseFormat.getChannels(),
                baseFormat.getChannels() * 2,
                baseFormat.getSampleRate(),
                false
        );
        AudioInputStream audioInputStream2 = AudioSystem.getAudioInputStream(decodeFormat, audioInputStream);
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            clip.open(audioInputStream2);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip == null)
            return;

        stop();

        clip.setFramePosition(0);
        clip.start();
    }

    public void stop() {
        if (clip.isRunning())
            clip.stop();
    }

    public void close() {
        stop();
        clip.close();
    }
}
