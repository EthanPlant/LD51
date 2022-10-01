package com.aquilla.ludumdare.level.entity;

import com.aquilla.ludumdare.LudumDare;
import com.aquilla.ludumdare.assets.Assets;
import com.aquilla.ludumdare.util.CollisionHandler;

public class Player extends Entity {

    public static final int PLAYER_SPEED = 10;
    public static final int JUMP_SPEED = 7;

    private enum State {RUNNING, JUMPING, FALLING}
    private State state;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        state = State.RUNNING;
    }

    @Override
    public void update(float delta) {
        vel.add(accel.cpy().scl(delta)); // Add acceleration to velocity prior to collision check

        if (vel.y > 0) state = State.JUMPING;
        if (vel.y < 0) state = State.FALLING;

        // Check for collision
        boundingBox.x += (vel.x * delta);
        if (CollisionHandler.get().isCollidingWithMap(this, Assets.get().getTiledMap("maps/testmap.tmx"))) vel.x = 0;
        boundingBox.x = pos.x;
        boundingBox.y += (vel.y * delta);
        if (CollisionHandler.get().isCollidingWithMap(this, Assets.get().getTiledMap("maps/testmap.tmx"))) {
            if (state == State.FALLING) state = State.RUNNING; // We've hit the ground so we're no longer falling
            vel.y = 0;
        }
        boundingBox.y = pos.y;

        // Update the new position
        pos.add(vel.cpy().scl(delta));
        boundingBox.x = pos.x;
        boundingBox.y = pos.y;
    }

    public void jump() {
        if (state == State.RUNNING) {
            vel.y = JUMP_SPEED * LudumDare.TILE_SIZE;
            state = State.JUMPING;
        }
    }
}
