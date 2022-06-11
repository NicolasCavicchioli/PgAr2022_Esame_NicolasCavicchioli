package it.unibs.fp.pgarnaldo;

import it.unibs.fp.mylib.InputDati;

public class Main {
	
	private static String filePath = "files\\livelli\\";
	private static String[] levels
	= {"livello1.xml","livello2.xml","livello3.xml","livello4Boss.xml","livello4Princess.xml"};
	
	
	public static void main(String[] args) {
		
		String playerName = InputDati.leggiStringaNonVuota("Inserisci il tuo nome");
		DungeonManager dm = new DungeonManager(playerName, filePath, levels);
		dm.inizializzaOggetti();
		
		try { while(true) {
			dm.printFloor();
			dm.playerTurn();
		}} catch(ExitProgram e) {}
		
	}
	
	
	/**
	 * Scrive le stringhe date una di fianco all'altra.
	 * 
	 * @param a la prima stringa
	 * @param b la seconda stringa
	 */
	public static void parallelPrintln(String a, String b) {
		String[] aa = a.split("\n");
		String[] bb = b.split("\n");
		int len = (aa.length - bb.length) / 2;
		int i=0;
		
		for (; i<len; i++) System.out.println(aa[i]);
		for (int j=0,n=Math.min(len+bb.length, aa.length); i<n; i++,j++) {
			System.out.println(aa[i]+"\t\t"+bb[j]);
		}
		for (; i<aa.length; i++) System.out.println(aa[i]);
		
	}
	
	
	public static class ExitProgram extends RuntimeException {
		
		private static final long serialVersionUID = 1L;
		
	}
	
}
