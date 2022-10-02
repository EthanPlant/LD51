package com.aquilla.ludumdare.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class Shader {
    public static final ShaderProgram DEFAULT;
    public static final ShaderProgram SCANLINE;

    static {
        ShaderProgram.pedantic = false;
        DEFAULT = SpriteBatch.createDefaultShader();
        SCANLINE = new ShaderProgram(
                Gdx.files.internal("shaders/scanline.vert").readString(),
                Gdx.files.internal("shaders/scanline.frag").readString()
        );
    }
}
