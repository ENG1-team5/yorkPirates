package com.yorkpirates.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public class CannonBall extends DynamicObject{

    public CannonBall(String imgName, Float xPos, Float yPos){
        super(imgName, xPos, yPos);

        // Point the cannonball towards the mouse pointer
        double angle = 180 / MathUtils.PI * MathUtils.atan2(yPos - Gdx.input.getY(), xPos - Gdx.input.getX());
        setRotation((float) angle);

        speed = 1f;
    }

    // Anything in here is ran every frame the object is alive
    @Override
    public void act(float delta){
        super.act(delta);
    }
}
