package patterns;

import main.LightArray;

public class ClockwisePattern implements Pattern{

    @Override
    public LightArray getNewValues(LightArray old) {
        LightArray newLights = new LightArray(old.length);
        //sets all lights to the previous light
        for (int i = 1; i < old.length; i++) {
            newLights.setLight(i, old.getLight(i-1));
        }
        newLights.setLight(0, old.getLight(old.length-1));
        //sees if there are no lights on
        boolean noLightsOn = true;
        for (int i = 0; i < old.length; i++) {
            if (old.getLight(i)) {
                noLightsOn = false;
                break;
            }
        }
        //sets the first light on if none were on
        if (noLightsOn) {
            newLights.setLight(0, true);
        }
        return newLights;
    }
}
