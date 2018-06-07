package com.hard.animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {
    private Array<TextureRegion> frames;

    private double currentFrame;
    private double speedFrame;

    private boolean playing;
    private boolean looped;
    private boolean flipped;

    public Animation() {
        speedFrame = 10.0;

        playing = true;
        looped = true;
    }

    public Array<TextureRegion> getFrames() {
        return frames;
    }

    public void setFrames(Array<TextureRegion> frames) {
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

    public void update(float time) {
        if (!playing)
            return;

        currentFrame += speedFrame * time;

        if (currentFrame >= frames.size) {
            currentFrame = 0;

            if (!looped)
                playing = false;
        }
    }

    public void draw(SpriteBatch spriteBatch, float x, float y, float w, float h) {
        TextureRegion frame = frames.get((int) currentFrame);

        spriteBatch.begin();

        if (!flipped)
            spriteBatch.draw(frame, x, y, w, h);
        else
            spriteBatch.draw(frame, x + w, y, -w, h);

        spriteBatch.end();
    }
}
