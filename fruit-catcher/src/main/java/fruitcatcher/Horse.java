package fruitcatcher;

import java.util.List;

import nl.han.ica.oopg.objects.GameObject;

public class Horse extends FallingObject {

	public Horse(FruitCatcher fruitCatcher) {
		super(fruitCatcher, "horse.png");
	}
	
	@Override
	public void doAction() {
		fruitCatcher.getPlayer().setSpeed(4);
	}

	// @Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject go : collidedGameObjects) {
			if (go instanceof Player) {
				doAction();
				fruitCatcher.deleteGameObject(this);
			}
		}
	}

}