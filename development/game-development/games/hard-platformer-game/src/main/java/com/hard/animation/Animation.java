package com.hard.animation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class Animation {
    private List<BufferedImage> frames;

    private double currentFrame = 0;
    private double speedFrame = 0.145;

    private boolean playing;
    private boolean looped;
    private boolean flipped;

    public List<BufferedImage> getFrames() {
        return frames;
    }

    public void setFrames(List<BufferedImage> frames) {
        this.frames = frames;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public boolean isLooped() {
        return looped;
    }

    public void setLooped(boolean looped) {
        this.looped = looped;
    }

    public boolean isFlipped() {
        return flipped;
    }

    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }

    public void update(double time) {
        currentFrame += speedFrame * time;
        if (currentFrame >= frames.size())
            currentFrame = 0;
    }

    public void draw(Graphics graphics, double x, double y, int w, int h) {
        BufferedImage frame = frames.get((int) currentFrame);

        graphics.drawImage(frame, (int) x, (int) y, w, h, null);
    }
}
