package fruitcatcher;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public abstract class FallingObject extends SpriteObject implements ICollidableWithGameObjects {

	protected FruitCatcher fruitCatcher;

	private static int speed = 1;
	private int size = 96;

	public FallingObject(FruitCatcher fruitCatcher, String object) {
		super(new Sprite(FruitCatcher.MEDIA_URL.concat(object)));
		this.fruitCatcher = fruitCatcher;
	}

	@Override
	public void update() {
		if (this.y > -getSize() - 1) {
			setDirectionSpeed(180, speed);
		}   
		if (this.y > fruitCatcher.getHeight()) {
			fruitCatcher.deleteGameObject(this);
		}
	}
	
	public abstract void doAction();
	
	
	public int getSize() {
		return size;
	}

	public void setSpeed(int speed) {
		FallingObject.speed = speed;
	}

	
}
