package com.yorkpirates.game;

import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Gdx;

public class PlayerShip extends Ship{

    Float maxSpeed = 3f;
    Float acceleration = 1f;
    Float turnSpeed = 1f;

    public PlayerShip(String imgName, Float xPos, Float yPos){
        super(imgName, xPos, yPos);
        //setTouchable(Touchable.enabled);

        //addListener(new PlayerListener());
    }

    // Called each frame
    @Override
    public void act(float delta){
        // Placeholder functions for movement
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
    }
}
