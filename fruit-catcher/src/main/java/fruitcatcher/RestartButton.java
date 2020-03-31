package fruitcatcher;

public class RestartButton extends Button {
	
	private FruitCatcher fruitCatcher;

	RestartButton(FruitCatcher fruitCatcher, int x, int y, int width, int height) {
		super(x, y, width, height, "RESTART");
		this.fruitCatcher = fruitCatcher;
	}

	@Override
	public void doAction() {
		fruitCatcher.restartPlaying();
		fruitCatcher.deleteGameObject(this);
	}
}