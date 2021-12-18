package com.team5.pirategame;

abstract class Ship extends DynamicObject {

    Integer Health = 500; //default placeholder value for health

    public Ship(String imgName, Integer xPos, Integer yPos){
        super(imgName, xPos, yPos);
    }

    public void Fire(){

    }

    public void moveForward(){

    }

    public void moveBackward(){
        
    }

}
