package com.yorkpirates.game;

import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;


abstract class StaticObject extends Actor{
    Sprite sprite;
    Rectangle collisionBox;


    // init function, sets location and texture
    public StaticObject(String imgName, Float xPos, Float yPos){
        sprite = new Sprite(new Texture(Gdx.files.internal(imgName)));
        setBounds(sprite.getWidth(), sprite.getHeight(), sprite.getWidth(), sprite.getHeight()); // v important
        setOrigin(getWidth()/2,getHeight()/2); // Changes center of the object from its (0,0) to the actual center, for rotation
        setX(xPos);
        setY(yPos);

        collisionBox = new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());//Collision box, can be overwritten for other shapes in object init
    }

    // Redraws the object every frame, I think.
    @Override
    public void draw(Batch batch, float parentAlpha){
        batch.draw(sprite, getX(), getY(), getOriginX(), getOriginY(), sprite.getRegionWidth(), sprite.getRegionHeight(), getScaleX(), getScaleY(), getRotation());
    }

    // Called each frame
    @Override
    public void act(float delta){
        super.act(delta);
        collisionBox.setPosition(this.getX(),this.getY());
    }
}
