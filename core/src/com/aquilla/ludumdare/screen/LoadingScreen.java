package com.aquilla.ludumdare.screen;

import com.aquilla.ludumdare.LudumDare;
import com.aquilla.ludumdare.assets.Assets;
import com.aquilla.ludumdare.util.Palette;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Matrix4;

public class LoadingScreen extends Screen{
    public static final int BAR_WIDTH = 38;
    public static final int BAR_HEIGHT = 2;
    public static final int BORDER_GAP = 1;
    public static final int BORDER_THICKNESS = 1;
    public static final Color BACKGROUND_COLOR = Palette.INK.cpy();

    private AssetManager assets;
    private boolean loaded;

    public LoadingScreen(LudumDare game) {
        super(game);

        // Initialise asset loading
        assets = new AssetManager();
        assets.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));

        // Load all assets
        assets.load("fonts/hud.fnt", BitmapFont.class);
        assets.load("maps/testmap.tmx", TiledMap.class);
    }

    @Override
    public void update(float delta) {
        if (assets.update()) {
            Assets.get().provide(assets);
            if (!loaded) {
                loaded = true;
                transitionToScreen(new GameScreen(getGame()));
            }
        }
    }

    @Override
    public void draw(SpriteBatch sb, ShapeRenderer sr) {
        float x;
        float y;
        float width;
        float height;

        // Reset camera projection matrix
        Matrix4 uiMatrix = getCam().combined.cpy();
        uiMatrix.setToOrtho2D(0, 0, LudumDare.WIDTH, LudumDare.HEIGHT);
        sr.setProjectionMatrix(uiMatrix);
        sb.setProjectionMatrix(uiMatrix);

        sr.begin(ShapeRenderer.ShapeType.Filled);
        // Background
        sr.setColor(BACKGROUND_COLOR);
        sr.rect(0, 0, LudumDare.WIDTH * LudumDare.SCALE, LudumDare.HEIGHT * LudumDare.SCALE);

        // Outer
        width = BAR_WIDTH + (BORDER_GAP + BORDER_THICKNESS) * 2;
        height = BAR_HEIGHT + (BORDER_GAP + BORDER_THICKNESS) * 2;
        x = (LudumDare.WIDTH - width) / 2;
        y = (LudumDare.HEIGHT - height) / 2;
        sr.setColor(Color.WHITE);
        sr.rect(x, y, width, height);

        // Inner
        width = BAR_WIDTH + (BORDER_GAP) * 2;
        height = BAR_HEIGHT + (BORDER_GAP) * 2;
        sr.setColor(BACKGROUND_COLOR);
        sr.rect(x, y, width, height);

        // Bar
        width = BAR_WIDTH;
        height = BAR_HEIGHT;

        // If in HTML mode, set bar at 50% process so it seemlessly continues from splash screen
        if (LudumDare.mode == LudumDare.Mode.HTML) width = (1 + assets.getProgress() * (width * 0.5f));
        else width = assets.getProgress() * width;

        // Draw the bar
        sr.setColor(Color.WHITE);
        sr.rect(x, y, width, height);

        sr.end();
    }
}
