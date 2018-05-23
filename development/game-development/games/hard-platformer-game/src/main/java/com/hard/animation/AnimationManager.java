package com.hard.animation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimationManager {
    private Map<Integer, Animation> animations;
    private int currentAnimation;

    public AnimationManager() {
        animations = new HashMap<>();
    }

    public void addAnimation(int title, Animation animation) {
        animations.put(title, animation);
    }

    public int getCurrentAnimation() {
        return currentAnimation;
    }

    public void setCurrentAnimation(int currentAnimation) {
        this.currentAnimation = currentAnimation;
    }

    public void update(double time) {
        Animation animation = animations.get(currentAnimation);
        animation.update(time);
    }

    public void draw(Graphics graphics, double x, double y, int w, int h) {
        Animation animation = animations.get(currentAnimation);
        animation.draw(graphics, x, y, w, h);
    }

    public boolean isPlaying() {
        Animation animation = animations.get(currentAnimation);
        return animation.isPlaying();
    }

    public void setPlaying(boolean playing) {
        Animation animation = animations.get(currentAnimation);
        animation.setPlaying(playing);
    }

    public boolean isLooped() {
        Animation animation = animations.get(currentAnimation);
        return animation.isLooped();
    }

    public void setLooped(boolean looped) {
        Animation animation = animations.get(currentAnimation);
        animation.setLooped(looped);
    }

    public boolean isFlipped() {
        Animation animation = animations.get(currentAnimation);
        return animation.isFlipped();
    }

    public void setFlipped(boolean flipped) {
        Animation animation = animations.get(currentAnimation);
        animation.setFlipped(flipped);
    }

    public int getWidth() {
        Animation animation = animations.get(currentAnimation);
        int currentFrame = (int) animation.getCurrentFrame();

        List<BufferedImage> frames = animation.getFrames();

        BufferedImage frame = frames.get(currentFrame);

        return frame.getWidth();
    }

    public int getHeight() {
        Animation animation = animations.get(currentAnimation);
        int currentFrame = (int) animation.getCurrentFrame();

        List<BufferedImage> frames = animation.getFrames();

        BufferedImage frame = frames.get(currentFrame);

        return frame.getHeight();
    }
}
