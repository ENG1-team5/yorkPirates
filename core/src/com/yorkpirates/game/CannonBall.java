package com.yorkpirates.game;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class CannonBall extends DynamicObject{

    long timeToLive = 5000; //5s
    long timeFired;
    Integer damage = 10; // 10 HP damage 
    StaticObject owner;

    public CannonBall(String imgName, Float startX, Float startY, Float targetX, Float targetY, StaticObject owner){
        super(imgName, startX, startY);
        this.owner = owner;
        setX(getX() - getWidth()/2); // Changes the start location to the center of the ship
        setY(getY() - getHeight()/2);

        double angle = 180 / MathUtils.PI * MathUtils.atan2((targetY+(getHeight()/2)) - startY, (targetX+(getWidth()/2)) - startX);
        setRotation((float) angle);

        speed = 5f;

        timeFired = TimeUtils.millis();
    }

    /** Removes object from stage and perform any additional code specified in the function*/
    public void explode(){
        remove();
    }

    // Anything in here is ran every frame the object is alive
    @Override
    public void act(float delta){
        super.act(delta);
        if (TimeUtils.timeSinceMillis(timeFired) > timeToLive){
            explode(); //Removes cannonball from stage
            return;
        }
        Array<Actor> actors = getStage().getActors();
        for (int i = 0; i < actors.size; i++){
            if (owner instanceof PlayerShip){
                // Bullet is owned by player and has hit EnemyShip
                if(actors.get(i) instanceof EnemyShip){
                    EnemyShip eShip = (EnemyShip) actors.get(i);
                    if ( Intersector.overlapConvexPolygons(collisionBox, eShip.collisionBox) ){
                        Boolean hasDestroyed = eShip.Hit();
                        if (hasDestroyed){
                            ((PlayerShip) owner).plunder += eShip.plunder;
                            ((PlayerShip) owner).XP += eShip.XPVALUE;
                        }
                        explode();
                    }
                }
                
                    //Bullet is owned by player and hits college
                if(actors.get(i) instanceof College){
                    College college = (College) actors.get(i);
                    if (!college.affiliation.equals(((PlayerShip)owner).affiliation)){ //Makes it so players cannot friendly fire their own colleges.
                        if ( Intersector.overlapConvexPolygons(collisionBox, college.collisionBox) ){
                            Boolean hasDestroyed = college.Hit(((PlayerShip)owner).affiliation);
                            if (hasDestroyed){
                                ((PlayerShip) owner).plunder += college.plunder;
                                ((PlayerShip) owner).XP += college.XPVALUE;
                            }
                            explode();
                        }
                    }
                }
            }
            
            else if (owner instanceof EnemyShip){
                //Bullet is owned by EnemyShip and hits player
                if(actors.get(i) instanceof PlayerShip){
                    PlayerShip pShip = (PlayerShip) actors.get(i);
                    if (Intersector.overlapConvexPolygons(collisionBox, pShip.collisionBox)){
                        pShip.Hit();
                        explode();
                    }
                }
            }

            else if (owner instanceof College) {
                //Bullet fired by a college hits any ship 
                if(actors.get(i) instanceof Ship){
                    Ship targetShip = (Ship) actors.get(i);
                    if (!targetShip.affiliation.equals(((College)owner).affiliation)){ // This makes it so cannonballs fired by a college pass over friendly ships, could be changed
                        if (Intersector.overlapConvexPolygons(collisionBox, targetShip.collisionBox)){
                            targetShip.Hit();
                            explode();
                        }
                    }
                }
                }
            }  
        }

    @Override
    public boolean checkActorCollisions() { return false; }
}