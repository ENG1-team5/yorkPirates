package com.yorkpirates.game;


public class EnemyShip extends Ship{

    float offsetToCenter;
    float healthBarBuffer = 30f; //How high above the ship the health bar floats

    public EnemyShip(String imgName, Float xPos, Float yPos,String affiliation){
        super(imgName, xPos, yPos,affiliation);

    }

    // Called each frame
    @Override
    public void act(float delta){
        super.act(delta);
        if (healthBar != null){
            healthBar.setX(getX() + offsetToCenter);
            healthBar.setY(getY() + getHeight() + healthBarBuffer);
        }
    }

    public Integer Hit(){
        if (Health == maxHealth){ //If this is the first hit taken, spawn a health bar above the ship
            healthBar = new HealthBar(this);
            offsetToCenter = (getWidth() - healthBar.fgSprite.getWidth()) / 2; //Helps to work out where to place the healthbar 
            getStage().addActor(healthBar);
        }
        return super.Hit();
    }

    public void explode(){
        healthBar.remove();
        remove();
    }
}
