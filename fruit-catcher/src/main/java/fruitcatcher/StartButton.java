package fruitcatcher;

/**
 * @author Sjaak Kok
 * @author Rowan Paul Flynn
 */
public class StartButton extends Button {
	
	private FruitCatcher fruitCatcher;

	/**
	 * Constructor
	 * 
	 * @param fruitCatcher reference to the game
	 * @param x The x coordinate of the button
	 * @param y The y coordinate of the button
	 * @param width The width of the button
	 * @param height The height of the button
	 */
	StartButton(FruitCatcher fruitCatcher, int x, int y, int width, int height) {
		super(x, y, width, height, "START");
		this.fruitCatcher = fruitCatcher;
	}

	/**
	 * Does action
	 * in this case: start the game and remove the button
	 */
	@Override
	public void doAction() {
		fruitCatcher.startPlaying();
		fruitCatcher.deleteGameObject(this);
	}
}
