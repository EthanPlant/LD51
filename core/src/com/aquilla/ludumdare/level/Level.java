package com.aquilla.ludumdare.level;

import com.aquilla.ludumdare.level.entity.Player;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static com.aquilla.ludumdare.LudumDare.TILE_SIZE;

public class Level {
    public static final int GRAVITY_STRENGTH = 15 * TILE_SIZE;

    private boolean isGravityDown;

    // Player
    private Player player;

    // TiledMap stuff
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    public Level(TiledMap map) {
        this.map = map;
        renderer = new OrthogonalTiledMapRenderer(map);

        Vector2 spawnPoint = findPlayerSpawn();
        player = new Player(spawnPoint.x, spawnPoint.y, TILE_SIZE, 2 * TILE_SIZE);
        player.setAccel(new Vector2(0, -1 * GRAVITY_STRENGTH));

        isGravityDown = true;
    }

    public void update(float delta) {
        // Set player gravity
        player.update(delta, isGravityDown);
    }

    public void draw(SpriteBatch sb) {
        renderer.render();

        player.draw(sb, isGravityDown);
    }

    private Vector2 findPlayerSpawn() {
        Rectangle rect = ((RectangleMapObject) map.getLayers().get("player_spawn").getObjects().get("spawnpoint")).getRectangle();
        return new Vector2(rect.x, rect.y);
    }

    public void switchGravity() {
        isGravityDown = !isGravityDown;
        if (isGravityDown) player.setAccel(new Vector2(player.getAccel().x, -1 * GRAVITY_STRENGTH));
        else player.setAccel(new Vector2(player.getAccel().x, GRAVITY_STRENGTH));
    }

    public OrthogonalTiledMapRenderer getRenderer() {
        return renderer;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isGravityDown() {
        return isGravityDown;
    }
}
