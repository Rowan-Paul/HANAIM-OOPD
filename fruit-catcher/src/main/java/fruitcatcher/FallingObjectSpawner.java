package fruitcatcher;

import java.util.Random;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;

public class FallingObjectSpawner implements IAlarmListener {

	private FruitCatcher fruitCatcher;
	private Random random;
	private double objectsPerSecond;

	public FallingObjectSpawner(FruitCatcher fruitCatcher) {
		this.fruitCatcher = fruitCatcher;
		this.random = new Random();
		this.objectsPerSecond = 1;
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

	private void generateFallingObject() {
		int randomNumber;
		randomNumber = random.nextInt(10);

		if (randomNumber == 0 || randomNumber == 1 || randomNumber == 3 || randomNumber == 5 
				|| randomNumber == 6 || randomNumber == 8) {
			Fruit fruit = new Fruit(fruitCatcher, generateFruitObject());
			fruitCatcher.addGameObject(fruit, random.nextInt(fruitCatcher.width - fruit.getSize()), -fruit.getSize());
		} else if (randomNumber == 4) {
			Horse horse = new Horse(fruitCatcher);
			fruitCatcher.addGameObject(horse, random.nextInt(fruitCatcher.width - horse.getSize()), -horse.getSize());
		} else if (randomNumber == 7) {
			Train train = new Train(fruitCatcher);
			fruitCatcher.addGameObject(train, random.nextInt(fruitCatcher.width - train.getSize()), -train.getSize());
		} else if (randomNumber == 9) {
			Bomb bomb = new Bomb(fruitCatcher);
			fruitCatcher.addGameObject(bomb, random.nextInt(fruitCatcher.width - bomb.getSize()), -bomb.getSize());
		}

	}

	private void startAlarm() {
		Alarm alarm = new Alarm("New Object", objectsPerSecond);
		alarm.addTarget(this);
		alarm.start();
	}

	@Override
	public void triggerAlarm(String alarmName) {
		generateFallingObject();
		startAlarm();
	}

}
