package main;

import patterns.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Runner extends JPanel {

    public final static int WIDTH = 500;
    public final static int HEIGHT = 500;

    LightArray lightArr;
    JFrame jf;

    private Runner() {
        jf = new JFrame("LEDs");
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(WIDTH,HEIGHT);
        jf.add(this);
    }

    public static void main(String[] args) throws Exception{
        Runner r = new Runner();
        r.run(new ClockwisePattern());
    }

    public void run(Pattern p) throws Exception{
        lightArr = new LightArray(12);
        Clock c = new Clock();
        c.setPattern(p);
        c.start(lightArr, this);
    }


    public void repaintFrame() {
        jf.repaint();
    }
    public JFrame getFrame() {return jf;}

    @Override
    public void paintComponent(Graphics g) {
        //drawing the LEDs
        try {
            lightArr.paint(g);
        } catch (NullPointerException e) { }
    }
}
