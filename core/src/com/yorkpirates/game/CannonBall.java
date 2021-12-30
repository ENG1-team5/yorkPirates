package com.yorkpirates.game;

public class CannonBall extends DynamicObject{

    public CannonBall(String imgName, Integer xPos, Integer yPos){
        super(imgName, xPos, yPos);
    }

    // Anything in here is ran every frame the object is alive
    @Override
    public void act(float delta){
        super.act(delta);
    }
}
