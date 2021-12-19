package com.yorkpirates.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class YorkPirates extends ApplicationAdapter {


	Stage stage;
	
	@Override
	public void create () {
		// Stage acts as a container for actors, holding the references for them that can be collected
		// with stage.getActors()
		stage = new Stage(new ScreenViewport()); // Creates a stage the size of our screen
		Gdx.input.setInputProcessor(stage); // Wires up the stage as our input processor
		
		StaticObject pShip = new PlayerShip("ship.png",100,100);
		stage.addActor(pShip);
		stage.setKeyboardFocus(pShip);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 1, 1, 1); // Sets background to white
		stage.act(Gdx.graphics.getDeltaTime()); // Runs the act function for all objects in stage, passes in amount of time since last frame
		stage.draw();
	}

	@Override
	public void dispose(){
		
	}
	
}
