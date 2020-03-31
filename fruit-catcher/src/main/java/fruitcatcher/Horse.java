package fruitcatcher;

import java.util.List;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.objects.GameObject;

/**
 * @author Sjaak Kok
 * @author Rowan Paul Flynn
 */
public class Horse extends FallingObject implements IAlarmListener {

	/**
	 * Adds horse image
	 * @param fruitCatcher
	 */
	public Horse(FruitCatcher fruitCatcher) {
		super(fruitCatcher, "horse.png");
	}
	
	/**
	 * Does action
	 * in this case: decrease speed
	 */
	@Override
	public void doAction() {
		fruitCatcher.getPlayer().setSpeed(3);
		fruitCatcher.getPlayer().setHorseHit(true);
		startAlarm();
	}

	/**
	 * Checks if object collides with player,
	 * does action and deletes object
	 */
	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject go : collidedGameObjects) {
			if (go instanceof Player) {
				doAction();
				fruitCatcher.deleteGameObject(this);
			}
		}
	}
	
	/**
	 * Starts alarm
	 */
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