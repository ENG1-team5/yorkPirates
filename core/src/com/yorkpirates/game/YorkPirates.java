package com.yorkpirates.game;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class YorkPirates extends ApplicationAdapter {

	Stage stage;
	TiledMap map;
	Viewport viewport;
	OrthographicCamera camera;
	OrthogonalTiledMapRenderer tiledMapRenderer;
	
	PlayerShip pShip;
	ArrayList<College> colleges;  
	
	//Ui variables
	Vector2 originalScreenSize;  //Used to calculate positions for some ui elements
	SpriteBatch uiBatch;
	BitmapFont font;

	HealthBar pHealthBar;

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
		viewport = new FitViewport(mapWidth, mapHeight, camera);
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// Stage acts as a container for actors, holding the references for them that can be collected with stage.getActors()
		stage = new Stage(viewport); // Creates a stage the size of our screen
		Gdx.input.setInputProcessor(stage); // Wires up the stage as our input processor

		// Changing cursor image
		Pixmap pm = new Pixmap(Gdx.files.internal("reticle.png"));
		Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm,pm.getWidth()/2,pm.getHeight()/2)); 
		pm.dispose();

		// Spawn some actors
		MapLayer spawns = map.getLayers().get("spawns");

		// Add player ship
		// Seperate as a player ship must be spawned for the camera to work
		RectangleMapObject spawn = (RectangleMapObject)spawns.getObjects().get("player_spawn");
		pShip = new PlayerShip("ship.png", spawn.getRectangle().x, spawn.getRectangle().y, "Goodricke");
		stage.addActor(pShip);
		stage.setKeyboardFocus(pShip);
		
		// Initialise colleges list
		colleges = new ArrayList<College>();

		// Spawn other 
		for (MapObject sp : spawns.getObjects()) {
			// System.out.println(sp.getName());
			if (sp.getName().contains("enemy_spawn")) {
				Rectangle _sp = ((RectangleMapObject)sp).getRectangle();
				EnemyShip eShip = new EnemyShip("ship.png", _sp.x, _sp.y, sp.getProperties().get("affiliation", String.class));
				stage.addActor(eShip);
			}

			if (sp.getName().contains("college_spawn")) {
				Rectangle _sp = ((RectangleMapObject)sp).getRectangle();
				College college = new College("college.png", _sp.x, _sp.y, sp.getProperties().get("affiliation", String.class));
				colleges.add(college);
				stage.addActor(college);
			}
		}

		//Ui code
		uiBatch = new SpriteBatch();
		font = new BitmapFont();
		originalScreenSize = new Vector2(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		pHealthBar = new HealthBar(pShip);
		pHealthBar.scaleBy(1.5f);
		pHealthBar.setX(15);
		pHealthBar.setY(Gdx.graphics.getHeight()-35);

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

		//ui Rendering
		uiBatch.begin();
		font.setColor(1.0f,1.0f,1.0f,1.0f);
		font.draw(uiBatch, "Objective: Destroy enemy colleges", originalScreenSize.x - 250, originalScreenSize.y - 20);
		if (colleges.size() == 0){
			font.draw(uiBatch, "No enemy colleges remaining!", originalScreenSize.x - 250, originalScreenSize.y -40);
		}
		else{
			for (int i = 0; i < colleges.size(); i++){
				College college = colleges.get(i);
				if (college.affiliation == pShip.affiliation){colleges.remove(i);} // If college is freindly, remove it from the list
				// Places a health percentage for each college in a vertical list
				font.draw(uiBatch, college.affiliation + " : health = " + (int)((college.Health/college.maxHealth)*100) + "%", originalScreenSize.x - 200, originalScreenSize.y -(40 * (i+1))); //Scales vetically with i
			}
		}
		font.draw(uiBatch,"Plunder = " + pShip.plunder,25, 25); //Plunder counter in roughly the center of screen
		pHealthBar.draw(uiBatch, 0); // Draws the player health bar
		uiBatch.end();
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
