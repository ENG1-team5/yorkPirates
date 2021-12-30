package com.yorkpirates.game;

abstract class Ship extends DynamicObject {

    Integer Health = 500; //default placeholder value for health

    public Ship(String imgName, Float xPos, Float yPos){
        super(imgName, xPos, yPos);
    }

    public void Fire(){

    }

    public void moveForward(){
        
    }

    public void moveBackward(){
        
    }

}
