package fruitcatcher;

import java.util.List;

import nl.han.ica.oopg.objects.GameObject;

/**
 * @author Sjaak Kok
 * @author Rowan Paul Flynn
 */
public class Bomb extends FallingObject {
	
	/**
	 * Adds bomb image
	 * @param fruitCatcher Refrence to package
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
	 * When a player collides with object
	 * doAction()
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
