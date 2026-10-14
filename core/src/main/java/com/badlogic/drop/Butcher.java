package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Butcher extends Sprite {
    double gravity;
    private final double jumpPower;
    private final double gravMod;
    private final double tVelocity;

    public Butcher(Texture asset, double JP, double gravMod, double tVeloci){
        super(asset);
        gravity = 0;
        this.jumpPower = JP;
        this.gravMod = gravMod;
        this.tVelocity = tVeloci;
    }
    public void fall(){
        if (gravity > tVelocity) {
            gravity -= gravMod;
        }
    }
    public double getGrav(){
        return gravity;
    }
    public void jump(){
        gravity = jumpPower;
    }
}
