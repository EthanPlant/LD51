package com.aquilla.ludumdare.level;

import com.aquilla.ludumdare.assets.Assets;
import com.aquilla.ludumdare.level.entity.Player;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
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
    private Vector2 lastCheckpoint;

    // TiledMap stuff
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    public Level(TiledMap map) {
        this.map = map;
        renderer = new OrthogonalTiledMapRenderer(map);

        Vector2 spawnPoint = findPlayerSpawn();
        lastCheckpoint = spawnPoint; // Checkpoint should be the same pos as spawn at first
        player = new Player(spawnPoint.x, spawnPoint.y, TILE_SIZE, 2 * TILE_SIZE);
        player.setAccel(new Vector2(0, -1 * GRAVITY_STRENGTH));

        isGravityDown = true;
    }

    public void update(float delta) {
        player.update(delta, isGravityDown);
        checkCheckpoint();
    }

    public void draw(SpriteBatch sb) {
        renderer.render();

        player.draw(sb, isGravityDown);
    }

    private Vector2 findPlayerSpawn() {
        Rectangle rect = ((RectangleMapObject) map.getLayers().get("player_spawn").getObjects().get("spawnpoint")).getRectangle();
        return new Vector2(rect.x, rect.y);
    }

    private void checkCheckpoint() {
        // Check to see if the player is colliding with a checkpoint this frame
        for (MapObject object : map.getLayers().get("checkpoints").getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            if (player.getBoundingBox().overlaps(rect)) { // We've hit a checkpoint, check to see if its further than the previous
                if (rect.x > lastCheckpoint.x) {
                    lastCheckpoint = new Vector2(rect.x, rect.y);
                    System.out.println("Hit a new checkpoint!");
                }
            }
        }
    }

    public void switchGravity() {
        Assets.get().getSound("sounds/switch.wav").play();
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
