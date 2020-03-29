package fruitcatcher;

import fruitcatcher.tiles.FloorTile;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.sound.Sound;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.view.View;
import fruitcatcher.Player;

public class FruitCatcher extends GameEngine {
    
	private Player player;
	private Fruit fruit;
	private FallingObjectSpawner spawner;
	
	// This line makes it easier to reference your images.
    public static String MEDIA_URL = "src/main/java/fruitcatcher/media/";
    
    public static void main(String[] args) {
        FruitCatcher fruitCatcher = new FruitCatcher();
        fruitCatcher.runSketch();
    }

    @Override
    public void setupGame() {
        int worldWidth = 800;
        int worldHeight = 600;
        
        // of course it is best to add new game objects
        // in a separate method instead of making the update so large.
        player = new Player(this);
        addGameObject(player, 200, 200);
        
        fruit = new Fruit(this, "apple.png");
        addGameObject(fruit, 500, 100);
        
        spawner = new FallingObjectSpawner(this, 1);
        
        View view = new View(worldWidth, worldHeight);

        setView(view);
        size(worldWidth, worldHeight);
        view.setBackground(loadImage(FruitCatcher.MEDIA_URL.concat("background.png")));
        initializeTileMap();
        //initializeSound();
    }

    @Override
    public void update() {
        // Dit doet nog helemaal niks
        
    }
    
    private void initializeTileMap() {
        // Load Sprites
        Sprite floorSprite = new Sprite(FruitCatcher.MEDIA_URL.concat("grass.png"));
        // Create tile types with the right Tile class and sprite
        TileType<FloorTile> floorTileType = new TileType<>(FloorTile.class, floorSprite);
        @SuppressWarnings("rawtypes")
        TileType[] tileTypes = {floorTileType};
        int tileSize = 64;
        int tilesMap[][] = {
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},          
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
        };
        tileMap = new TileMap(tileSize, tileTypes, tilesMap);
    }
    
	/*
	 * private void initializeSound() { backgroundMusic = new Sound(this,
	 * MEDIA_URL.concat("jump_08.mp3")); backgroundMusic.loop(-1); }
	 */
    

}