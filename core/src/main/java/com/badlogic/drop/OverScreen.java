package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class OverScreen implements Screen {
    Texture BG = new Texture("gameAssets/Game over screen.png");
    SpriteBatch batch;
    private FitViewport viewport;
    public float volume;
    public int mode;
    public int goScreen = 0;

    Texture restartT;
    SimpleButton restart;

    Texture titleT;
    SimpleButton title;

    @Override
    public void show() {
        batch = new SpriteBatch();

        float worldWidth = 80;
        float worldHeight = 50;

        restartT = new Texture("gameAssets/Restart Button.png");
        restart = new SimpleButton(restartT);
        restart.setSize(25, 15);
        restart.setCenter(25, 15);
        restart.setBounds();

        titleT = new Texture("gameAssets/Title Button.png");
        title = new SimpleButton(titleT);
        title.setSize(25, 15);
        title.setCenter(55, 15);
        title.setBounds();
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
        batch.draw(BG, 0, 0, worldWidth, worldHeight);

        restart.setCenter(25, 15);
        title.setCenter(55, 15);

        restart.draw(batch);
        title.draw(batch);

        batch.end();
    }
    public void logic(float delta){
        Vector2 mousePos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        Vector2 mpUnconvert = viewport.unproject(mousePos);
        if (restart.touchingMouse(mpUnconvert)){
            restart.setSize(30, 18);
        }
        else {
            restart.setSize(25, 15);
        }

        if (title.touchingMouse(mpUnconvert)){
            title.setSize(30, 18);
        }
        else {
            title.setSize(25, 15);
        }
    }
    public void input(float delta){
        Vector2 mousePos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        Vector2 mpUnconvert = viewport.unproject(mousePos);

        if (restart.isJustPressed(mpUnconvert)){
            goScreen = 2;
        }
        if (title.isJustPressed(mpUnconvert)){
            goScreen = 1;
        }
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
