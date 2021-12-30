package com.yorkpirates.game;

import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Gdx;

public class PlayerShip extends Ship{

    public PlayerShip(String imgName, Integer xPos, Integer yPos){
        super(imgName, xPos, yPos);
        setTouchable(Touchable.enabled);
        //addListener(new PlayerListener());
    }

    // Called each frame
    @Override
    public void act(float delta){
        // Placeholder functions for movement
        super.act(delta);
        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            //moveForward();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            //moveBackward();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            //rotateLeft();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            //rotateRight();
        }

    }

    // Did not work very well at all...
    // private class PlayerListener extends InputListener {
    //     @Override
    //     public boolean keyDown(InputEvent event, int keycode){
    //         switch(keycode){
    //             case Input.Keys.RIGHT:
    //                 MoveByAction rightAction = new MoveByAction();
    //                 rightAction.setAmount(10f,0f);
    //                 rightAction.setDuration(0.5f);
    //                 PlayerShip.this.addAction(rightAction);
    //                 break;
    //             case Input.Keys.LEFT:
    //                 MoveByAction leftAction = new MoveByAction();
    //                 leftAction.setAmount(-10f,0f);
    //                 leftAction.setDuration(0.5f);
    //                 PlayerShip.this.addAction(leftAction);
    //                 break;
    //             case Input.Keys.UP:
    //                 MoveByAction upAction = new MoveByAction();
    //                 upAction.setAmount(0f,10f);
    //                 upAction.setDuration(0.5f);
    //                 PlayerShip.this.addAction(upAction);
    //                 break;
    //             case Input.Keys.DOWN:
    //                 MoveByAction downAction = new MoveByAction();
    //                 downAction.setAmount(0f,-10f);
    //                 downAction.setDuration(0.5f);
    //                 PlayerShip.this.addAction(downAction);
    //                 break;
    //             case Input.Keys.BACKSPACE:
    //                 // MTA moves the object to a location over a time
    //                 MoveToAction homeAction = new MoveToAction();
    //                 homeAction.setPosition(200f, 200f);
    //                 homeAction.setDuration(3f);
    //                 PlayerShip.this.addAction(homeAction);
    //         }
    //         return true;
    //     }
    // }

    // InputListener inputListener = new PlayerListener();  
}
