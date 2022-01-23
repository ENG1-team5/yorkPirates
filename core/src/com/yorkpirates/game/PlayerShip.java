package com.yorkpirates.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.Gdx;

public class PlayerShip extends Ship{

    Float maxSpeed = 3f;
    Float acceleration = 1f;
    Float turnSpeed = 2f;

    public PlayerShip(String imgName, Float xPos, Float yPos,String affiliation){
        super(imgName, xPos, yPos, affiliation);

        //Creating a healthbar
        float totalBarWidth = Gdx.graphics.getWidth()/2;
        float width = Health / maxHealth * totalBarWidth;
        
    }

    // Called each frame
    @Override
    public void act(float delta){
        // Movement
        super.act(delta);


        if (Gdx.input.isKeyPressed(Input.Keys.UP) | Gdx.input.isKeyPressed(Input.Keys.W)) {
            speed += delta * acceleration;
            if (speed > maxSpeed) {
                speed = +maxSpeed;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) | Gdx.input.isKeyPressed(Input.Keys.S)) {
            speed -= delta * acceleration;
            if (speed > maxSpeed) {
                speed = -maxSpeed;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) | Gdx.input.isKeyPressed(Input.Keys.A)) {
            rotateBy(+turnSpeed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) | Gdx.input.isKeyPressed(Input.Keys.D)) {
            rotateBy(-turnSpeed);
        }

        // Fire a cannonball action
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) | Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            // The target x and y from Gdx.input is reletive to the screen, these must be converted
            // to be reletive to the stage's coordinate system 
            Vector3 target = getStage().getCamera().unproject(new Vector3(Gdx.input.getX(),Gdx.input.getY(), 0));
            Fire(target.x, target.y);
        }
    }
}
