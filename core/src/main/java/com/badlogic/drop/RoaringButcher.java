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
        this.lastAttack = 0;
        this.attackNum = 0;
    }

    public void takeDamage(int value){
        health -= value;
    }

    public int getHP(){
        return health;
    }

    public void chooseAttack(){
        attackNum = (int) Math.round(1 + (Math.random() * 1));

        if (attackNum == lastAttack){
            while (attackNum == lastAttack){
                attackNum = (int) Math.round(1 + (Math.random() * 1));
            }
        }

        lastAttack = attackNum;

    }
    public int getAttackNum(){
        return attackNum;
    }
}
