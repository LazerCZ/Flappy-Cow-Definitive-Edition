package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class SwoonScreen implements Screen {
    SpriteBatch batch;
    private FitViewport viewport;
    public float volume;
    public int mode;
    public float timer;
    public float swoonY;

    Texture swoonT;
    Sprite swoon;


    @Override
    public void show() {
        batch = new SpriteBatch();

        float worldWidth = 80;
        float worldHeight = 50;

        timer = 0.0f;

        swoonT = new Texture("gameAssets/SWOON.png");
        swoon = new Sprite(swoonT);
        swoon.setSize(30, 10);
        swoon.setX(-7);

    }

    @Override
    public void render(float delta) {
        draw(delta);
        logic(delta);
        input(delta);
    }

    public void draw(float delta){
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        swoon.setY(swoonY);
        swoon.draw(batch);
        swoon.translateX(0.02f);

        batch.end();
    }
    public void logic(float delta){
        timer += delta;
    }
    public void input(float delta){

    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        if(width <= 0 || height <= 0) return;

        // Resize your screen here. The parameters represent the new window size.
        viewport = new FitViewport(80, 50);
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
