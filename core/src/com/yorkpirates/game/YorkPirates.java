package com.yorkpirates.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class YorkPirates extends ApplicationAdapter {

	Stage stage;

	// Create is run when the game is launched
	@Override
	public void create () {
		// Stage acts as a container for actors, holding the references for them that can be collected with stage.getActors()
		stage = new Stage(new ScreenViewport()); // Creates a stage the size of our screen
		Gdx.input.setInputProcessor(stage); // Wires up the stage as our input processor
		
		//Changing cursor image
		Pixmap pm = new Pixmap(Gdx.files.internal("reticle.png"));
		Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, pm.getWidth()/2, pm.getHeight()/2)); //0,0 is the tip of the cursor on the image
		pm.dispose();

		StaticObject pShip = new PlayerShip("ship.png", 100f, 100f);
		stage.addActor(pShip);
		stage.setKeyboardFocus(pShip);

		// For testing collision - please remove
		EnemyShip eShip = new EnemyShip("ship.png", 300f, 300f);
		stage.addActor(eShip);
	}

	// Render is ran every frame of the game
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
