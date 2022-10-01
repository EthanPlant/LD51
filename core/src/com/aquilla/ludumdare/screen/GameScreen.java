package com.aquilla.ludumdare.screen;

import com.aquilla.ludumdare.LudumDare;
import com.aquilla.ludumdare.assets.Assets;
import com.aquilla.ludumdare.level.Level;
import com.aquilla.ludumdare.input.KeyboardInputController;
import com.aquilla.ludumdare.level.entity.Player;
import com.aquilla.ludumdare.ui.Hud;
import com.aquilla.ludumdare.util.Palette;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen extends Screen {
    // Level
    private Level level;

    //Input
    private KeyboardInputController input;

    private long timer;
    private int timeToSwitch;

    private Hud hud;

    public GameScreen(LudumDare game) {
        super(game);

        level = new Level(Assets.get().getTiledMap("maps/testmap.tmx"));

        input = new KeyboardInputController();
        input.enable();

        timer = TimeUtils.nanoTime();
        timeToSwitch = 10;

        hud = new Hud(this);
    }

    @Override
    public void update(float delta) {
        input.update(delta);

        if (input.left()) level.getPlayer().setVel(new Vector2(-1 * Player.PLAYER_SPEED * LudumDare.TILE_SIZE, level.getPlayer().getVel().y));
        if (input.right()) level.getPlayer().setVel(new Vector2(Player.PLAYER_SPEED * LudumDare.TILE_SIZE, level.getPlayer().getVel().y));
        if (input.jump()) level.getPlayer().jump(level.isGravityDown());

        if (!input.left() && !input.right()) level.getPlayer().setVel(new Vector2(0, level.getPlayer().getVel().y));

        if (TimeUtils.nanoTime() - timer >= 1_000_000_000) {
            timeToSwitch--;
            System.out.println(timeToSwitch);
            // TODO Update HUD
            if (timeToSwitch == 0) {
                level.switchGravity();
                timeToSwitch = 10;
            }
            timer = TimeUtils.nanoTime();
        }

        level.update(delta);
        getCam().position.set(level.getPlayer().getPos(), 0);

        getCam().update();

        hud.update();
    }

    @Override
    public void draw(SpriteBatch sb, ShapeRenderer sr) {
        Gdx.gl.glClearColor(Palette.DEEP.r, Palette.DEEP.g, Palette.DEEP.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.setProjectionMatrix(getCam().combined);
        sr.setProjectionMatrix(getCam().combined);

        level.getRenderer().setView(getCam());
        level.draw();

        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Palette.TETANUS);
        sr.rect(level.getPlayer().getPos().x, level.getPlayer().getPos().y, level.getPlayer().getBoundingBox().getWidth(), level.getPlayer().getBoundingBox().getHeight());
        sr.end();

        sb.begin();
        hud.draw(game.getBatch());
        sb.end();
    }

    public int getTime() {
        return timeToSwitch;
    }
}
