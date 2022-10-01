package com.aquilla.ludumdare.level.entity;

import com.aquilla.ludumdare.LudumDare;
import com.aquilla.ludumdare.assets.Assets;
import com.aquilla.ludumdare.util.CollisionHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Player extends Entity {

    public static final int PLAYER_SPEED = 6 * LudumDare.TILE_SIZE;
    public static final int JUMP_SPEED = 10 * LudumDare.TILE_SIZE;

    public static final int ACCEL_SPEED = 15 * LudumDare.TILE_SIZE;
    public static final int DECEL_SPEED = 50 * LudumDare.TILE_SIZE;
    public static final float ACCEL_PERCENTAGE = 3F;

    public enum State {STANDING, RUNNING, JUMPING, FALLING}
    private State state;

    private Animation<TextureRegion> idle;
    private float frameTime;
    private boolean isRight;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        boundingBox.height = 18;
        isRight = true;
        idle = new Animation<TextureRegion>(0.1f, Assets.get().getTextureAtlas("textures/player.atlas").findRegions("player-idle"), Animation.PlayMode.LOOP_PINGPONG);
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

        if (vel.x > 0) isRight = true;
        else if (vel.x < 0) isRight = false;

        if (vel.y > 0 && isDown || vel.y < 0 && !isDown) state = State.JUMPING;
        if (vel.y < 0 || vel.y > 0 && !isDown) state = State.FALLING;

        // Check for collision
        boundingBox.x += (vel.x * delta);
        if (CollisionHandler.get().isCollidingWithMap(this, Assets.get().getTiledMap("maps/level1.tmx"))) vel.x = 0;
        boundingBox.x = pos.x;
        boundingBox.y += (vel.y * delta);
        if (CollisionHandler.get().isCollidingWithMap(this, Assets.get().getTiledMap("maps/level1.tmx"))) {
            if (state == State.FALLING) state = State.RUNNING; // We've hit the ground so we're no longer falling
            vel.y = 0;
        }
        boundingBox.y = pos.y;

        // Update the new position
        pos.add(vel.cpy().scl(delta));
        boundingBox.x = pos.x;
        boundingBox.y = isDown ? pos.y : pos.y + 14;

        // Update animation time
        frameTime += delta;
    }

    public void draw(SpriteBatch sb, boolean isDown) {
        int index = idle.getKeyFrameIndex(frameTime);
        TextureRegion currentFrame = idle.getKeyFrame(frameTime, true);
        float x = isRight ? pos.x + currentFrame.getRegionWidth() : pos.x;
        float y = isDown ? pos.y : pos.y + currentFrame.getRegionHeight();
        float width = isRight ? -1 * currentFrame.getRegionWidth() : currentFrame.getRegionWidth();
        float height = isDown ? currentFrame.getRegionHeight() : -1 * currentFrame.getRegionHeight();
        sb.draw(currentFrame, x, y, width, height);
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
