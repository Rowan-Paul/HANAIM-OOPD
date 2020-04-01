package fruitcatcher;

/**
 * @author Sjaak Kok
 * @author Rowan Paul Flynn
 *
 */
public class RestartButton extends Button {
	
	private FruitCatcher fruitCatcher;

	/**
	 * Constructor
	 * 
	 * @param fruitCatcher Reference to the game
	 * @param x The x coordinate of the restart button
	 * @param y The y coordinate of the restart button
	 * @param width The width of the restart button
	 * @param height The height of the restart button
	 */
	RestartButton(FruitCatcher fruitCatcher, int x, int y, int width, int height) {
		super(x, y, width, height, "RESTART");
		this.fruitCatcher = fruitCatcher;
	}

	/**
	 * Does action
	 * in this case: restart the game and remove object the button
	 */
	@Override
	public void doAction() {
		fruitCatcher.restartPlaying();
		fruitCatcher.deleteGameObject(this);
	}
}