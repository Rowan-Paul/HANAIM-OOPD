package fruitcatcher;

import java.util.List;

import nl.han.ica.oopg.objects.GameObject;

public class Train extends FallingObject {

	public Train(FruitCatcher fruitCatcher) {
		super(fruitCatcher, "train.png");
	}
	
	@Override
	public void doAction() {
		setSpeed(4);
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