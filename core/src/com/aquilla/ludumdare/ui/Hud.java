package com.aquilla.ludumdare.ui;

import com.aquilla.ludumdare.LudumDare;
import com.aquilla.ludumdare.assets.Assets;
import com.aquilla.ludumdare.screen.GameScreen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

public class Hud {
    private GameScreen game;
    private int time;
    private String timeText;
    private BitmapFont font;

    private GlyphLayout layout;

    public Hud(GameScreen game) {
        this.game = game;

        font = Assets.get().getFont("fonts/hud.fnt");
        layout = new GlyphLayout();

        time = 10;
    }

    public void update() {
        time = game.getTime();
        timeText = Integer.toString(time);
    }

    public void draw(SpriteBatch sb) {
        Matrix4 uiMatrix = game.getCam().combined.cpy();
        uiMatrix.setToOrtho2D(0, 0, LudumDare.WIDTH, LudumDare.HEIGHT);
        sb.setProjectionMatrix(uiMatrix);

        // Render our timer
        float x, y, width, height;
        int padding = 10;
        int vertPadding = 5;
        String text = "Time";
        layout.setText(font, text);
        width = layout.width;
        float timeWidth = width;
        height = layout.width;
        x = LudumDare.WIDTH - width - padding;
        y = LudumDare.HEIGHT - vertPadding;
        font.draw(sb, text, x, y);

        layout.setText(font, timeText);
        width = layout.width;
        x = LudumDare.WIDTH - padding - timeWidth / 2 - width / 2;
        y = LudumDare.HEIGHT - height / 2 - vertPadding;
        font.draw(sb, timeText, x, y);

        sb.setProjectionMatrix(game.getCam().combined);
    }
}
