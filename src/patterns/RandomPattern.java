package patterns;

import main.LightArray;

import java.util.Random;

public class RandomPattern implements Pattern{
    @Override
    public LightArray getNewValues(LightArray old) {
        Random rand = new Random();
        for (int i = 0; i < old.length; i++) {
            old.setLight(i, rand.nextBoolean());
        }
        return old;
    }
}
