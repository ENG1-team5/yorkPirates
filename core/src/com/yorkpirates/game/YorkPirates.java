package com.yorkpirates.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class YorkPirates extends ApplicationAdapter {

	Stage stage;
	TiledMap map;
	OrthographicCamera camera;
	OrthogonalTiledMapRenderer tiledMapRenderer;

	// The player ship
	// Unsure if this is a good idea but need to keep it around
	// So we can set the orthoCam position to its position
	StaticObject pShip;

	// Create is run when the game is launched
	@Override
	public void create () {
		// Load tiled map
		map = new TmxMapLoader().load("placeholder.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(map);

		// Get width and height
		// width and height are in units (== tiles), tilewidth and tileheight are pixels per unit (== pixels per tile)
		MapProperties prop = map.getProperties();
		Integer mapWidth = prop.get("width", Integer.class) * prop.get("tilewidth", Integer.class);
		Integer mapHeight = prop.get("height", Integer.class) * prop.get("tileheight", Integer.class);

		// Create and set up orthographic camera
		camera = new OrthographicCamera();
		Viewport viewport = new FitViewport(mapWidth, mapHeight, camera);
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// Stage acts as a container for actors, holding the references for them that can be collected with stage.getActors()
		stage = new Stage(viewport); // Creates a stage the size of our screen
		Gdx.input.setInputProcessor(stage); // Wires up the stage as our input processor

		// System.out.println(stage.getHeight() + " " + stage.getWidth());

		// Changing cursor image
		Pixmap pm = new Pixmap(Gdx.files.internal("reticle.png"));
		Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, pm.getWidth()/2, pm.getHeight()/2)); //0,0 is the tip of the cursor on the image
		pm.dispose();

		// Spawn some actors
		MapLayer spawns = map.getLayers().get("spawns");

		// Add player ship
		// Seperate as a player ship must be spawned for the camera to work
		RectangleMapObject spawn = (RectangleMapObject)spawns.getObjects().get("player_spawn");
		pShip = new PlayerShip("ship.png", spawn.getRectangle().x, spawn.getRectangle().y, "Goodricke");
		stage.addActor(pShip);
		stage.setKeyboardFocus(pShip);

		// Spawn other 
		for (MapObject sp : spawns.getObjects()) {
			// System.out.println(sp.getName());
			if (sp.getName().contains("enemy_spawn")) {
				Rectangle _sp = ((RectangleMapObject)sp).getRectangle();
				EnemyShip eShip = new EnemyShip("ship.png", _sp.x, _sp.y, sp.getProperties().get("allegance", String.class));
				stage.addActor(eShip);
			}

			if (sp.getName().contains("college_spawn")) {
				Rectangle _sp = ((RectangleMapObject)sp).getRectangle();
				EnemyShip eShip = new EnemyShip("ship.png", _sp.x, _sp.y, sp.getProperties().get("allegance", String.class));
				stage.addActor(eShip);
			}
		}
	}

	// Render is ran every frame of the game
	@Override
	public void render () {
		camera.position.set(pShip.getX(), pShip.getY(), 16f);
		camera.update();

		ScreenUtils.clear(0.5f, 0.5f, 0.5f, 1); // Sets background to white

		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();

		stage.act(Gdx.graphics.getDeltaTime()); // Runs the act function for all objects in stage, passes in amount of time since last frame
		stage.draw();
	}

	@Override
	public void dispose(){
		
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width;
		camera.viewportHeight = height;
	}
}
