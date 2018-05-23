package com.hard;

import java.awt.*;
import java.util.HashMap;
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
}
