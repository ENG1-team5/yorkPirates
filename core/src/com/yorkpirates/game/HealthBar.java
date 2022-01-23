package com.yorkpirates.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class HealthBar extends Actor{
    Texture healthBarBackground;
    Texture healthBarForeground;
    StaticObject owner;
    String ownerType;
    float buffer = 30f; // Controls how high above the actor the healthbar will be
    Sprite fgSprite;
    Sprite bgSprite;
    float offsetToCenter;

    //Note, setX and setY is controlled outside of this object to make it more reusable

    public HealthBar(StaticObject owner){
        this.owner = owner;
        
        Texture healthBarForeground = new Texture(Gdx.files.internal("healthfg.png"));
        Texture healthBarBackground = new Texture(Gdx.files.internal("healthbg.png"));

        fgSprite = new Sprite(healthBarForeground); // The foreground sprite is the one that shrinks as health gets lower
        bgSprite = new Sprite(healthBarBackground); // Background sprite remains constant to show the original size of the health bar

        
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        float Health = 0; //This is a pretty ugly workaround.
        float MaxHealth = 0;

        // If this healthbar code is to be used with another object, it must be added as an if statement here to access
        if (owner instanceof College){
            Health =  ((College)owner).Health;
            MaxHealth = ((College)owner).maxHealth;
        }
        if (owner instanceof Ship){
            Health =  ((Ship)owner).Health;
            MaxHealth = ((Ship)owner).maxHealth;
        }
        

        batch.setColor(determineColor(Health,MaxHealth)); //Works out the tint that needs to be applyed to the sprites that make up healthBar
        batch.draw(fgSprite, getX(), getY(), getOriginX(), getOriginY(), (fgSprite.getRegionWidth()/(MaxHealth/Health)), fgSprite.getRegionHeight(), getScaleX(), getScaleY(), getRotation());
        batch.draw(bgSprite, getX(), getY(), getOriginX(), getOriginY(), bgSprite.getRegionWidth(), bgSprite.getRegionHeight(), getScaleX(), getScaleY(), getRotation());
        batch.setColor(Color.WHITE); //Resets color to ensure all other draw actions are not tinted
    }

    public Color determineColor(float Health, float MaxHealth){
        Color color = Color.GREEN; //Default colour is green if above 66% health
        if (Health <= (MaxHealth * 0.66)){
            color =  Color.ORANGE; //If below 66% health, set bar to orange
            if (Health <= (MaxHealth * 0.33)){
               color = Color.RED; //If below 33% health, set bar to red
            }  
        }
        return color;
    }
}