package fruitcatcher;

import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class TextObject extends GameObject {

    private String text;

    public TextObject(String text) {
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics g) {
    	g.background(9, 130, 55);
        g.textAlign(LEFT, TOP);
        g.textSize(20);
        g.text(text, x, y);
    }
}