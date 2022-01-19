package com.yorkpirates.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class CannonBall extends DynamicObject{

    long timeToLive = 5000; //5s
    long timeFired;
    Integer damage = 10; // 10 HP damage 
    Ship Owner;
    

    public CannonBall(String imgName, Float startX, Float startY, Float targetX, Float targetY,Ship Owner){
        super(imgName, startX, startY);
        this.Owner = Owner;
        setX(getX() - getWidth()/2); // Changes the start location to the center of the ship
        setY(getY() - getHeight()/2);

        double angle = 180 / MathUtils.PI * MathUtils.atan2((targetY-getHeight()/2) - startY, (targetX-getWidth()/2) - startX);
        setRotation((float) angle);

        speed = 3f;

        timeFired = TimeUtils.millis();
    }

    public void explode(){
        remove();
    }

    // Anything in here is ran every frame the object is alive
    @Override
    public void act(float delta){
        super.act(delta);
        if (TimeUtils.timeSinceMillis(timeFired) > timeToLive){
            explode(); //Removes cannonball from stage
        }
        else{
            Array<Actor> actors = getStage().getActors();
    
            for (int i = 0; i < actors.size; i++){
                // Bullet is owned by player and has hit EnemyShip
                if (actors.get(i) instanceof EnemyShip && Owner instanceof PlayerShip){
                    EnemyShip eShip = (EnemyShip) actors.get(i);
                    if (collisionBox.overlaps(eShip.collisionBox)){
                        eShip.Hit();
                        explode();
                    }
                }
                if (actors.get(i) instanceof PlayerShip && Owner instanceof EnemyShip){
                    PlayerShip pShip = (PlayerShip) actors.get(i);
                    if (collisionBox.overlaps(pShip.collisionBox)){
                        pShip.Hit();
                        explode();
                    }
                }
            }  
        } 
    }
}
