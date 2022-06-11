package it.unibs.fp.mylib;

import java.util.List;
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
	public static double estraiDouble(double max) {
		return estraiDouble() * max;
	}
	public static double estraiDouble(double min, double max) {
		return min + estraiDouble(max-min);
	}
	
	public static char estraiChar(String string) {
		int index = estraiIntero(string.length()-1);
		return string.charAt(index);
	}
	
	@SafeVarargs
	public static <T> T estraiObject(T...array) {
		int index = estraiIntero(array.length-1);
		return array[index];
	}
	
	/**
	 * Estrae e restituisce un'oggetto casuale dalla lista specificata,
	 * oppure <code>null</code> se la lista è vuota.
	 * 
	 * @param <T> il tipo dell'oggetto da estrarre
	 * @param list la lista da cui estrarre l'oggetto
	 * @return un'oggetto casuale dalla lista specificata, 
	 *         oppure <code>null</code> se la lista è vuota
	 */
	public static <T> T estraiObject(List<T> list) {
		if (list.size()==0) return null;
		int index = estraiIntero(list.size()-1);
		return list.get(index);
	}
	
	public static <T> T popRandom(List<T> list) {
		if (list.size()==0) return null;
		int index = estraiIntero(list.size()-1);
		return list.remove(index);
	}
	
}
