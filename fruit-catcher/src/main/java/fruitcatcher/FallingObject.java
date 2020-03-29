package fruitcatcher;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public abstract class FallingObject extends SpriteObject implements ICollidableWithGameObjects {

	protected FruitCatcher fruitCatcher;

	private int speed;

	public FallingObject(FruitCatcher fruitCatcher, String fruitObject) {
		super(new Sprite(FruitCatcher.MEDIA_URL.concat(fruitObject)));
		this.fruitCatcher = fruitCatcher;
		this.speed = 1;
	}
	
	@Override
	public void update() {
		if (this.y > 0) {
			setDirectionSpeed(180, speed);
		}   
		if (this.y > fruitCatcher.getHeight()) {
			fruitCatcher.deleteGameObject(this);
		}
	}

}
