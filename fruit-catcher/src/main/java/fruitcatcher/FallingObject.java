package fruitcatcher;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

/**
 * @author Sjaak Kok
 * @author Rowan Paul Flynn
 *
 */
public abstract class FallingObject extends SpriteObject implements ICollidableWithGameObjects {

	protected FruitCatcher fruitCatcher;

	private static int speed;

	/**
	 * Adds Constructor
	 * 
	 * @param fruitCatcher reference to the game
	 * @param object
	 */
	public FallingObject(FruitCatcher fruitCatcher, String object) {
		super(new Sprite(FruitCatcher.MEDIA_URL.concat(object)));
		this.fruitCatcher = fruitCatcher;
		FallingObject.speed = 1;
	}

	/**
	 * Makes objects fall down with speed
	 * and removes them if they've fallen down
	 * as well as adding to the dropped fruit
	 */
	@Override
	public void update() {
		if (this.y > -this.height - 1) {
			setDirectionSpeed(180, speed);
		}
		if (this.y > fruitCatcher.getHeight()) {
			fruitCatcher.deleteGameObject(this);
		}
		if (this.y > fruitCatcher.getHeight() && this instanceof Fruit) {
			fruitCatcher.increaseFruitDropped();
		}
	}

	/**
	 * Does action
	 */
	public abstract void doAction();

	/**
	 * Sets speed at which objects fall
	 * @param speed Speed at which objects fall
	 */
	public static void setSpeed(int speed) {
		FallingObject.speed = speed;
	}
}
