package fruitcatcher;

import java.util.Random;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;

/**
 * @author Sjaak Kok
 * @author Rowan Paul Flynn
 */
public class FallingObjectSpawner implements IAlarmListener {

	private FruitCatcher fruitCatcher;
	private Random random;
	private double newObjectWait;
	private boolean stopAlarm;;

	/**
	 * Constructor
	 * 
	 * @param fruitCatcher reference to the game
	 * @param newObjectWait the amount of you have to wait till a new object will spawn in seconds
	 */
	public FallingObjectSpawner(FruitCatcher fruitCatcher, double newObjectWait) {
		this.fruitCatcher = fruitCatcher;
		this.random = new Random();
		this.newObjectWait = newObjectWait;
		this.stopAlarm = false;
		startAlarm();
	}

	/**
	 * Generates random fruit object
	 * @return Returns image path
	 */
	private String generateFruitObject() {
		int randomNumber;
		randomNumber = random.nextInt(4);

		if (randomNumber == 0) {
			return "apple.png";
		} else if (randomNumber == 1) {
			return "banana.png";
		} else if (randomNumber == 2) {
			return "orange.png";
		} else if (randomNumber == 3) {
			return "watermelon.png";
		}
		return null;
	}

	/**
	 * Generates random falling objects
	 */
	private void generateFallingObject() {
		int randomNumber;
		randomNumber = random.nextInt(10);

		if (randomNumber == 0 || randomNumber == 1 || randomNumber == 3 || randomNumber == 5 || randomNumber == 6
				|| randomNumber == 8) {
			Fruit fruit = new Fruit(fruitCatcher, generateFruitObject());
			fruitCatcher.addGameObject(fruit, random.nextInt(fruitCatcher.getWidth() - (int) fruit.getWidth()),
					-fruit.getHeight());
		} else if (randomNumber == 4) {
			Horse horse = new Horse(fruitCatcher);
			fruitCatcher.addGameObject(horse, random.nextInt(fruitCatcher.getWidth() - (int) horse.getWidth()),
					-horse.getHeight());
		} else if (randomNumber == 7) {
			Train train = new Train(fruitCatcher);
			fruitCatcher.addGameObject(train, random.nextInt(fruitCatcher.getWidth() - (int) train.getWidth()),
					-train.getHeight());
		} else if (randomNumber == 9) {
			Bomb bomb = new Bomb(fruitCatcher);
			fruitCatcher.addGameObject(bomb, random.nextInt(fruitCatcher.getWidth() - (int) bomb.getWidth()),
					-bomb.getHeight());
		}
	}

	/**
	 * Starts alarm
	 */
	public void startAlarm() {
		if (!stopAlarm) {
			Alarm alarm = new Alarm("New Object", newObjectWait);
			alarm.addTarget(this);
			alarm.start();
		}
	}
	
	
	@Override
	public void triggerAlarm(String alarmName) {
		generateFallingObject();
		startAlarm();
	}

	/**
	 * Sets alarm on or off
	 * @param stopAlarm if alarm should be on stopAlarm is false
	 */
	public void setStopAlarm(boolean stopAlarm) {
		this.stopAlarm = stopAlarm;
	}

}
