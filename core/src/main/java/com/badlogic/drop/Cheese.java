package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Cheese extends Sprite {
    float size;
    Hitbox cheeseHB;

    public Cheese(Texture asset, float size){
        super(asset);
        this.size = size;
        setSize(size, size);
        this.cheeseHB = new Hitbox(getX(), getY(), size, size, true);
    }
    public boolean detectRectangle(Rectangle rect){
        cheeseHB.setPosition(getX(), getY());
        return cheeseHB.detectRectangle(rect);
    }
}
