package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class EvilMilkStars extends Sprite {
    Hitbox milkHB;
    private float accel;
    public float angle;

    public EvilMilkStars(Texture asset){
        super(asset);
        setSize(6, 17);
        accel = -2.0f;
    }
    public boolean detectRectangle(Rectangle rect){
        milkHB = new Hitbox(getX(), getY() + getHeight() / 4, getWidth(), getHeight() / 2, true);
        return milkHB.overlaps(rect);
    }
    public void attack(){
        accel += 0.1f;
        setRotation(angle);
        translateX((float) Math.sin(angle) * accel);
        translateY((float) Math.cos(angle) * accel);
    }
}
