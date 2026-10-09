package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class SimpleButton extends Sprite {
    private Vector2 buttonPos;
    private Vector2 buttonPos2;

    public SimpleButton(Texture asset){
        super(asset);
        this.buttonPos = new Vector2(this.getX(), this.getY());
        this.buttonPos2 = new Vector2(this.getX() + this.getWidth(), this.getY() + this.getHeight());
    }

    public void setBounds(){
        this.buttonPos = new Vector2(this.getX(), this.getY());
        this.buttonPos2 = new Vector2(this.getX() + this.getWidth(), this.getY() + this.getHeight());
    }

    public boolean touchingMouse(Vector2 mousePos){
        return mousePos.x > buttonPos.x && mousePos.x < buttonPos2.x && mousePos.y > buttonPos.y && mousePos.y < buttonPos2.y;
    }

    public boolean isPressed(Vector2 mousePos){
        if (mousePos.x > buttonPos.x && mousePos.x < buttonPos2.x && mousePos.y > buttonPos.y && mousePos.y < buttonPos2.y){
            return Gdx.input.isButtonPressed(Input.Buttons.LEFT);
        }
        return false;
    }
    public Vector2 getButtonPos(){
        return buttonPos;
    }
}
