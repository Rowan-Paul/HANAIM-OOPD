package fruitcatcher;

import java.util.List;

import nl.han.ica.oopg.objects.GameObject;

/**
 * @author Sjaak Kok
 * @author Rowan Paul Flynn
 */
public class Bomb extends FallingObject {
	
	/**
	 * Constructor 
	 * @param fruitCatcher reference to the game
	 */
	public Bomb(FruitCatcher fruitCatcher) {
		super(fruitCatcher, "bomb.png");
	}
	
	/**
	 * Does action
	 * in this case: end the game
	 */
	@Override
	public void doAction() {
		fruitCatcher.setEndGame(true);
		fruitCatcher.endGame();
	}

	/**
	 * DoAction if the bomb collided with the player and remove the bomb object
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
