package fruitcatcher;

import java.util.List;

import fruitcatcher.tiles.FloorTile;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PVector;

public class Player extends AnimatedSpriteObject implements ICollidableWithTiles {

	private FruitCatcher fruitCatcher;
	private int speed;
	private boolean horseHit = false;

	public Player(FruitCatcher fruitCatcher) {
		super(new Sprite(FruitCatcher.MEDIA_URL.concat("player.png")), 3);
		this.fruitCatcher = fruitCatcher;
		setCurrentFrameIndex(1);
		setGravity(0.2f);
		speed = 7;
		setFriction(0.05f);
	}

	@Override
	public void update() {
		if (getX() <= 0) {
			setxSpeed(0);
			setX(0);
		}

		if (getX() >= fruitCatcher.width - getWidth()) {
			setxSpeed(0);
			setX(fruitCatcher.width - getWidth());
		}

		if (getSpeed() < 4.5 && horseHit == false) {
			setCurrentFrameIndex(1);
		}
	}

	@Override
	public void keyPressed(int keyCode, char key) {
		if (keyCode == fruitCatcher.LEFT) {
			setDirectionSpeed(270, speed);
			setCurrentFrameIndex(0);
		}
		if (keyCode == fruitCatcher.RIGHT) {
			setDirectionSpeed(90, speed);
			setCurrentFrameIndex(2);
		}
	}

	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		PVector vector;
		for (CollidedTile ct : collidedTiles) {
			if (ct.getTile() instanceof FloorTile) {
				if (ct.getCollisionSide() == CollisionSide.TOP) {
					vector = fruitCatcher.getTileMap().getTilePixelLocation(ct.getTile());
					setY(vector.y - getHeight());
				}
			}
		}
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setTrainHit(boolean horseHit) {
		this.horseHit = horseHit;
	}

}