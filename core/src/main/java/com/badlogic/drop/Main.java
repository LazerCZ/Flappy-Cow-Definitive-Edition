package com.badlogic.drop;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    FirstScreen fs;
    GameScreen gs;
    OverScreen os;
    SwoonScreen ss;
    @Override
    public void create() {
        fs = new FirstScreen();
        gs = new GameScreen();
        os = new OverScreen();
        ss = new SwoonScreen();

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
            if (gs.isBoss){
                ss.volume = gs.volume;
                ss.mode = gs.mode;
                ss.swoonY = gs.cow.getY();
                setScreen(ss);
            }
            else {
                os.volume = gs.volume;
                os.mode = gs.mode;
                setScreen(os);
            }
        }
        if (os.goScreen == 1){
            os.goScreen = 0;
            setScreen(fs);
        }
        if (os.goScreen == 2){
            os.goScreen = 0;
            setScreen(gs);
        }
        if (ss.timer >= 3){
            setScreen(os);
            ss.timer = 0;
        }
    }
}
