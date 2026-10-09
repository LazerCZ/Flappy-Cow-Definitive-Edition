package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class SimpleSlider extends SimpleButton {
    private int value;
    private Texture bgText;
    public Sprite BG;

    public SimpleSlider(Texture asset){
        super(asset);
        bgText = new Texture("gameAssets/SliderBG.png");
        this.BG = new Sprite(bgText);
        this.BG.setSize(this.getScaleX() * 3, this.getScaleY() / 2);
        this.BG.setPosition(this.getX(), this.getY() - (this.getHeight() / 2));
    }

    public void setBG(){
        this.BG.setSize(this.getWidth() * 3, this.getHeight() / 2);
        this.BG.setPosition(this.getButtonPos().x, getButtonPos().y + (this.getHeight() / 4));
    }


    public void slideSlider(Vector2 mousePos){
        this.setX(mousePos.x);
        this.setX(Math.clamp(this.getX(), BG.getX(), BG.getX() + BG.getWidth()));
    }
}
