package com.yorkpirates.game;


public class EnemyShip extends Ship{
    HealthBar healthBar;

    public EnemyShip(String imgName, Float xPos, Float yPos,String affiliation){
        super(imgName, xPos, yPos,affiliation);

    }

    // Called each frame
    @Override
    public void act(float delta){
        super.act(delta);
    }

    public void Hit(){
        if (Health == maxHealth){ //If this is the first hit taken, spawn a health bar above the ship
            healthBar = new HealthBar(this);
            getStage().addActor(healthBar);
        }
        super.Hit();
    }

    public void explode(){
        healthBar.remove();
        remove();
    }
}
