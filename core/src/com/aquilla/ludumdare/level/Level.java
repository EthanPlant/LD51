package com.aquilla.ludumdare.level;

import com.aquilla.ludumdare.level.entity.Player;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

import static com.aquilla.ludumdare.LudumDare.TILE_SIZE;

public class Level {
    public static int GRAVITY_STRENGTH = 15 * TILE_SIZE;

    private boolean isGravityDown;

    // Player
    private Player player;

    // TiledMap stuff
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    public Level(TiledMap map) {
        this.map = map;
        renderer = new OrthogonalTiledMapRenderer(map);

        player = new Player(0, TILE_SIZE, TILE_SIZE, 2 * TILE_SIZE);
        player.setAccel(new Vector2(0, -1 * GRAVITY_STRENGTH));

        isGravityDown = true;
    }

    public void update(float delta) {
        // Set player gravity
        if (isGravityDown) player.setAccel(new Vector2(0, -1 * GRAVITY_STRENGTH));
        else player.setAccel(new Vector2(0, GRAVITY_STRENGTH));
        player.update(delta);
    }

    public void draw() {
        renderer.render();

        // TODO Draw player sprite
    }

    public void switchGravity() {
        isGravityDown = !isGravityDown;
    }

    public OrthogonalTiledMapRenderer getRenderer() {
        return renderer;
    }

    public Player getPlayer() {
        return player;
    }
}
