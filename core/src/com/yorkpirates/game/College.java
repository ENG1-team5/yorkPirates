package com.yorkpirates.game;

import java.util.ArrayList;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

class College extends StaticObject{

    Integer Health = 500; //default placeholder value for health
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
                if (new Vector2(targetShip.getX(), targetShip.getY()).dst(new Vector2(getX(), getY())) < attackRadius){
                    if (this.affiliation != targetShip.affiliation){ // Check if the ship is an enemy of the college, i.e. not the same affiliations
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

    public void Hit(){
        //Do something, remove health etc.
        Health -= 1;
        System.out.println("College has been hit " + Health);
    }
}