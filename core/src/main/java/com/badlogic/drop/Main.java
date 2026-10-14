package com.badlogic.drop;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    FirstScreen fs;
    GameScreen gs;
    OverScreen os;
    @Override
    public void create() {
        fs = new FirstScreen();
        gs = new GameScreen();
        os = new OverScreen();

        setScreen(fs);
    }

    @Override
    public void render() {
        super.render();
        if (fs.switchScreen){
            gs.mode = fs.mode;
            gs.volume = fs.volume;
            setScreen(gs);
            fs.switchScreen = false;
        }
        if (gs.dead){
            gs.dead = false;
            os.volume = gs.volume;
            os.mode = gs.mode;
            setScreen(os);
        }
        if (os.goScreen == 1){
            os.goScreen = 0;
            setScreen(fs);
        }
        if (os.goScreen == 2){
            os.goScreen = 0;
            setScreen(gs);
        }
    }
}
