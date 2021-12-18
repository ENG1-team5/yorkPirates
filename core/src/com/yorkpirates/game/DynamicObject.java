package com.yorkpirates.game;

abstract class DynamicObject extends StaticObject{
    float speed = 1;
    float rotationSpeed = 1;

    public DynamicObject(String imgName, Integer xPos, Integer yPos){
        super(imgName, xPos, yPos);
        // TO DO
    }
    @Override
    protected void positionChanged(){
        sprite.setPosition(getX(), getY());
    }
    
}
