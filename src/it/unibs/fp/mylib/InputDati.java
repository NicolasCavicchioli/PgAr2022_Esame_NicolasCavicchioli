package it.unibs.fp.mylib;

import java.util.*;

public class InputDati {
	
	private static Scanner lettore = creaScanner();
	
	private final static String ERRORE_FORMATO
	= "Attenzione: il dato inserito non e' nel formato corretto";
	private final static String ERRORE_MINIMO
	= "Attenzione: e' richiesto un valore maggiore o uguale a ";
	private final static String ERRORE_STRINGA_VUOTA
	= "Attenzione: non hai inserito alcun carattere";
	private final static String ERRORE_STRINGA_COMBACIA
	= "Attenzione: non è possibile inserire la stringa %s";
	private final static String ERRORE_MASSIMO
	= "Attenzione: e' richiesto un valore minore o uguale a ";
	private final static String MESSAGGIO_AMMISSIBILI
	= "Attenzione: i caratteri ammissibili sono: ";
	private final static String FINE = " > ";
	
	private final static char RISPOSTA_SI = 'Y';
	private final static char RISPOSTA_NO = 'N';
	
	
	private static Scanner creaScanner() {
		Scanner creato = new Scanner(System.in);
		creato.useDelimiter(System.getProperty("line.separator"));
		return creato;
	}
	
	
	public static String leggiStringa(String messaggio) {
		System.out.print(messaggio + FINE);
		return lettore.next();
	}
	
	public static String leggiStringaNonVuota(String messaggio) {
		boolean finito = false;
		String lettura = null;
		do {
			lettura = leggiStringa(messaggio).trim();
			if (lettura.length() == 0)
				System.out.println(ERRORE_STRINGA_VUOTA);
			else
				finito = true;
		} while (!finito);
		
		return lettura;
	}
	
	
	public static char leggiChar(String messaggio) {
		return leggiStringaNonVuota(messaggio).charAt(0);
	}
	
	public static char leggiUpperChar(String messaggio, String ammissibili) {
		boolean finito = false;
		char valoreLetto = '\0';
		do {
			valoreLetto = leggiChar(messaggio);
			valoreLetto = Character.toUpperCase(valoreLetto);
			if (ammissibili.indexOf(valoreLetto) != -1)
				finito = true;
			else
				System.out.println(MESSAGGIO_AMMISSIBILI + Arrays.toString(ammissibili.toCharArray()));
		} while (!finito);
		return valoreLetto;
	}
	
	
	public static void enterToContinue(String messaggio) {
		leggiStringa(messaggio);
	}
	
}
