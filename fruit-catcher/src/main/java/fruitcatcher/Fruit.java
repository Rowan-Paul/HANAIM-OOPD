package fruitcatcher;

import java.util.List;

import nl.han.ica.oopg.objects.GameObject;

/**
 * @author Sjaak Kok
 * @author Rowan Paul Flynn
 */
public class Fruit extends FallingObject {
	
	/**
	 * Creates object
	 * @param fruitCatcher
	 * @param fruitObject Specific object
	 */
	public Fruit(FruitCatcher fruitCatcher, String fruitObject) {
		super(fruitCatcher, fruitObject);
	}
	
	/**
	 * Does action
	 * in this case: increase points
	 */
	@Override
	public void doAction() {
		fruitCatcher.increasePoints(1);
	}

	/**
	 * When a player collides with object
	 * do action and delete object
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

}