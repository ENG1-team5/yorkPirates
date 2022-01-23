package com.yorkpirates.game;

import java.util.ArrayList;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

class College extends StaticObject{

    Integer Health = 5; //default placeholder value for health
    Integer maxHealth = 5;
    HealthBar healthBar;

    String affiliation; //Name of college
    Circle attackRange; //Invisible attack range, where the college will shoot at the player if they enter 
    Float attackRadius = 100f; // Radius of the attackRange Circle
    
    long shootingCooldown = 2000; // 2s cooldown
    long lastFiredTime;
    ArrayList<CannonBall> cannonBalls = new ArrayList<CannonBall>();

    public College(String imgName, Float xPos, Float yPos, String affiliation){
        super(imgName, xPos, yPos);
        this.affiliation = affiliation;
        
        attackRange = new Circle(attackRadius,this.getX(),this.getY());
        
    }

    // Called each frame
    @Override
    public void act(float delta){
        super.act(delta);
        Array<Actor> actors = getStage().getActors();

        for (int i = 0; i < actors.size; i++){
                
            if (actors.get(i) instanceof Ship){ // If the actor is a ship
                Ship targetShip = (Ship) actors.get(i); 
                if (Intersector.overlaps(attackRange, targetShip.collisionBox)){
                    if (!this.affiliation.equals(targetShip.affiliation)){ // Check if the ship is an enemy of the college, i.e. not the same affiliations
                        Fire(targetShip.getX(),targetShip.getY());
                    }
                }   
            }     
        }
    }

    public void Fire(Float xCoord, Float yCoord){
        if (TimeUtils.timeSinceMillis(lastFiredTime) > shootingCooldown){
            lastFiredTime = TimeUtils.millis();
            CannonBall cb = new CannonBall("cannonball.png", getX()+(getWidth()/2), getY()+(getHeight()/2), xCoord, yCoord,this); // "this" is a reference to this object used for hitreg
            cannonBalls.add(cb);
            getStage().addActor(cb);    
        }
    }

    public void Hit(String newAffiliation){
        //Do something, remove health etc.
        if (Health == maxHealth){ //If this is the first hit taken, spawn a health bar above the ship
            healthBar = new HealthBar(this);
            getStage().addActor(healthBar);
        }
        Health -= 1;
        if(Health <= 0){
            this.affiliation = newAffiliation; 
            Health = maxHealth;
        }
        System.out.println("College has been hit " + Health);
    }
}