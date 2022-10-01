package com.aquilla.ludumdare.screen;

import com.aquilla.ludumdare.LudumDare;
import com.aquilla.ludumdare.assets.Assets;
import com.aquilla.ludumdare.entity.Player;
import com.aquilla.ludumdare.input.KeyboardInputController;
import com.aquilla.ludumdare.util.Palette;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

public class GameScreen extends Screen {
    // Map variables
    private OrthogonalTiledMapRenderer renderer;

    //Input
    private KeyboardInputController input;

    // Player
    private Player player;

    public GameScreen(LudumDare game) {
        super(game);

        renderer = new OrthogonalTiledMapRenderer(Assets.get().getTiledMap("maps/testmap.tmx"));

        input = new KeyboardInputController(this);
        input.enable();

        player = new Player(0, LudumDare.TILE_SIZE, LudumDare.TILE_SIZE, 2 * LudumDare.TILE_SIZE);
    }

    @Override
    public void update(float delta) {
        input.update(delta);

        if (input.left()) player.setVel(new Vector2(-1 * Player.PLAYER_SPEED * LudumDare.TILE_SIZE, player.getVel().y));
        if (input.right()) player.setVel(new Vector2(Player.PLAYER_SPEED * LudumDare.TILE_SIZE, player.getVel().y));
        if (input.jump()) player.jump();

        if (!input.left() && !input.right()) player.setVel(new Vector2(0, player.getVel().y));

        player.update(delta);
        getCam().position.set(player.getPos(), 0);

        getCam().update();
    }

    @Override
    public void draw(SpriteBatch sb, ShapeRenderer sr) {
        Gdx.gl.glClearColor(Palette.DEEP.r, Palette.DEEP.g, Palette.DEEP.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.setProjectionMatrix(getCam().combined);
        sr.setProjectionMatrix(getCam().combined);

        renderer.setView(getCam());
        renderer.render();

        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Palette.TETANUS);
        sr.rect(player.getPos().x, player.getPos().y, player.getBoundingBox().getWidth(), player.getBoundingBox().getHeight());
        sr.end();
    }
}
