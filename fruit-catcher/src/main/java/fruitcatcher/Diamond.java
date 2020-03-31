package fruitcatcher;

import java.util.List;

import fruitcatcher.tiles.FloorTile;
import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import processing.core.PVector;

/**
 * @author Sjaak Kok
 * @author Rowan Paul Flynn
 */
public class Diamond extends SpriteObject implements ICollidableWithTiles, ICollidableWithGameObjects, IAlarmListener {

	private FruitCatcher fruitCatcher;

	private final int HEIGHT = 66;
	private final int WIDTH = 66;

	/**
	 * Constructor 
	 * 
	 * @param fruitCatcher reference to the game
	 */
	public Diamond(FruitCatcher fruitCatcher) {
		super(new Sprite(FruitCatcher.MEDIA_URL.concat("diamond.png")));
		this.fruitCatcher = fruitCatcher;
		setGravity(0.2f);
		setHeight(HEIGHT);
		setWidth(WIDTH);
		startAlarm();
	}

	/**
	 * Does nothing
	 */
	@Override
	public void update() {

	}

	/**
	 * Increases the points by 10
	 */
	private void doAction() {
		fruitCatcher.increasePoints(10);
	}

	/**
	 * DoAction if the diamond collided with the player and remove the diamond
	 */
	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject go : collidedGameObjects) {
			if (go instanceof Player) {
				doAction();
				fruitCatcher.deleteGameObject(this);
			}
		}
	}

	/**
	 * Keeps the diamond on the ground
	 */
	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		PVector vector;
		for (CollidedTile ct : collidedTiles) {
			if (ct.getTile() instanceof FloorTile) {
				if (ct.getCollisionSide() == CollisionSide.TOP) {
					vector = fruitCatcher.getTileMap().getTilePixelLocation(ct.getTile());
					setY(vector.y - getHeight());
				}
			}
		}
	}

	/**
	 * Starts alarm
	 */
	private void startAlarm() {
		Alarm alarm = new Alarm("Diamond", 3);
		alarm.addTarget(this);
		alarm.start();
	}

	/**
	 * Removes object when alarm is triggered
	 */
	@Override
	public void triggerAlarm(String alarmName) {
		fruitCatcher.deleteGameObject(this);
	}
}
