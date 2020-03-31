package fruitcatcher;

import java.util.List;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.objects.GameObject;

public class Horse extends FallingObject implements IAlarmListener {

	public Horse(FruitCatcher fruitCatcher) {
		super(fruitCatcher, "horse.png");
	}
	
	@Override
	public void doAction() {
		fruitCatcher.getPlayer().setSpeed(3);
		fruitCatcher.getPlayer().setHorseHit(true);
		startAlarm();
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject go : collidedGameObjects) {
			if (go instanceof Player) {
				doAction();
				fruitCatcher.deleteGameObject(this);
			}
		}
	}
	
	private void startAlarm() {
		Alarm alarm = new Alarm("Horse", 5);
		alarm.addTarget(this);
		alarm.start();
	}

	@Override
	public void triggerAlarm(String alarmName) {
		fruitCatcher.getPlayer().setSpeed(7);
		fruitCatcher.getPlayer().setHorseHit(false);
	}

}