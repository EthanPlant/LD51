package com.aquilla.ludumdare.level.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Entity {
    protected Vector2 pos;
    protected Vector2 vel;
    protected Vector2 accel;

    protected Rectangle boundingBox;

    public Entity(float x, float y, int width, int height) {
        pos = new Vector2(x, y);
        vel = Vector2.Zero;
        accel = Vector2.Zero;
        boundingBox = new Rectangle(x, y, width, height);
    }

    public void update(float delta) {
        vel.add(accel.cpy().scl(delta));
        pos.add(vel.cpy().scl(delta));

        boundingBox.x = pos.x;
        boundingBox.y = pos.y;
    }

    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public Vector2 getVel() {
        return vel;
    }

    public void setVel(Vector2 vel) {
        this.vel = vel;
    }

    public Vector2 getAccel() {
        return accel;
    }

    public void setAccel(Vector2 accel) {
        this.accel = accel;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }
}
