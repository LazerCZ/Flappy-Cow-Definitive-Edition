package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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

    Texture evilt;
    Sprite evil;

    float volume;
    int mode;

    boolean switchScreen;

    @Override
    public void show() {
        // Prepare your screen here.

        batch = new SpriteBatch();

        float worldWidth = 80;
        float worldHeight = 50;

        switchScreen = false;

        volume = 0.0f;

        mode = 0;

        playTexture = new Texture("gameAssets/Play Button.png");
        playSprite = new SimpleButton(playTexture);

        playSprite.setSize(25, 15);
        playSprite.setCenter(40, 15);
        playSprite.setBounds();

        modeTexture = new Texture("gameAssets/ClassicMode.png");
        modeButton = new SimpleButton(modeTexture);

        modeButton.setSize(20, 15);
        modeButton.setCenter(70, 15);
        modeButton.setBounds();

        sliderTexture = new Texture("gameAssets/southern_comfort-2374724973.png");
        sliderSlider = new SimpleSlider(sliderTexture);

        sliderSlider.setSize(7,5);
        sliderSlider.setCenter(10,25);

        sliderSlider.setBounds();
        sliderSlider.setBG();

        evilt = new Texture("gameAssets/ywah.png");
        evil = new Sprite(evilt);
        evil.setSize(35,25);
        evil.setCenter(15, 6);

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

        modeButton.setCenter(70, 15);
        modeButton.draw(batch);

        evil.setCenter((float) (15 + (1 - 2 * Math.random())), (float) (6 + (1 - 2 * Math.random())));
        evil.draw(batch);


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

        if (modeButton.touchingMouse(mpUnconvert)){
            modeButton.setSize(25, 18);
        }
        else {
            modeButton.setSize(20, 15);
        }
    }
    public void input(float delta){
        Vector2 mousePos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        Vector2 mpUnconvert = viewport.unproject(mousePos);

        if (sliderSlider.isPressed(mpUnconvert)){
            sliderSlider.slideSlider(mpUnconvert);
            volume = (float) sliderSlider.getValue() / 100;
        }

        if (modeButton.isJustPressed(mousePos)){
            Texture m1 = new Texture("gameAssets/ClassicMode.png");
            Texture m2 = new Texture("gameAssets/ChocoMode.png");
            Texture m3 = new Texture("gameAssets/StrawMode.png");
            Texture m4 = new Texture("gameAssets/NeaMode.png");

            mode ++;
            if (mode > 3){
                mode = 0;
            }
            switch (mode){
                case 1:
                    modeButton.setTexture(m2);
                    System.out.println("Chocolate Mode");
                    break;
                case 2:
                    modeButton.setTexture(m3);
                    System.out.println("Strawberry Mode");
                    break;
                case 3:
                    modeButton.setTexture(m4);
                    System.out.println("Neapolitan Mode");
                    break;
                default:
                    modeButton.setTexture(m1);
                    System.out.println("Classic Mode");
                    break;
            }
        }
        if (playSprite.isJustPressed(mousePos) && !(mode == 3)){
            switchScreen = true;
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
