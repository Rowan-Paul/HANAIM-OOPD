package fruitcatcher;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

/**
 * @author Sjaak Kok
 * @author Rowan Paul Flynn
 */
public class TextObject extends GameObject {

    private String text;

    /**
     * Constructor
     * 
     * @param text The text that should be displayed
     */
    public TextObject(String text) {
        this.text = text;
    }

    /**
     * Sets text
     * @param text The text that should be set
     */
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void update() {

    }

    /**
     * Draws text
     */
    @Override
    public void draw(PGraphics g) {
    	g.background(9, 130, 55);
        g.textAlign(LEFT, TOP);
        g.textSize(20);
        g.text(text, x, y);
    }
}