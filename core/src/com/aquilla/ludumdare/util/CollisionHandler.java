package com.aquilla.ludumdare.util;

import com.aquilla.ludumdare.entity.Entity;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;

public class CollisionHandler {
    private static CollisionHandler instance;

    public static CollisionHandler get() {
        if (instance == null) instance = new CollisionHandler();

        return instance;
    }

    public boolean isCollidingWithMap(Entity e, TiledMap map) {
        for (MapObject object : map.getLayers().get("collision").getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            if (e.getBoundingBox().overlaps(rect)) return true;
        }

        return false;
    }
}