package com.yorkpirates.game;

public class EnemyShip extends Ship{
    public EnemyShip(String imgName, Float xPos, Float yPos,String affiliation){
        super(imgName, xPos, yPos,affiliation);
    }

    // Called each frame
    @Override
    public void act(float delta){
        super.act(delta);
    }
}
