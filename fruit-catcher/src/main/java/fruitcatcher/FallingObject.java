package fruitcatcher;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public abstract class FallingObject extends SpriteObject implements ICollidableWithGameObjects {

	protected FruitCatcher fruitCatcher;

	private static int speed;

	public FallingObject(FruitCatcher fruitCatcher, String object) {
		super(new Sprite(FruitCatcher.MEDIA_URL.concat(object)));
		this.fruitCatcher = fruitCatcher;
		FallingObject.speed = 1;
	}

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

	public abstract void doAction();

	public static void setSpeed(int speed) {
		FallingObject.speed = speed;
	}
}
