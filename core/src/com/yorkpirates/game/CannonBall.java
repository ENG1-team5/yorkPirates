package com.yorkpirates.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class CannonBall extends DynamicObject{

    public CannonBall(String imgName, Float startX, Float startY, Float targetX, Float targetY){
        super(imgName, startX, startY);

        double angle = 180 / MathUtils.PI * MathUtils.atan2(targetY - startY, targetX - startX);
        setRotation((float) angle);
        //setX(getX()-getWidth());
        //setY(getY()-getHeight());
        
        speed = 1f;
    }

    // Anything in here is ran every frame the object is alive
    @Override
    public void act(float delta){
        super.act(delta);
    }
}
