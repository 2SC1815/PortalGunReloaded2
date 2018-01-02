package com.murabi10.portalgunreloaded.portalgun;


/**
 * High speed Low precision Trigonometrics
 */
public class Trigonometrics {


    private static final int NUM = 360;
    private static double[] sinMap;
    private static double[] cosMap;
    private static double[] sinMap2;
    private static double[] cosMap2;

    static {
        sinMap = new double[NUM];
        for (int i = 0; i < NUM; i++) {
            sinMap[i] = Math.sin((double) i / NUM * Math.PI * 2);
        }
        cosMap = new double[NUM];
        for (int i = 0; i < NUM; i++) {
            cosMap[i] = Math.cos((double) i / NUM * Math.PI * 2);
        }
        sinMap2 = new double[NUM];
        for (int i = 0; i < NUM; i++) {
            sinMap2[i] = Math.sin((double) (i+180) / NUM * Math.PI * 2);
        }
        cosMap2 = new double[NUM];
        for (int i = 0; i < NUM; i++) {
            cosMap2[i] = Math.cos((double) (i+180) / NUM * Math.PI * 2);
        }
    }

    public static final double sin(double t){
        t = t / (Math.PI * 2) * NUM;
        return sinMap[ ((int) t ) % NUM];
    }

    public static final double cos(double t){
        t = t / (Math.PI * 2) * NUM;
        return cosMap[ ((int) t ) % NUM];
    }

    public static final double sin2(double t){
        t = t / (Math.PI * 2) * NUM;
        return sinMap2[ ((int) t ) % NUM];
    }

    public static final double cos2(double t){
        t = t / (Math.PI * 2) * NUM;
        return cosMap2[ ((int) t ) % NUM];
    }
}
