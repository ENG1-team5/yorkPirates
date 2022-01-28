package com.yorkpirates.game;

import java.util.ArrayList;

import com.badlogic.gdx.utils.TimeUtils;

abstract class Ship extends DynamicObject {
    Integer maxHealth = 5; //default placeholder value for health
    Integer Health = maxHealth; 
    HealthBar healthBar;

    Integer plunder = 10; //All ships start holding 10 plunder, even the playership
    
    long shootingCooldown = 1000; // 1s cooldown
    long lastFiredTime;
    ArrayList<CannonBall> cannonBalls = new ArrayList<CannonBall>();
    
    String affiliation; //College that the ship belongs to, could be changed to a reference to college?

    public Ship(String imgName, Float xPos, Float yPos,String affiliation){
        super(imgName, xPos, yPos);
        this.affiliation = affiliation;
        lastFiredTime = TimeUtils.millis(); //used to measure time between shots in milliseconds, initialised here for conditional statement in Fire()
    }

    /** Fires a cannonball from the center of the ship towards a target
     *  only if the time elapsed since the last shot is greater than shootingCooldown.
     *  Cannonball is added to the same stage as the ship object that fires it
     *  @param xCoord x target of the cannonball
     *  @param yCoord y target of the cannonball
     *  */ 
    public void Fire(Float xCoord, Float yCoord){
        if (TimeUtils.timeSinceMillis(lastFiredTime) > shootingCooldown){
            lastFiredTime = TimeUtils.millis();
            CannonBall cb = new CannonBall("cannonball.png", getX()+(getWidth()/2), getY()+(getHeight()/2), xCoord, yCoord,this); // "this" is a reference to this object used for hitreg
            cannonBalls.add(cb);
            getStage().addActor(cb);    
        }
    }

    /**Takes 1 health of of the ship. 
     * Checks if the health of the ship is less than 0, removing it from the stage if so
     * @return Boolean representing if the ship has been destroyed by the attack
     */
    public Boolean Hit(){ 
        Health -= 1;
        if (Health <= 0){
            explode();
            return true; // If ship is destroyed, return any plunder it holds
        }
        return false; //If ship not destroyed, return no plunder
    }

    /** Performed on death. Removes object from stage and perform any additional code specified in the function*/
    public void explode(){
        remove();
    }

}
