package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Lives extends Sprite {
    private final Texture l1 = new Texture("gameAssets/Lives3.png");
    private final Texture l2 = new Texture("gameAssets/Lives2.png");
    private final Texture l3 = new Texture("gameAssets/Lives1.png");
    private int value;

    public Lives(){
        super(new Texture("gameAssets/Lives1.png"));
        value = 3;
    }
    public void setLives(int val){
        switch (val){
            case 1:
                setTexture(l1);
                break;
            case 2:
                setTexture(l2);
                break;
            default:
                setTexture(l3);
                break;
        }
        value = val;
    }

}
