package com.aquilla.ludumdare.level.entity;

import com.aquilla.ludumdare.LudumDare;
import com.aquilla.ludumdare.assets.Assets;
import com.aquilla.ludumdare.level.Level;
import com.aquilla.ludumdare.util.CollisionHandler;

public class Player extends Entity {

    public static final int PLAYER_SPEED = 10 * LudumDare.TILE_SIZE;
    public static final int JUMP_SPEED = 7 * LudumDare.TILE_SIZE;

    public static final int ACCEL_SPEED = 15 * LudumDare.TILE_SIZE;
    public static final int DECEL_SPEED = 50 * LudumDare.TILE_SIZE;
    public static final float ACCEL_PERCENTAGE = 3F;

    public enum State {STANDING, RUNNING, JUMPING, FALLING}
    private State state;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        state = State.STANDING;
    }


    public void update(float delta, boolean isDown) {
        float oldX = vel.x;
        // Decelerate to a stop if needed
        if (state == State.STANDING) {
            if (vel.x < 0) { // Left
                accel.x = DECEL_SPEED;
            } else if (vel.x > 0) { // Right
                accel.x = -1 * DECEL_SPEED;
            }
        }

        vel.add(accel.cpy().scl(delta)); // Add acceleration to velocity prior to collision check

        if (state == State.STANDING) {
            if ((oldX < 0 && vel.x > 0) || (oldX > 0 && vel.x < 0)) {
                accel.x = 0;
                vel.x = 0;
            }
        }

        // Clamp max speed
        if (vel.x > PLAYER_SPEED) {
            vel.x = PLAYER_SPEED;
            accel.x = 0;
        }
        if (vel.x < -1 * PLAYER_SPEED) {
            vel.x = -1 * PLAYER_SPEED;
            accel.x = 0;
        }

        if (vel.y > 0 && isDown || vel.y < 0 && !isDown) state = State.JUMPING;
        if (vel.y < 0 || vel.y > 0 && !isDown) state = State.FALLING;

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

    public void jump(boolean isDown) {
        if (state == State.RUNNING) {
            if (isDown) vel.y =  JUMP_SPEED;
            else vel.y =  -1 * JUMP_SPEED;
            state = State.JUMPING;
        }
    }

    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }
}
