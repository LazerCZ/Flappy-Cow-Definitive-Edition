package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class RoaringButcher extends Sprite {
    private int health;
    private int attackNum;
    private int lastAttack;

    public RoaringButcher(Texture asset, int HP){
        super(asset);
        this.health = HP;
    }

    public void takeDamage(int value){
        health -= value;
    }

    public int getHP(){
        return health;
    }
}
