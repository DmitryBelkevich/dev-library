package com.hard.entities;

import com.hard.tiles.TileMap;

import java.awt.*;


public abstract class EnemyProjectile extends MapObject {

    protected boolean hit;
    protected boolean remove;
    protected int damage;

    public EnemyProjectile(TileMap tm) {
        super(tm);
    }

    public int getDamage() {
        return damage;
    }

    public boolean shouldRemove() {
        return remove;
    }

    public abstract void setHit();

    public abstract void update();

    public void draw(Graphics2D g) {
        super.draw(g);
    }

}
