package com.yorkpirates.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

class College extends StaticObject{

    float Health = 10; //default placeholder value for health
    float maxHealth = 10;
    HealthBar healthBar;
    float offsetToCenter;
    float healthBarBuffer = 30f; //How high above the ship the health bar floats

    Integer plunder = 50; // Worth 50 by default
    Integer XPVALUE = 250; // Set value of XP, when player destroys it, it gains this much XP

    String affiliation; //Name of college
    Circle attackRange; //Invisible attack range, where the college will shoot at the player if they enter 
    Float attackRadius = 300f; // Radius of the attackRange Circle
    
    long shootingCooldown = 2000; // 2s cooldown
    long lastFiredTime;
    ArrayList<CannonBall> cannonBalls = new ArrayList<CannonBall>();
    

    public College(String imgName, Float xPos, Float yPos, String affiliation){
        super(imgName, xPos, yPos);
        this.affiliation = affiliation; 
        
        // attackRange = new Circle(attackRadius,this.getX(),this.getY());
        
    }

    // Called each frame
    @Override
    public void act(float delta){
        super.act(delta);
        Array<Actor> actors = getStage().getActors();

        for (int i = 0; i < actors.size; i++){
                
            if (actors.get(i) instanceof Ship){ // If the actor is a ship
                Ship targetShip = (Ship) actors.get(i); 
                //Measures distance from center of the college to the ship, if it is less than the attack radius, fire at it
                if (new Vector2(targetShip.getX(), targetShip.getY()).dst(new Vector2(getX()+((int)(getWidth()/2)), getY()+((int)(getHeight()/2)))) < attackRadius){
                    if (!this.affiliation.equals(targetShip.affiliation)){ // Check if the ship is an enemy of the college, i.e. not the same affiliations
                        Fire(targetShip.getX(),targetShip.getY());
                    }
                }   
            }     
        }
        if (healthBar != null){
            healthBar.setX(getX() + offsetToCenter);
            healthBar.setY(getY() + getHeight() + healthBarBuffer);
        }
    }

    /** Fires a cannonball from the center of the college towards a target
     *  only if the time elapsed since the last shot is greater than shootingCooldown.
     *  Cannonball is added to the same stage as the college object that fires it
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

    /** Removes 1 health from the college and spawning a healthbar upon being hit for the first time, 
     *  changing its affiliation to its attacker's college if it's health drops below 0.
     *  This function invoked by CannonBall class in its collision detection loop
     *  @param newAffiliation The attackers affiliation string e.g "goodricke"
     *  @return Boolean representing if college was destroyed in attack or not 
     */
    public Boolean Hit(String newAffiliation){
        if (Health == maxHealth){ //If this is the first hit taken, spawn a health bar above the ship
            healthBar = new HealthBar(this);
            offsetToCenter = (getWidth() - healthBar.fgSprite.getWidth()) / 2; //Helps to work out where to place the healthbar
            getStage().addActor(healthBar);
        }
        Health -= 1;
        if(Health <= 0){
            this.affiliation = newAffiliation; 
            sprite = new Sprite(new Texture(Gdx.files.internal(affiliation + "_college.png"))) ;
            Health = maxHealth;
            healthBar.remove();
            return true; //If college destroyed, return true to indicate college has been destroyed
        }
        return false; // If college not destroyed, return false, to indicate college has not been destroyed
    }
}