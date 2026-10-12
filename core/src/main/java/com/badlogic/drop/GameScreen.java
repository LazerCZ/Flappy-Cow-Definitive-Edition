package com.badlogic.drop;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameScreen implements Screen {
    SpriteBatch batch;
    private FitViewport viewport;
    public float volume;
    public int mode;

    Texture BG;


    Texture cowText;
    Cow cow;

    @Override
    public void show() {
        batch = new SpriteBatch();

        float worldWidth = 80;
        float worldHeight = 50;

        BG = new Texture("gameAssets/4622732.png");

        cowText = new Texture("gameAssets/Untitled Drawing.png");
        cow = new Cow(cowText, 6.0, 20, 5);

    }

    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
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

        batch.draw(BG,0,0, worldWidth, worldHeight);

        batch.end();
    }
    public void logic(float delta){

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
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here.
    }
}
