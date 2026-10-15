package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import org.w3c.dom.Text;

public class GameScreen implements Screen {
    SpriteBatch batch;
    private FitViewport viewport;
    public float volume;
    public int mode;

    boolean notPaused;
    boolean start;
    boolean dead = false;

    boolean sleepy;
    boolean event;

    Texture BG;
    Texture pauseScreen;

    Texture rb;
    RoaringButcher Boss;

    Texture cowText;
    Cow cow;

    Texture bt;

    Array<Milk> milks;
    float milkTimer;

    Array<Cheese> cheeses;
    float cheeseTimer;
    Texture cheeseT;

    Texture bText;
    Butcher theButcher;

    Lives lifeCount;

    int allOfIt = 999999999;
    float bossThing;

    @Override
    public void show() {
        batch = new SpriteBatch();

        float worldWidth = 80;
        float worldHeight = 50;

        BG = new Texture("gameAssets/4622732.png");

        cowText = new Texture("gameAssets/Untitled Drawing.png");
        cow = new Cow(cowText, 0.2, 1, 0.1, -2, new Rectangle());
        cow.setSize(7,5);
        cow.setX(15);
        cow.setY(25);
        cow.skin(mode);
        cow.Hurtbox = new Rectangle(cow.getX(), cow.getY(), cow.getHeight(), cow.getWidth());

        bText = new Texture("gameAssets/Untitled Drawing (2).png");
        theButcher = new Butcher(bText, 1.3, 0.1, -2);
        theButcher.setSize(7,5);
        theButcher.setX(0);
        theButcher.setY(25);

        milks = new Array<>();

        bt = new Texture("gameAssets/TheBigMilk.png");

        milkTimer = 99.9f;

        cheeses = new Array<>();
        cheeseTimer = 0.0f;
        cheeseT = new Texture("gameAssets/DaCheese.png");

        notPaused = false;
        start = false;
        pauseScreen = new Texture("gameAssets/Pause screen.png");

        lifeCount = new Lives();
        lifeCount.setSize(15, 10);
        lifeCount.setPosition(65, 40);

        rb = new Texture("gameAssets/RoaringButcher.png");
        Boss = new RoaringButcher(rb, allOfIt + 1);
        Boss.setSize(30,25);
        Boss.setCenter(40, 25);
        Boss.setX(worldWidth);

        bossThing = 0;

        sleepy = false;
        event = false;
    }

    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        draw(delta);
        if(notPaused){
            logic(delta);
        }
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

        cow.draw(batch);
        theButcher.draw(batch);

        Boss.draw(batch);

        for (int i = 0; i < milks.size; i ++){
            Milk bilk = milks.get(i);
            bilk.draw(batch);
            bilk.upperBottle.draw(batch);
        }
        for(int i = 0; i < cheeses.size; i++){
            Cheese c = cheeses.get(i);
            c.draw(batch);
        }



        lifeCount.setLives(cow.getHP());
        lifeCount.draw(batch);

        if (!notPaused && start){
            batch.draw(pauseScreen,0,0, worldWidth, worldHeight);
        }
        batch.end();
    }
    public void logic(float delta){
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();
        if(cow.getY() > 4) {
            cow.fall();
            cow.translateY((float) cow.getGrav());
        }
        cow.setY(MathUtils.clamp(cow.getY(), 4, worldHeight - cow.getHeight()));

        if(theButcher.getY() > 4) {
            theButcher.fall();
            theButcher.translateY((float) theButcher.getGrav());
        }
        theButcher.setY(MathUtils.clamp(theButcher.getY(), 4, worldHeight - theButcher.getHeight()));

        if (theButcher.getY() + 3 < cow.getY()){
            theButcher.jump();
            theButcher.translateY((float) theButcher.getGrav());
        }
        for (int i = 0; i < milks.size; i ++){
            Milk bilk = milks.get(i);
            bilk.translateX( (float) (-1 * cow.getSpeed()));
            bilk.updateUpper();
            if (bilk.detectRectangle(cow.Hurtbox)){
                System.out.println("owie");
                cow.makeSound(2, volume);
                cow.damageCow(1);
                milks.removeIndex(i);
            }
            if (bilk.getX() < -1 * (bilk.getWidth())){
                milks.removeIndex(i);
            }
        }

        for(int i = 0; i < cheeses.size; i++){
            Cheese c = cheeses.get(i);
            c.translateX(-0.5f);
            c.setY(cow.getY());
            if(c.detectRectangle(cow.Hurtbox)){
                cow.makeSound(1, volume);
                cow.changeSpeed(0.1);
                cheeses.removeIndex(i);
            }
        }
        if (sleepy) {
            viewport.setScreenSize(viewport.getScreenWidth(), (int) Math.round((Math.sin(bossThing) + 1) * 500));
            viewport.setScreenPosition(viewport.getScreenX(), (int) Math.round((Math.sin(-bossThing) + 1) * 250));
        }
        if (!event) {
            milkTimer += delta;
        }

        if (milkTimer >= 7 - mode - ((cow.getSpeed() - 0.1) * 10) / (cow.getSpeed() * 2) && !event){
            createMilk();
            milkTimer = 0;
        }

        if (cow.getHP() <= 0){
            dead = true;
        }

        cheeseTimer += delta;

        if (cheeseTimer >= 10){
            createCheese();
            cheeseTimer = 0;
        }
        bossThing += delta;
        if (bossThing >= 5) {
            Boss.translateX(((worldWidth - Boss.getWidth()) - Boss.getX()) / 100);
            Boss.setY(12.5f + (float) Math.sin(bossThing * 3) * 7);
            event = true;
        }


    }
    public void input(float delta){
        boolean jumped = Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.UP) || Gdx.input.isKeyJustPressed(Input.Keys.W) || Gdx.input.isButtonJustPressed(Input.Buttons.LEFT);

        if (jumped){
            cow.jump();
            cow.translateY((float) cow.getGrav());
            cow.makeSound(3, volume);

            notPaused = true;
            start = true;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) && start){
            notPaused = !notPaused;
        }
    }

    private void createMilk(){
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        float width = 10;
        float height = (float) (Math.random() * 16) + 15;
        Milk bottle = new Milk(height, width, bt, worldHeight);
        bottle.setY(4);
        bottle.setX(worldWidth);
        bottle.skin(mode);

        milks.add(bottle);
    }
    private void createCheese(){
        float worldWidth = viewport.getWorldWidth();

        float width = 5;
        Cheese bottle = new Cheese(cheeseT, width);
        bottle.setX(worldWidth);

        cheeses.add(bottle);
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
