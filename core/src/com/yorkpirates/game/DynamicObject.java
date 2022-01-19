package com.yorkpirates.game;

import com.badlogic.gdx.math.MathUtils;

abstract class DynamicObject extends StaticObject{
    Float speed = 0f;

    public DynamicObject(String imgName, Float xPos, Float yPos){
        super(imgName, xPos, yPos);
        // TO DO
    }

    @Override
    protected void positionChanged(){
        sprite.setPosition(getX(), getY());
    }

    @Override
    public void act(float delta){
        super.act(delta);
        setX(getX() + MathUtils.cosDeg(getRotation()) * speed);
        setY(getY() + MathUtils.sinDeg(getRotation()) * speed);
    }
    
}
