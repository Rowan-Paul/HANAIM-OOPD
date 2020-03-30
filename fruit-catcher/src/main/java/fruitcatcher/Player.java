package fruitcatcher;

import java.util.List;

import fruitcatcher.tiles.FloorTile;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import processing.core.PVector;

public class Player extends SpriteObject implements ICollidableWithTiles {

	private FruitCatcher fruitCatcher;
	private final int size = 96;
	private int speed;

	public Player(FruitCatcher fruitCatcher) {
		super(new Sprite(FruitCatcher.MEDIA_URL.concat("playerStanding.png")));
		this.fruitCatcher = fruitCatcher;
		setGravity(0.2f);
		this.speed = 6;
	}

	@Override
	public void update() {
		if (getX() <= 0) {
			setxSpeed(0);
			setX(0);
		}

		if (getX() >= fruitCatcher.width - size) {
			setxSpeed(0);
			setX(fruitCatcher.width - size);
		}
	}

	@Override
	public void keyPressed(int keyCode, char key) {
		if (keyCode == fruitCatcher.LEFT) {
			setDirectionSpeed(270, speed);
		}
		if (keyCode == fruitCatcher.RIGHT) {
			setDirectionSpeed(90, speed);
		}

	}

	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		PVector vector;
		for (CollidedTile ct : collidedTiles) {
			if (ct.getTile() instanceof FloorTile) {
				if (ct.getCollisionSide() == CollisionSide.TOP) {
					try {
						vector = fruitCatcher.getTileMap().getTilePixelLocation(ct.getTile());
						setY(vector.y - getHeight());
					} catch (TileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}