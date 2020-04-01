package fruitcatcher;

import java.util.List;

import nl.han.ica.oopg.objects.GameObject;

/**
 * @author Sjaak Kok
 * @author Rowan Paul Flynn
 */
public class Fruit extends FallingObject {
	
	/**
	 * Constructor
	 * 
	 * @param fruitCatcher Rreference to the game
	 * @param fruitObject Fruit object reference
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
	 * DoAction if the fruit collided with the player and remove the fruit object
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