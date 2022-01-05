package com.yorkpirates.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public class CannonBall extends DynamicObject{

    public CannonBall(String imgName, Float startX, Float startY, Float targetX, Float targetY){
        super(imgName, startX, startY);

        // Point the cannonball towards target x,y
        double angle = 180 / MathUtils.PI * MathUtils.atan2(startY - targetY, startX - targetX);
        setRotation((float) angle);

        speed = 1f;
    }

    // Anything in here is ran every frame the object is alive
    @Override
    public void act(float delta){
        super.act(delta);
    }
}
