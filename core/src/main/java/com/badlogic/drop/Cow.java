package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Cow extends Sprite {
    private int lives;
    private double speed;
    private final double jumpPower;
    private double gravity;
    private final double gravMod;
    private final double tVelocity;
    public Rectangle Hurtbox;

    public Cow(Texture asset, double baseSpeed, double JP, double gravMod, double tVelocity, Rectangle hurtbox){
        super(asset);
        this.speed = baseSpeed;
        this.jumpPower = JP;
        this.gravMod = gravMod;
        this.tVelocity = tVelocity;
        this.Hurtbox = hurtbox;
        this.lives = 3;
    }

    public double getSpeed(){
        return speed;
    }

    public void changeSpeed(double value){
        speed += value;
    }
    public void jump(){
        gravity = jumpPower;
    }
    public int getHP(){
        return lives;
    }
    public void damageCow(int value){
        lives -= value;
    }
    public void skin(int value){
        Texture s1 = new Texture("gameAssets/Untitled Drawing.png");
        Texture s2 = new Texture("gameAssets/ChocoCow.png");
        Texture s3 = new Texture("gameAssets/StrawCow.png");
        switch (value){
            case 1:
                this.setTexture(s2);
                break;
            case 2:
                this.setTexture(s3);
                break;
            default:
                this.setTexture(s1);
                break;
        }
    }
    public void makeSound(int Sound,float volume){
        Sound moo = Gdx.audio.newSound(Gdx.files.internal("gameAssets/cow3.mp3"));
        Sound hurt = Gdx.audio.newSound(Gdx.files.internal("gameAssets/cowhurt3.mp3"));
        Sound jump = Gdx.audio.newSound(Gdx.files.internal("gameAssets/jump.mp3"));
        switch (Sound){
            case 2:
                hurt.play(volume);
                break;
            case 3:
                jump.play(volume);
                break;
            default:
                moo.play(volume);
                break;
        }


    }
    public void fall(){
        if (gravity > tVelocity) {
            gravity -= gravMod;
        }
        Hurtbox.setPosition(getX(), getY());
    }
    public double getGrav(){
        return gravity;
    }
}
