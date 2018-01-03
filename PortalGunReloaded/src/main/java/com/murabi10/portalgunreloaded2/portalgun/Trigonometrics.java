package com.murabi10.portalgunreloaded2.portalgun;

public class Trigonometrics {

	private static double[] sinMap = new double[360];
	private static double[] cosMap = new double[360];
	private static double[] sinMap2;
	private static double[] cosMap2;
	static {
		for (int i = 0; i < 360; i++) {
			sinMap[i] = Math.sin(i / 360.0D * 3.141592653589793D * 2.0D);
		}
		cosMap = new double['Ũ'];
		for (int i = 0; i < 360; i++) {
			cosMap[i] = Math.cos(i / 360.0D * 3.141592653589793D * 2.0D);
		}
		sinMap2 = new double['Ũ'];
		for (int i = 0; i < 360; i++) {
			sinMap2[i] = Math.sin((i + 180) / 360.0D * 3.141592653589793D * 2.0D);
		}
		cosMap2 = new double['Ũ'];
		for (int i = 0; i < 360; i++)
			cosMap2[i] = Math.cos((i + 180) / 360.0D * 3.141592653589793D * 2.0D);
	}


	public static final double sin(double t) {
		t = t / 6.283185307179586D * 360.0D;
		return sinMap[((int) t % 360)];
	}

	public static final double cos(double t) {
		t = t / 6.283185307179586D * 360.0D;
		return cosMap[((int) t % 360)];
	}

	public static final double sin2(double t) {
		t = t / 6.283185307179586D * 360.0D;
		return sinMap2[((int) t % 360)];
	}

	public static final double cos2(double t) {
		t = t / 6.283185307179586D * 360.0D;
		return cosMap2[((int) t % 360)];
	}
}

/* Location:              C:\Users\2SC1815\Desktop\PortalGunReloaded-1.7.2.jar!\com\murabi10\portalgunreloaded\portalgun\Trigonometrics.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */