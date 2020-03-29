package fruitcatcher;

import java.util.Random;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;

public class FallingObjectSpawner implements IAlarmListener {

	private FruitCatcher fruitCatcher;
	private Random random;
	private int objectsPerSecond;

	public FallingObjectSpawner(FruitCatcher fruitCatcher, int objectsPerSecond) {
		// Use `.concat ()` to string 2 strings together.
		// The method returned a new String.
		this.fruitCatcher = fruitCatcher;
		this.random = new Random();
		this.objectsPerSecond = objectsPerSecond;
		startAlarm();
	}

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

	private void startAlarm() {
		Alarm alarm = new Alarm("New Object", 1 / objectsPerSecond);
		alarm.addTarget(this);
		alarm.start();
	}

	@Override
	public void triggerAlarm(String alarmName) {
		Fruit fruit = new Fruit(fruitCatcher, generateFruitObject());
		fruitCatcher.addGameObject(fruit, random.nextInt(fruitCatcher.width), 50);
		startAlarm();
	}

}
