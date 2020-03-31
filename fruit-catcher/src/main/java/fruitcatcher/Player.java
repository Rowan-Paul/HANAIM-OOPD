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
	private boolean horseHit;

	public Player(FruitCatcher fruitCatcher) {
		super(new Sprite(FruitCatcher.MEDIA_URL.concat("player.png")), 3);
		this.fruitCatcher = fruitCatcher;
		horseHit = false;
		speed = 7;
		setFriction(0.05f);
		setGravity(0.2f);
		setCurrentFrameIndex(1);
	}

	@Override
	public void update() {
		if (getX() <= 0) {
			setxSpeed(0);
			setX(0);
		}

		if (getX() >= fruitCatcher.getWidth() - width) {
			setxSpeed(0);
			setX(fruitCatcher.getWidth() - width);
		}

		if (getSpeed() < 4.5 && horseHit == false) {
			setCurrentFrameIndex(1);
		}
	}

	@Override
	public void keyPressed(int keyCode, char key) {
		if (keyCode == LEFT) {
			setDirectionSpeed(270, speed);
			setCurrentFrameIndex(0);
		}
		if (keyCode == RIGHT) {
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
					setY(vector.y - height);
				}
			}
		}
	}
	
	public boolean isPlayerOnTheLeft() {
		boolean isPlayerOnTheLeft = false;
		if(getX() < fruitCatcher.getWidth() / 2) {
			isPlayerOnTheLeft = true;
		} else if(getX() > fruitCatcher.getWidth() / 2) {
			isPlayerOnTheLeft = false;
		}
		return isPlayerOnTheLeft;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setHorseHit(boolean horseHit) {
		this.horseHit = horseHit;
	}

}