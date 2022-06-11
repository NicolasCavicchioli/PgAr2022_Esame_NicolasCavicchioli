package it.unibs.fp.mylib;

import java.util.Random;

public class EstrazioniCasuali {
	
	private static Random rand = new Random();
	
	public static int estraiIntero(int max) {
		return rand.nextInt(max+1);
	}
	public static int estraiIntero(int min, int max) {
		return min + estraiIntero(max-min);
	}
	
	public static double estraiDouble() {
		return rand.nextDouble();
	}
	
}
