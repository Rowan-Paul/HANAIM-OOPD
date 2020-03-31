package fruitcatcher;

import fruitcatcher.tiles.FloorTile;
import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.persistence.FilePersistence;
import nl.han.ica.oopg.persistence.IPersistence;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.view.View;
import fruitcatcher.Player;

/**
 * @author Sjaak Kok
 * @author Rowan Paul Flynn
 */
@SuppressWarnings("serial")
public class FruitCatcher extends GameEngine {

	private Player player;
	private FallingObjectSpawner fallingObjectSpawner;
	private DiamondSpawner diamondSpawner;
	private int points;
	private int droppedFruit;
	private int highscore;
	private TextObject dashboardText;
	private StartButton startButton;
	private RestartButton restartButton;
	private int buttons;
	private IPersistence persistence;
	private boolean endGame;

	private final int WORLD_WIDTH = 800;
	private final int WORLD_HEIGHT = 600;

	public static String MEDIA_URL = "src/main/java/Fruitcatcher/media/";

	public static void main(String[] args) {
		FruitCatcher FruitCatcher = new FruitCatcher();
		FruitCatcher.runSketch();
	}

	/**
	 * Loads necessary functions
	 */
	@Override
	public void setupGame() {
		setEndGame(false);

		startButton = new StartButton(this, WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 200, 150);
		addGameObject(startButton);

		View view = new View(WORLD_WIDTH, WORLD_HEIGHT);
		view.setBackground(loadImage(FruitCatcher.MEDIA_URL.concat("background.png")));
		
		setView(view);
		size(WORLD_WIDTH, WORLD_HEIGHT);
		
		initializeTileMap();
		createDashboard(WORLD_WIDTH, 26);
		initializePersistence();
	}

	/**
	 * Checks if the game should be ended
	 */
	@Override
	public void update() {
		if (droppedFruit == 3) {
			if (buttons == 0) {
				setEndGame(true);
				endGame();
				buttons++;
			}
			if (buttons > 1) {
				buttons = 1;
			}
		}
	}

	/**
	 * Adds tilemap for collisions
	 */
	private void initializeTileMap() {
		Sprite floorSprite = new Sprite(FruitCatcher.MEDIA_URL.concat("grass.png"));
		TileType<FloorTile> floorTileType = new TileType<>(FloorTile.class, floorSprite);
		@SuppressWarnings("rawtypes")
		TileType[] tileTypes = { floorTileType };
		int tileSize = 64;
		int tilesMap[][] = { 
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }, 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, }, 
				};
		tileMap = new TileMap(tileSize, tileTypes, tilesMap);
	}

	/**
	 * Creates dashboard for points, highscore and dropped Fruit
	 * @param dashboardWidth The width of the dashboard
	 * @param dashboardHeight The height of the dashboard
	 */
	private void createDashboard(int dashboardWidth, int dashboardHeight) {
		Dashboard dashboard = new Dashboard(0, 0, dashboardWidth, dashboardHeight);
		dashboardText = new TextObject("                        Points: " + points + "        High score: " + highscore
				+ "        Dropped Fruit: " + droppedFruit);
		dashboard.addGameObject(dashboardText);
		addDashboard(dashboard);
	}

	/**
	 * Updates dashboard
	 */
	private void refreshDashboardText() {
		dashboardText.setText("                        Points: " + points + "        High score: " + highscore
				+ "        Dropped Fruit: " + droppedFruit);
	}
	
	/**
	 * Resets points and dropped Fruit
	 * for when starting a new game
	 */
	private void resetDashboardScores() {
		setPoints(0);
		setDroppedFruit(0);
	}

	/**
	 * Initializes the play area
	 */
	public void startPlaying() {
		player = new Player(this);
		addGameObject(getPlayer(), 200, 500);

		fallingObjectSpawner = new FallingObjectSpawner(this, 1);
		diamondSpawner = new DiamondSpawner(this, 10);
	}

	/**
	 * Restarts playing area after
	 * the game has ended
	 */
	public void restartPlaying() {
		setEndGame(false);

		FallingObject.setSpeed(1);

		player = new Player(this);
		addGameObject(getPlayer(), 200, 500);

		fallingObjectSpawner.setStopAlarm(false);
		fallingObjectSpawner.startAlarm();

		diamondSpawner.setStopAlarm(false);
		diamondSpawner.startAlarm();

		resetDashboardScores();

		this.highscore = Integer.parseInt(persistence.loadDataString());
		refreshDashboardText();
		buttons = 0;
	}

	/**
	 * Ends the game, removes game objects
	 * and sets high score
	 */
	public void endGame() {
		deleteGameObject(player);
		FallingObject.setSpeed(5);
		fallingObjectSpawner.setStopAlarm(true);
		diamondSpawner.setStopAlarm(true);
		restartButton = new RestartButton(this, WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 200, 150);
		addGameObject(restartButton);
		
		if (this.points > this.highscore) {
			persistence.saveData(Integer.toString(points));
			refreshDashboardText();
		}
	}

	/**
	 * 
	 * @param amount Amount of points that needs to be added to score
	 */
	public void increasePoints(int amount) {
		this.points += amount;
		refreshDashboardText();
	}

	/**
	 * Increases the speed of the Fruit dropping
	 * and refreshes the dashboard
	 */
	public void increaseFruitDropped() {
		if (!endGame) {
			this.droppedFruit++;
			refreshDashboardText();
		}
	}

	/**
	 * Initialise persistence for highscore file
	 */
	private void initializePersistence() {
		persistence = new FilePersistence("main/java/Fruitcatcher/media/highscore.txt");
		if (persistence.fileExists()) {
			this.highscore = Integer.parseInt(persistence.loadDataString());
			refreshDashboardText();
		}
	}

	/**
	 * Getter for Player
	 * @return returns player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Setter for points
	 * @param points Amount of points that needs to be set
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * Sets amount of dropped Fruit
	 * @param droppedFruit Amount of dropped fruit
	 */
	public void setDroppedFruit(int droppedFruit) {
		this.droppedFruit = droppedFruit;
	}

	/**
	 * Ends the game or restarts it
	 * @param endGame True when ending game
	 */
	public void setEndGame(boolean endGame) {
		this.endGame = endGame;
	}


}