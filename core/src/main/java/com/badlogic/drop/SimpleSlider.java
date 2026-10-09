package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class SimpleSlider extends SimpleButton {
    private int value;
    private Texture bgText;
    private Sprite BG;

    public SimpleSlider(Texture asset){
        super(asset);
        bgText = new Texture("gameAssets/SliderBG.png");
        this.BG = new Sprite(bgText);
        this.BG.setSize(this.getScaleX() * 3, this.getScaleY() / 2);
        this.BG.setPosition(this.getX(), this.getY() - (this.getHeight() / 2));
    }

    public void slideSlider(Vector2 mousePos){
        this.setX(mousePos.x);
        this.setX(Math.clamp(this.getX(), BG.getX(), BG.getX() + BG.getWidth()));
    }
}
