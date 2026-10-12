package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class SimpleSlider extends SimpleButton {
    private int value;
    public Sprite BG;
    private boolean pressing;

    public SimpleSlider(Texture asset){
        super(asset);
        Texture bgText = new Texture("gameAssets/SliderBG.png");
        this.BG = new Sprite(bgText);
        this.BG.setSize(this.getScaleX() * 3, this.getScaleY() / 2);
        this.BG.setPosition(this.getX(), this.getY() - (this.getHeight() / 2));
        this.pressing = false;
    }

    public void setBG(){
        this.BG.setSize(this.getWidth() * 3, this.getHeight() / 2);
        this.BG.setPosition(this.getButtonPos().x, getButtonPos().y + (this.getHeight() / 4));
    }


    public void slideSlider(Vector2 mousePos){
        this.setX(mousePos.x - this.getWidth() / 2);
        this.setX(Math.clamp(this.getX(), BG.getX(), BG.getX() + BG.getWidth() - 7));
        value = Math.round(BG.getX() + (this.getX() - BG.getX())) - 7;
        value = Math.round(value / (this.getX() -BG.getX() + BG.getWidth() - 7) * 200);
    }

    public int getValue(){
        return value;
    }

    boolean canMoo = false;

    public boolean isPressed(Vector2 mousePos){

        if (mousePos.x > this.getButtonPos().x && mousePos.x < this.getButtonPos2().x && mousePos.y > this.getButtonPos().y && mousePos.y < this.getButtonPos2().y){
            pressing = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
            canMoo = true;
            return Gdx.input.isButtonPressed(Input.Buttons.LEFT);
        }
        if(pressing){
            pressing = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
        }
        if(canMoo){

        }
        return pressing;
    }
}
