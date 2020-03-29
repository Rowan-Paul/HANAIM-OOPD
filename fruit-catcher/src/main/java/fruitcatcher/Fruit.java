package fruitcatcher;

import java.util.List;
import java.util.Random;

import nl.han.ica.oopg.objects.GameObject;

public class Fruit extends FallingObject {
	
	private Random random;

	public Fruit(FruitCatcher fruitCatcher, String fruitObject) {
		// Use `.concat ()` to string 2 strings together.
		// The method returned a new String.
		super(fruitCatcher, fruitObject);
		random = new Random();
	}

	// @Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject go : collidedGameObjects) {
			if (go instanceof Player) {
				fruitCatcher.deleteGameObject(this);
			}
		}
	}
}