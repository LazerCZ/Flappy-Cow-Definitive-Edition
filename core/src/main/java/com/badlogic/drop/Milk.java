package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;


public class Milk extends Sprite {
    Sprite upperBottle;
    Hitbox milkHB;
    float worldHeight;

    public Milk(float height, float width, Texture asset, float worldHeight){
        super(asset);
        setSize(width, height);
        upperBottle = new Sprite(asset);
        upperBottle.setSize(width,  -1 * (worldHeight - height - 15));
        this.worldHeight = worldHeight;
    }
    public boolean detectRectangle(Rectangle rect){
        milkHB = new Hitbox(getX() + getWidth() / 4, getY(), getWidth() / 2, getHeight() - 5, true);
        if (milkHB.overlaps(rect)){
            return true;
        }
        milkHB.set(upperBottle.getX() + upperBottle.getWidth() / 4, upperBottle.getY() - Math.abs(upperBottle.getHeight()) + 5, upperBottle.getWidth() / 2, upperBottle.getY());
        return milkHB.detectRectangle(rect);
    }
    public void updateUpper(){
        upperBottle.setPosition(getX(), worldHeight);
    }
    public void skin(int value){
        Texture s1 = new Texture("gameAssets/TheBigMilk.png");
        Texture s2 = new Texture("gameAssets/ChocoMilk.png");
        Texture s3 = new Texture("gameAssets/StrawMilk.png");
        Texture s4 = new Texture("gameAssets/EvilMilk2.png");
        switch (value){
            case 1:
                this.setTexture(s2);
                upperBottle.setTexture(s2);
                break;
            case 2:
                this.setTexture(s3);
                upperBottle.setTexture(s3);
                break;
            case 3:
                this.setTexture(s4);
                upperBottle.setTexture(s4);
                break;
            default:
                this.setTexture(s1);
                upperBottle.setTexture(s1);
                break;
        }
    }



}
