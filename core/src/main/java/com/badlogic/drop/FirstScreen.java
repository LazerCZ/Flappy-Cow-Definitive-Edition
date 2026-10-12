package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;


/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {
    SpriteBatch batch;
    private FitViewport viewport;

    Texture BG = new Texture("gameAssets/TitleScreen.png");
    Texture playTexture;
    SimpleButton playSprite;

    TextField textField;

    Texture sliderTexture;
    SimpleSlider sliderSlider;

    Texture modeTexture;
    SimpleButton modeButton;

    float volume;
    int mode;

    @Override
    public void show() {
        // Prepare your screen here.

        batch = new SpriteBatch();

        float worldWidth = 80;
        float worldHeight = 50;

        volume = 0.0f;

        mode = 1;

        playTexture = new Texture("gameAssets/Play Button.png");
        playSprite = new SimpleButton(playTexture);

        playSprite.setSize(25, 15);
        playSprite.setCenter(40, 15);
        playSprite.setBounds();



        sliderTexture = new Texture("gameAssets/southern_comfort-2374724973.png");
        sliderSlider = new SimpleSlider(sliderTexture);

        sliderSlider.setSize(7,5);
        sliderSlider.setCenter(10,25);

        sliderSlider.setBounds();
        sliderSlider.setBG();

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


        batch.draw(BG,0, 0, worldWidth, worldHeight);


        playSprite.setCenter(40, 15);
        playSprite.draw(batch);

        sliderSlider.BG.draw(batch);
        sliderSlider.draw(batch);


        batch.end();
    }
    public void logic(float delta){
        Vector2 mousePos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        Vector2 mpUnconvert = viewport.unproject(mousePos);
        if (playSprite.touchingMouse(mpUnconvert)){
            playSprite.setSize(30, 18);
        }
        else {
            playSprite.setSize(25, 15);
        }
    }
    public void input(float delta){
        Vector2 mousePos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        Vector2 mpUnconvert = viewport.unproject(mousePos);

        if (sliderSlider.isPressed(mpUnconvert)){
            sliderSlider.slideSlider(mpUnconvert);
            volume = (float) sliderSlider.getValue() / 100;
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
