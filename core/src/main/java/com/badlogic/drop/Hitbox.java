package com.badlogic.drop;

import com.badlogic.gdx.math.Rectangle;

public class Hitbox extends Rectangle {
    boolean enabled;
    public Hitbox(float x, float y, float width, float height, boolean enabled){
        super(x,y,width,height);
        this.enabled = enabled;
    }
    public boolean detectRectangle(Rectangle target){
        if (enabled) {
            return overlaps(target);
        }
        return false;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
