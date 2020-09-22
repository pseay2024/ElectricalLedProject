package patterns;

import main.LightArray;

public class FlashPattern implements Pattern {
    @Override
    public LightArray getNewValues(LightArray old) {
        old.loopSet((light,index) -> !light.getOn());
        return old;
    }
}
