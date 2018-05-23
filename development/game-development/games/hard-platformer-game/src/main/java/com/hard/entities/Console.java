package com.hard.entities;

import java.awt.*;

public class Console {
    public void draw(Graphics graphics, Entity entity) {
        int step = 20;

        double x = entity.getX();
        double y = entity.getY();

        double dx = entity.getDx();
        double dy = entity.getDy();

        double speed = entity.getSpeed();

        graphics.setColor(new Color(0, 255, 0, 255));

//        if (x != 0) graphics.setColor(new Color(225, 0, 0, 255));
        graphics.drawString("x=" + x, 50, 1 * step);
//        graphics.setColor(new Color(0, 255, 0, 255));

//        if (y != 0) graphics.setColor(new Color(225, 0, 0, 255));
        graphics.drawString("y=" + y, 50, 2 * step);
//        graphics.setColor(new Color(0, 255, 0, 255));

        if (dx != 0) graphics.setColor(new Color(225, 0, 0, 255));
        graphics.drawString("dx=" + dx, 50, 3 * step);
        graphics.setColor(new Color(0, 255, 0, 255));

        if (dy != 0) graphics.setColor(new Color(225, 0, 0, 255));
        graphics.drawString("dy=" + dy, 50, 4 * step);
        graphics.setColor(new Color(0, 255, 0, 255));

        if (speed != 0) graphics.setColor(new Color(225, 0, 0, 255));
        graphics.drawString("speed=" + speed, 50, 5 * step);
        graphics.setColor(new Color(0, 255, 0, 255));

        /**
         * moving
         */

        boolean left = entity.isLeft();
        boolean right = entity.isRight();
        boolean up = entity.isUp();
        boolean down = entity.isDown();

        if (left) graphics.setColor(new Color(225, 0, 0, 255));
        graphics.drawString("left=" + left, 50, 7 * step);
        graphics.setColor(new Color(0, 255, 0, 255));

        if (right) graphics.setColor(new Color(225, 0, 0, 255));
        graphics.drawString("right=" + right, 50, 8 * step);
        graphics.setColor(new Color(0, 255, 0, 255));

        if (up) graphics.setColor(new Color(225, 0, 0, 255));
        graphics.drawString("up=" + up, 50, 9 * step);
        graphics.setColor(new Color(0, 255, 0, 255));

        if (down) graphics.setColor(new Color(225, 0, 0, 255));
        graphics.drawString("down=" + down, 50, 10 * step);
        graphics.setColor(new Color(0, 255, 0, 255));

        /**
         * collisions
         */

        boolean leftCollision = entity.isLeftCollision();
        boolean rightCollision = entity.isRightCollision();
        boolean topCollision = entity.isTopCollision();
        boolean bottomCollision = entity.isBottomCollision();

        if (leftCollision) graphics.setColor(new Color(225, 0, 0, 255));
        graphics.drawString("left collision=" + leftCollision, 50, 12 * step);
        graphics.setColor(new Color(0, 255, 0, 255));

        if (rightCollision) graphics.setColor(new Color(225, 0, 0, 255));
        graphics.drawString("left collision=" + leftCollision, 50, 13 * step);
        graphics.setColor(new Color(0, 255, 0, 255));

        if (topCollision) graphics.setColor(new Color(225, 0, 0, 255));
        graphics.drawString("top collision=" + topCollision, 50, 14 * step);
        graphics.setColor(new Color(0, 255, 0, 255));

        if (bottomCollision) graphics.setColor(new Color(225, 0, 0, 255));
        graphics.drawString("bottom collision=" + bottomCollision, 50, 15 * step);
        graphics.setColor(new Color(0, 255, 0, 255));
    }
}
