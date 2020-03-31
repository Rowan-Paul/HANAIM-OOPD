package fruitcatcher;

import java.util.Random;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;

public class DiamondSpawner implements IAlarmListener {

	private FruitCatcher fruitCatcher;
	private Random random;
	private double newDiamondWait;
	private boolean stopAlarm = false;

	public DiamondSpawner(FruitCatcher fruitCatcher, double newDiamondWait) {
		this.fruitCatcher = fruitCatcher;
		this.random = new Random();
		this.newDiamondWait = newDiamondWait;
		startAlarm();
	}

	public void startAlarm() {
		if (!stopAlarm) {
			Alarm alarm = new Alarm("New Diamond", newDiamondWait);
			alarm.addTarget(this);
			alarm.start();
		}
	}

	@Override
	public void triggerAlarm(String alarmName) {
		Diamond diamond = new Diamond(fruitCatcher);
		if (fruitCatcher.getPlayer().isPlayerOnTheLeft()) {
			fruitCatcher.addGameObject(diamond, fruitCatcher.getWidth() / 2 + random.nextInt(fruitCatcher.width / 2 - (int) diamond.getWidth()), 500);
		} else if (!fruitCatcher.getPlayer().isPlayerOnTheLeft()) {
			fruitCatcher.addGameObject(diamond, random.nextInt(fruitCatcher.getWidth() / 2 - (int) diamond.getWidth()), 500);
		}
		startAlarm();
	}

	public void setStopAlarm(boolean stopAlarm) {
		this.stopAlarm = stopAlarm;
	}

}
