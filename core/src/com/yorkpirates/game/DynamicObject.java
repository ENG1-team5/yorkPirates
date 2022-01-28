package com.yorkpirates.game;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

abstract class DynamicObject extends StaticObject{
    Float speed = 0f;
    Boolean collisionFlag = false;

    private float oldX;
    private float oldY;
    private float oldoldX;
    private float oldoldY;

    public DynamicObject(String imgName, Float xPos, Float yPos){
        super(imgName, xPos, yPos);
    }

    @Override
    protected void positionChanged(){
        sprite.setPosition(getX(), getY());
    }

    @Override
    public void act(float delta){
        super.act(delta);

        oldoldX = oldX;
        oldoldY = oldY;
        
        oldX = getX();
        oldY = getY();

        float newX = oldX + MathUtils.cosDeg(getRotation()) * speed;
        float newY = oldY + MathUtils.sinDeg(getRotation()) * speed;

        setX(newX);
        setY(newY);

        // If we collide, don't move into the object we hit
        if (checkActorCollisions()) {
            speed = 0f;
            // oldold used to avoid clipping into the impacted object
            // Causes a slight bounce effect at high speeds
            // I think it looks cool so I'm not bothered
            setX(oldoldX); setY(oldoldY);
        }
    }

    public boolean checkActorCollisions() {
        /**
         * Check if this actor is colliding with any other actors or is out of bounds
         * @returns: true if actor is colliding, false otherwise
         */

        // Future: replace these with the cloud tiles
        if (getX() < 0 || getStage().getWidth() < getX()) {
            return true;
        }
        if (getY() < 0 || getStage().getHeight() < getY()) {
            return true;
        }

        Array<Actor> actors = getStage().getActors();

        for (Actor actor : actors) {
            if (actor.equals(this) || actor instanceof CannonBall) {
                continue;
            }
            if (actor instanceof StaticObject) {
                StaticObject x = (StaticObject)actor;
                if (Intersector.overlapConvexPolygons(x.collisionBox, this.collisionBox)) {
                    return true;
                }
            }
        }
        return false;
    }
}
