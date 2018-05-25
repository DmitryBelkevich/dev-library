package com.hard.entities;

import java.awt.*;

public class Console extends AbstractEntity {
    private int stepX = 5;
    private int stepY = 20;

    private Object entity;

    public void setEntity(Object entity) {
        this.entity = entity;
    }

    @Override
    public void update(double time) {

    }

    @Override
    public void draw(Graphics graphics) {
        Entity entity = (Entity) this.entity;

        //

        graphics.setColor(new Color(0, 255, 255, 255));

        graphics.drawString(entity.toString(), (int) x, (int) (y + 0 * stepY));

        //

        if (entity.getX() != 0)
            graphics.setColor(new Color(0, 255, 0, 255));
        else if (entity.getX() == 0)
            graphics.setColor(new Color(0, 255, 0, 255));

        graphics.drawString("x=" + entity.getX(), (int) x, (int) (y + 1 * stepY));

        //

        if (entity.getX() != 0)
            graphics.setColor(new Color(0, 255, 0, 255));
        else if (entity.getX() == 0)
            graphics.setColor(new Color(0, 255, 0, 255));

        graphics.drawString("x=" + entity.getX(), (int) x, (int) (y + 2 * stepY));

////        if (x != 0) graphics.setColor(new Color(225, 0, 0, 255));
//        graphics.drawString("x=" + x, 50, 1 * stepY);
////        graphics.setColor(new Color(0, 255, 0, 255));
//
////        if (y != 0) graphics.setColor(new Color(225, 0, 0, 255));
//        graphics.drawString("y=" + y, 50, 2 * stepY);
////        graphics.setColor(new Color(0, 255, 0, 255));

//        if (dx != 0) graphics.setColor(new Color(225, 0, 0, 255));
//        graphics.drawString("dx=" + dx, 50, 3 * stepY);
//        graphics.setColor(new Color(0, 255, 0, 255));
//
//        if (dy != 0) graphics.setColor(new Color(225, 0, 0, 255));
//        graphics.drawString("dy=" + dy, 50, 4 * stepY);
//        graphics.setColor(new Color(0, 255, 0, 255));
//
//        if (speed != 0) graphics.setColor(new Color(225, 0, 0, 255));
//        graphics.drawString("speed=" + speed, 50, 5 * stepY);
//        graphics.setColor(new Color(0, 255, 0, 255));

        /**
         * moving
         */

//        boolean left = entity.isLeft();
//        boolean right = entity.isRight();
//        boolean jumping = entity.isJumping();
//        boolean sitting = entity.isSitting();
//
//        if (left) graphics.setColor(new Color(225, 0, 0, 255));
//        graphics.drawString("left=" + left, 50, 7 * stepY);
//        graphics.setColor(new Color(0, 255, 0, 255));
//
//        if (right) graphics.setColor(new Color(225, 0, 0, 255));
//        graphics.drawString("right=" + right, 50, 8 * stepY);
//        graphics.setColor(new Color(0, 255, 0, 255));
//
//        if (jumping) graphics.setColor(new Color(225, 0, 0, 255));
//        graphics.drawString("jumping=" + jumping, 50, 9 * stepY);
//        graphics.setColor(new Color(0, 255, 0, 255));
//
//        if (sitting) graphics.setColor(new Color(225, 0, 0, 255));
//        graphics.drawString("sitting=" + sitting, 50, 10 * stepY);
//        graphics.setColor(new Color(0, 255, 0, 255));

        /**
         * collisions
         */

//        boolean leftCollision = entity.isLeftCollision();
//        boolean rightCollision = entity.isRightCollision();
//        boolean topCollision = entity.isTopCollision();
//        boolean bottomCollision = entity.isBottomCollision();
//
//        if (leftCollision) graphics.setColor(new Color(225, 0, 0, 255));
//        graphics.drawString("left collision=" + leftCollision, 50, 12 * stepY);
//        graphics.setColor(new Color(0, 255, 0, 255));
//
//        if (rightCollision) graphics.setColor(new Color(225, 0, 0, 255));
//        graphics.drawString("left collision=" + leftCollision, 50, 13 * stepY);
//        graphics.setColor(new Color(0, 255, 0, 255));
//
//        if (topCollision) graphics.setColor(new Color(225, 0, 0, 255));
//        graphics.drawString("top collision=" + topCollision, 50, 14 * stepY);
//        graphics.setColor(new Color(0, 255, 0, 255));
//
//        if (bottomCollision) graphics.setColor(new Color(225, 0, 0, 255));
//        graphics.drawString("bottom collision=" + bottomCollision, 50, 15 * stepY);
//        graphics.setColor(new Color(0, 255, 0, 255));

        /**
         * camera
         */

//        double offsetX = Camera.x;
//        double offsetY = Camera.y;
//
////        if (offsetX != 0) graphics.setColor(new Color(225, 0, 0, 255));
//        graphics.drawString("camera x=" + offsetX, 50 * stepX, 1 * stepY);
////        graphics.setColor(new Color(0, 255, 0, 255));
//
////        if (offsetY != 0) graphics.setColor(new Color(225, 0, 0, 255));
//        graphics.drawString("camera y=" + offsetY, 50 * stepX, 2 * stepY);
////        graphics.setColor(new Color(0, 255, 0, 255));
//
//        /**
//         * Game settings
//         */
//
////        if (offsetX != 0) graphics.setColor(new Color(225, 0, 0, 255));
//        graphics.drawString("game speed=" + Settings.GAME_SPEED, 50 * stepX, 3 * stepY);
////        graphics.setColor(new Color(0, 255, 0, 255));
//
////        if (offsetY != 0) graphics.setColor(new Color(225, 0, 0, 255));
//        graphics.drawString("gravity=" + Settings.GRAVITY, 50 * stepX, 4 * stepY);
////        graphics.setColor(new Color(0, 255, 0, 255));
    }
}
