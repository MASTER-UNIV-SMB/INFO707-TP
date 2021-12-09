package fr.enzoharisthomasclement.utils;

import java.util.Random;

public class RandomUtils {

    public static int randomBetween(int min, int max){
        Random r = new Random();
        int low = 10;
        int high = 100;
        return r.nextInt(high-low) + low;
    }

}
