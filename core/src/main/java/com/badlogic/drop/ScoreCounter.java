package com.badlogic.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScoreCounter {
    private final Texture t1 = new Texture("gameAssets/ScoreAssets/1.png");
    private final Texture t2 = new Texture("gameAssets/ScoreAssets/2.png");
    private final Texture t3 = new Texture("gameAssets/ScoreAssets/3.png");
    private final Texture t4 = new Texture("gameAssets/ScoreAssets/4.png");
    private final Texture t5 = new Texture("gameAssets/ScoreAssets/5.png");
    private final Texture t6 = new Texture("gameAssets/ScoreAssets/6.png");
    private final Texture t7 = new Texture("gameAssets/ScoreAssets/7.png");
    private final Texture t8 = new Texture("gameAssets/ScoreAssets/8.png");
    private final Texture t9 = new Texture("gameAssets/ScoreAssets/9.png");
    private final Texture t0 = new Texture("gameAssets/ScoreAssets/0.png");
    private final Texture ts = new Texture("gameAssets/ScoreAssets/Score_.png");


    public int count = 0;

    private final SpriteBatch batch;

    public ScoreCounter(int startScore, SpriteBatch batch){
        this.count = startScore;
        this.batch = batch;
    }

    public void drawScore(float xPos, float yPos, float xSize, float ySize){
        String countString = String.valueOf(count);
        for (int i = 0; i < countString.length(); i++){
            batch.draw(getTexture(Integer.parseInt(String.valueOf(countString.charAt(i)))), xPos + (xSize * i) + (xSize * 6), yPos, xSize, ySize);
            batch.draw(ts, xPos, yPos, (float) (xSize * 5), ySize);
        }
    }
    private Texture getTexture(int value){
        switch (value) {
            case 1:
                return t1;
            case 2:
                return t2;
            case 3:
                return t3;
            case 4:
                return t4;
            case 5:
                return t5;
            case 6:
                return t6;
            case 7:
                return t7;
            case 8:
                return t8;
            case 9:
                return t9;
            default:
                return t0;
        }
    }
}
