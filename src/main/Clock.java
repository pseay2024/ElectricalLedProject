package main;

import patterns.Pattern;

import java.util.Timer;

public class Clock {
    Pattern p;
    boolean state;
    public void setPattern(Pattern p) {
        this.p = p;
        state = getSignal();
    }
    public static boolean getSignal() {
        return System.currentTimeMillis()/500 % 2 == 0; //toggles signal every second
    }

    public void start(LightArray lightArr, Runner runner) throws InterruptedException {
        while(true) {
            if (getSignal() != state) {
                state = getSignal();
                if (state) {
                    //rising edge
                    p.getNewValues(lightArr);
                    runner.repaintFrame();
                }
            }
            Thread.sleep(50);
        }
    }
}
