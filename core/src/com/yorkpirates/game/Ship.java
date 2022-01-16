package com.yorkpirates.game;

import java.util.ArrayList;

import com.badlogic.gdx.utils.TimeUtils;

abstract class Ship extends DynamicObject {

    Integer Health = 500; //default placeholder value for health
    long shootingCooldown = 1000; // 1s cooldown
    long lastFiredTime;
    ArrayList<CannonBall> cannonBalls = new ArrayList<CannonBall>();

    public Ship(String imgName, Float xPos, Float yPos){
        super(imgName, xPos, yPos);
        lastFiredTime = TimeUtils.millis(); //used to measure time between shots in milliseconds, initialised here for conditional statement in Fire()
    }

    public void Fire(Float xCoord, Float yCoord){
        if (TimeUtils.timeSinceMillis(lastFiredTime) > shootingCooldown){
            lastFiredTime = TimeUtils.millis();
            CannonBall cb = new CannonBall("cannonball.png", getX()+getWidth()/2, getY()+getHeight()/2, xCoord, yCoord,this); // "this" is a reference to this object used for hitreg
            cannonBalls.add(cb);
            getStage().addActor(cb);    
        }
    }

    public void Hit(){
        //Do something, remove health etc.
        Health -= 1;
        System.out.println("I have been hit" + Health);
    }

}
