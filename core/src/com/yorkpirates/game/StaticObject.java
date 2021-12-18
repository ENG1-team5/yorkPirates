package com.yorkpirates.game;

import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;


abstract class StaticObject extends Actor{
    Sprite sprite;
    
    // init function, sets location and texture
    public StaticObject(String imgName, Integer xPos, Integer yPos){
        sprite = new Sprite(new Texture(Gdx.files.internal(imgName)));
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight()); // v important
        setOrigin(getWidth()/2,getHeight()/2); // Changes center of the object from its (0,0) to the actual center
        setX(xPos);
        setY(yPos); 
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        batch.draw(sprite, getX(), getY(), getOriginX(), getOriginY(), sprite.getRegionWidth(), sprite.getRegionHeight(), getScaleX(), getScaleY(), getRotation());
    }

    @Override
    public void act(float delta){
        super.act(delta);
    }
}
