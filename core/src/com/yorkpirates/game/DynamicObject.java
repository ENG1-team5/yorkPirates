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

        float oldX = getX();
        float oldY = getY();

        float newX = oldX + MathUtils.cosDeg(getRotation()) * speed;
        float newY = oldY + MathUtils.sinDeg(getRotation()) * speed;

        if (newX < 0 || 3200 < newX) { newX = oldX; }
        if (newY < 0 || 3200 < newY) { newY = oldY; }

        setX(newX);
        setY(newY);
    }
    
}
