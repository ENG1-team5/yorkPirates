package com.yorkpirates.game;

import java.util.Arrays;
import java.util.stream.Stream;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

abstract class DynamicObject extends StaticObject{
    Float speed = 0f;
    Boolean collisionFlag = F

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

        float oldX = getX();
        float oldY = getY();

        float newX = oldX + MathUtils.cosDeg(getRotation()) * speed;
        float newY = oldY + MathUtils.sinDeg(getRotation()) * speed;

        if (checkActorCollisions()) {
            speed = 0f;
        }

        setX(newX);
        setY(newY);
    }

    public Boolean checkActorCollisions() {

        if (getX() < 0 || getStage().getHeight() < getX()) {
            return false;
        }
        if (getY() < 0 || getStage().getHeight() < getY()) {
            return false;
        }

        Array<Actor> actors = getStage().getActors();

        for (Actor actor : actors) {
            if (actor instanceof StaticObject) {
                StaticObject x = (StaticObject)actor;
                if (x.collisionBox.overlaps(this.collisionBox)) {
                    return false;
                }
            }
        }

        return true;
    }
}
