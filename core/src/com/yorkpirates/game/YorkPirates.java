package com.yorkpirates.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class YorkPirates extends ApplicationAdapter {

	Stage stage;
	TiledMap map;
	OrthographicCamera orthoCam;
	OrthogonalTiledMapRenderer tiledMapRenderer;

	// The player ship
	// Unsure if this is a good idea but need to keep it around
	// So we can set the orthoCam position to its position
	StaticObject pShip;

	// Create is run when the game is launched
	@Override
	public void create () {
		// Create and set up orthographic camera
		orthoCam = new OrthographicCamera();
		orthoCam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		Viewport viewport = new FitViewport(orthoCam.viewportWidth, orthoCam.viewportHeight, orthoCam);

		// Stage acts as a container for actors, holding the references for them that can be collected with stage.getActors()
		// stage = new Stage(new ScreenViewport()); // Creates a stage the size of our screen
		stage = new Stage(viewport); // Creates a stage the size of our screen
		Gdx.input.setInputProcessor(stage); // Wires up the stage as our input processor

		// Load tiled map
		map = new TmxMapLoader().load("placeholder.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(map);

		// System.out.println(stage.getHeight() + "" + stage.getWidth());

		// Changing cursor image
		Pixmap pm = new Pixmap(Gdx.files.internal("reticle.png"));
		Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, pm.getWidth()/2, pm.getHeight()/2)); //0,0 is the tip of the cursor on the image
		pm.dispose();

		pShip = new PlayerShip("ship.png", 100f, 100f);
		stage.addActor(pShip);
		stage.setKeyboardFocus(pShip);

		// For testing collision - please remove
		EnemyShip eShip = new EnemyShip("ship.png", 300f, 300f);
		stage.addActor(eShip);
	}

	// Render is ran every frame of the game
	@Override
	public void render () {
		orthoCam.position.set(pShip.getX(), pShip.getY(), 16f);
		orthoCam.update();

		ScreenUtils.clear(0.5f, 0.5f, 0.5f, 1); // Sets background to white

		tiledMapRenderer.setView(orthoCam);
		tiledMapRenderer.render();

		stage.act(Gdx.graphics.getDeltaTime()); // Runs the act function for all objects in stage, passes in amount of time since last frame
		stage.draw();

	}

	@Override
	public void dispose(){
		
	}
	
}
