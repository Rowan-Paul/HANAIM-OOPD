package fruitcatcher;

import java.util.List;
import java.util.Random;

import nl.han.ica.oopg.objects.GameObject;

public class Fruit extends FallingObject {
	
	private Random random;

	public Fruit(FruitCatcher fruitCatcher) {
		// Use `.concat ()` to string 2 strings together.
		// The method returned a new String.
		super(fruitCatcher);
		random = new Random();
	}
	
//	private String generateFruitObject() {
//		int randomNumber;
//		String fruit;
//		randomNumber = random.nextInt(4);
//		
//		if(randomNumber == 0) {
//			fruit = "apple.png";
//		}
//	}

	// @Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject go : collidedGameObjects) {
			if (go instanceof Player) {
				fruitCatcher.deleteGameObject(this);
			}
		}
	}
}