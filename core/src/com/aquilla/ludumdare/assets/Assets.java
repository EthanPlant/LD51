package com.aquilla.ludumdare.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;

// Basic singleton class for handling assets

public class Assets {
    private static Assets instance;
    private AssetManager manager;

    public static Assets get() {
        if (instance == null) instance = new Assets();
        return instance;
    }

    public BitmapFont getFont(String ref) {
        return manager.get(ref, BitmapFont.class);
    }

    public Music getMusic(String ref) {
        return manager.get(ref, Music.class);
    }

    public Sound getSound(String ref) {
        return manager.get(ref, Sound.class);
    }

    public Texture getTexture(String ref) {
        return manager.get(ref, Texture.class);
    }

    public TextureAtlas getTextureAtlas(String ref) {
        return manager.get(ref, TextureAtlas.class);
    }

    public TiledMap getTiledMap(String ref) {
        return manager.get(ref, TiledMap.class);
    }

    public void provide(AssetManager assets) {
        this.manager = assets;
    }
}
