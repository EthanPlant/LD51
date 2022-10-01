package com.aquilla.ludumdare.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;

// Basic singleton class for handling assets

public class Assets {
    private static Assets instance;
    private AssetManager manager;

    public static Assets get() {
        if (instance == null) instance = new Assets();
        return instance;
    }

    public Texture getTexture(String ref) {
        return manager.get(ref, Texture.class);
    }

    public TiledMap getTiledMap(String ref) {
        return manager.get(ref, TiledMap.class);
    }

    public void provide(AssetManager assets) {
        this.manager = assets;
    }
}
