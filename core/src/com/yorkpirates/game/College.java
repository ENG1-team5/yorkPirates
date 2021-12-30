package com.yorkpirates.game;

abstract class College extends StaticObject{

    public College(String imgName, Integer xPos, Integer yPos){
        super(imgName, xPos, yPos);
    }

    // Called each frame
    @Override
    public void act(float delta){
        super.act(delta);
    }
}