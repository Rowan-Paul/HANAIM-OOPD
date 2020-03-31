package fruitcatcher;

import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.userinput.IMouseInput;
import processing.core.PGraphics;

/**
 * @author Sjaak Kok
 * @author Rowan Paul Flynn
 */
public abstract class Button extends GameObject implements IMouseInput {
	
	private String text;
	
	Button(int x, int y, int width, int height, String text) {
		super(x, y, width, height);
		this.text = text;
	}

	/**
	 * Does nothing
	 */
	@Override
	public void update() {
		
	}
	
	/**
	 * Checks if mouse is clicked inside of the button
	 */
	public void mouseClicked(int x, int y, int button) {
		if (this.x - this.width / 2 < x && this.y - this.width / 2 < y && this.x + this.width / 2 > x && this.y + this.height / 2 > y)
		doAction();
	}
	
	/**
	 * Does action
	 */
	public abstract void doAction();

	/**
	 * Draws button
	 */
	@Override
	public void draw(PGraphics g) {
		g.fill(9, 130, 55);
		g.rectMode(CENTER);
		g.rect(this.x, this.y, this.width, this.height);
		g.fill(0);
		g.textSize(30);
		g.textAlign(CENTER, CENTER);
		g.text(this.text, this.x , this.y);
	}

}
