package com.yorkpirates.game;


abstract class Ship extends DynamicObject {

    Integer Health = 500; //default placeholder value for health

    public Ship(String imgName, Float xPos, Float yPos){
        super(imgName, xPos, yPos);
    }

    public void Fire(Float xCoord, Float yCoord){
        CannonBall cb = new CannonBall("cannonball.png", getX()+getWidth()/2, getY()+getHeight()/2, xCoord, yCoord);
        getStage().addActor(cb);    
    }

    public void moveForward(){
        
    }

    public void moveBackward(){
        
    }

}
