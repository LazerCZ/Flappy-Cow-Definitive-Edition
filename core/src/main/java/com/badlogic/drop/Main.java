package com.badlogic.drop;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    FirstScreen fs;
    GameScreen gs;
    @Override
    public void create() {
        fs = new FirstScreen();
        gs = new GameScreen();

        setScreen(fs);
    }

    @Override
    public void render() {
        super.render();
        if (fs.switchScreen){
            setScreen(gs);
            gs.mode = fs.mode;
            gs.volume = fs.volume;
        }
    }
}
