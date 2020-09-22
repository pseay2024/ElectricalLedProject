package main;

import java.awt.*;

public class Light {
    private Color c;
    private boolean on;
    public Light(Color c, boolean on) {
        this.c = c;
        this.on = on;
    }

    public Color getColor() {
        return c;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public boolean getOn() {
        return on;
    }
}