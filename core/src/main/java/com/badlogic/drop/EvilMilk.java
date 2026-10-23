package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;


public class EvilMilk extends Sprite {
    Hitbox milkHB;
    private float accel;
    public float angle = 0.0f;

    public EvilMilk(Texture asset){
        super(asset);
        setSize(17, 6);
        accel = 2.0f;
    }
    public boolean detectRectangle(Rectangle rect){
        milkHB = new Hitbox(getX() + getWidth() / 8, getY() + getHeight() / 4, getWidth() / 4, getHeight() / 2, true);
        return milkHB.overlaps(rect);
    }
    public void attack(){
        accel -= 0.1f;
        translateX(accel);

    }
}
