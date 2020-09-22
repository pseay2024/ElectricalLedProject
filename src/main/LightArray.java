package main;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.function.BiPredicate;

public class LightArray {

    Light[] lights;
    public final int length;

    public LightArray(int length) {
        this.length = length;
        lights = new Light[length];
        for (int i = 0; i < length; i++) {
            Color lightColor = Color.white;
            switch(i/(length/4)) {
                case 0:
                    lightColor = Color.red;
                    break;
                case 1:
                    lightColor = Color.green;
                    break;
                case 2:
                    lightColor = Color.blue;
            };
            lights[i] = new Light(lightColor, false); //gives 4 different colors to the lights
        }
    }


    /**
     * gets the state of the light
     */
    public boolean getLight(int i) {
        return lights[i].getOn(); //gets the state of the light
    }

    /**
     * sets the state of the light
     */
    public void setLight(int i, boolean on) {
        lights[i].setOn(on); //sets a light's state
    }

    /**
     * uses a BiPredicate with the Light and Index of the light to
     */
    public void loopSet(BiPredicate<Light, Integer> onTester) {
        for (int i = 0; i < lights.length; i++) {
            lights[i].setOn(onTester.test(lights[i], i));
        }
    }

    public void setToClone(LightArray arr) {
        if (arr.length != length) return;
        for (int i = 0; i < length; i++) {
            Color lightColor = Color.white;
            switch(i/(length/4)) {
                case 0:
                    lightColor = Color.red;
                    break;
                case 1:
                    lightColor = Color.green;
                    break;
                case 2:
                    lightColor = Color.blue;
            };
            lights[i] = new Light(lightColor, arr.getLight(i)); //gives 4 different colors to the lights
        }
    }

    /**
     * repaints the Lights
     */
    public void paint(Graphics g) {
        Light[] topLights =    Arrays.stream(lights).filter(l -> l.getColor() == Color.red).toArray(Light[]::new);
        Light[] rightLights =  Arrays.stream(lights).filter(l -> l.getColor() == Color.green).toArray(Light[]::new);
        Light[] bottomLights = Arrays.stream(lights).filter(l -> l.getColor() == Color.blue).toArray(Light[]::new);
        Light[] leftLights =   Arrays.stream(lights).filter(l -> l.getColor() == Color.white).toArray(Light[]::new);

        int maxSideLength = (int)Math.ceil(lights.length/4.0);

        int clipWidth = (int)g.getClip().getBounds().getWidth();
        int clipHeight = (int)g.getClip().getBounds().getHeight();

        int smallerDimension = clipWidth > clipHeight ? clipHeight : clipWidth;

        int lightSize = (smallerDimension-(maxSideLength*5))/(maxSideLength+2);

        //background
        g.setColor(Color.black);
        g.fillRect(0,0,clipWidth,clipHeight);

        //bottom and top
        int hspace = (clipWidth-((maxSideLength+2)*lightSize))/maxSideLength;
        int offset = lightSize+hspace/2;
        for (int i = 0; i < topLights.length; i++) {
            if (topLights[i].getOn() == true) g.setColor(topLights[i].getColor());
            else g.setColor(topLights[i].getColor().darker().darker().darker());
            g.fillRect(offset,0,lightSize,lightSize);
            offset+=lightSize+hspace;
        }

        offset = lightSize + hspace/2;
        for (int i = bottomLights.length-1; i >= 0; i--) {
            if (bottomLights[i].getOn() == true) g.setColor(bottomLights[i].getColor());
            else g.setColor(bottomLights[i].getColor().darker().darker().darker());
            g.fillRect(offset,clipHeight-lightSize,lightSize,lightSize);
            offset+=lightSize+hspace;
        }

        //left and right
        int vspace = (clipHeight-((maxSideLength+2)*lightSize))/maxSideLength;
        offset = lightSize + vspace/2;
        for (int i = leftLights.length-1; i >= 0; i--) {
            if (leftLights[i].getOn() == true) g.setColor(leftLights[i].getColor());
            else g.setColor(leftLights[i].getColor().darker().darker().darker());
            g.fillRect(0,offset,lightSize,lightSize);
            offset+=lightSize+vspace;
        }

        offset = lightSize+vspace/2;
        for (int i = 0; i < rightLights.length; i++) {
            if (rightLights[i].getOn() == true) g.setColor(rightLights[i].getColor());
            else g.setColor(rightLights[i].getColor().darker().darker().darker());
            g.fillRect(clipWidth-lightSize,offset,lightSize,lightSize);
            offset+=lightSize+vspace;
        }
    }
}
