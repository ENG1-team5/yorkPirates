package com.yorkpirates.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.TimeUtils;

abstract class Ship extends DynamicObject {
    Integer maxHealth = 5; //default placeholder value for health
    Integer Health = maxHealth; 
    HealthBar healthBar;
    
    long shootingCooldown = 1000; // 1s cooldown
    long lastFiredTime;
    ArrayList<CannonBall> cannonBalls = new ArrayList<CannonBall>();
    
    String affiliation; //College that the ship belongs to, could be changed to a reference to college?

    public Ship(String imgName, Float xPos, Float yPos,String affiliation){
        super(imgName, xPos, yPos);
        this.affiliation = affiliation;
        lastFiredTime = TimeUtils.millis(); //used to measure time between shots in milliseconds, initialised here for conditional statement in Fire()
    }

    public void Fire(Float xCoord, Float yCoord){
        if (TimeUtils.timeSinceMillis(lastFiredTime) > shootingCooldown){
            lastFiredTime = TimeUtils.millis();
            CannonBall cb = new CannonBall("cannonball.png", getX()+(getWidth()/2), getY()+(getHeight()/2), xCoord, yCoord,this); // "this" is a reference to this object used for hitreg
            cannonBalls.add(cb);
            getStage().addActor(cb);    
        }
    }

    public void Hit(){
        Health -= 1;
        if (Health <= 0){
            explode();
        }
        System.out.println("Ship hit " + Health);
    }

    public void explode(){
        // Add animation, or additional effect
        remove();
    }

}
