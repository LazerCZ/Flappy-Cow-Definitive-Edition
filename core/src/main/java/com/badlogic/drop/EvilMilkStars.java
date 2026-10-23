package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class EvilMilkStars extends Sprite {
    Hitbox milkHB;
    private float accel;
    private float angle;
    private boolean goAgain;

    public EvilMilkStars(Texture asset){
        super(asset);
        setSize(9, 3);
        accel = -2.0f;
        goAgain = true;
    }
    public boolean detectRectangle(Rectangle rect){
        milkHB = new Hitbox(getX(), getY() + getHeight() / 4, getWidth(), getHeight() / 2, true);
        return milkHB.overlaps(rect);
    }
    public void attack(){
        if (goAgain){
            goAgain = false;
            accel = -2.0f;
        }

        accel += 0.1f;
        setOrigin(getWidth() / 2, getHeight() / 2);
        setRotation(-angle + 270);
        translateX((float) (Math.sin(MathUtils.degreesToRadians * angle) * accel));
        translateY((float) (Math.cos(MathUtils.degreesToRadians * angle) * accel));
    }
    public void attack2(){
        accel += 0.1f;
        setOrigin(getWidth() / 2, getHeight() / 2);
        setRotation(-angle + 270);
        translateX(-accel);
    }
    public boolean starBool(){
        return goAgain;
    }
    public float getAngle(){
        return angle;
    }
    public void setAngle(float amng){
        angle = amng;
    }

}
