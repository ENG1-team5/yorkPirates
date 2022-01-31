package com.yorkpirates.game;

import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Actor;

abstract class StaticObject extends Actor{
    Sprite sprite;
    public Polygon collisionBox;

    // init function, sets location and texture
    public StaticObject(String imgName, Float xPos, Float yPos){
        sprite = new Sprite(new Texture(Gdx.files.internal(imgName)));
        setBounds(sprite.getWidth(), sprite.getHeight(), sprite.getWidth(), sprite.getHeight()); // v important
        setX(xPos);
        setY(yPos);

        collisionBox = new Polygon(new float[]{0, 0, sprite.getWidth(), 0, sprite.getWidth(), sprite.getHeight(), 0, sprite.getHeight()});
        collisionBox.setPosition(sprite.getX(), sprite.getY());

        // Changes center of the object from its (0,0) to the actual center, for rotation
        setOrigin(getWidth()/2,getHeight()/2);
        collisionBox.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
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
        collisionBox.setRotation(this.getRotation());
    }
}
