package com.hard.animation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class Animation {
    private List<BufferedImage> frames;

    private double currentFrame;
    private double speedFrame;

    private boolean playing;
    private boolean looped;
    private boolean flipped;

    public Animation() {
        speedFrame = 0.1;

        playing = true;
        looped = true;
    }

    public List<BufferedImage> getFrames() {
        return frames;
    }

    public void setFrames(List<BufferedImage> frames) {
        this.frames = frames;
    }

    public double getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(double currentFrame) {
        this.currentFrame = currentFrame;
    }

    public double getSpeedFrame() {
        return speedFrame;
    }

    public void setSpeedFrame(double speedFrame) {
        this.speedFrame = speedFrame;
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
        if (playing) {
            currentFrame += speedFrame * time;

            if (currentFrame >= frames.size())
                if (looped)
                    currentFrame = 0;
                else {
                    currentFrame = frames.size() - 1;
                    playing = false;
                }
        }
    }

    public void draw(Graphics graphics, double x, double y, int w, int h) {
        BufferedImage frame = frames.get((int) currentFrame);

        if (!flipped)
            graphics.drawImage(frame, (int) x, (int) y, w, h, null);
        else
            graphics.drawImage(frame, (int) x + w, (int) y, -w, h, null);
    }
}
