package it.unibs.fp.mylib;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

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
	
	public static String leggiStringaNonVuotaUpper(String messaggio) {
		return leggiStringaNonVuota(messaggio).toUpperCase();
	}
	
	public static String leggiStringaNonVuotaDiversaDa(String messaggio, String str) {
		do {
			String lettura = leggiStringaNonVuota(messaggio);
			if (!lettura.equals(str)) return lettura;
			System.out.println(ERRORE_STRINGA_COMBACIA);
		} while (true);
	}
	
	public static String leggiStringaRegex(String messaggio, String regex) {
		do {
			String lettura = leggiStringa(messaggio);
			if (lettura.matches(regex)) return lettura;
			System.out.println(ERRORE_FORMATO);
		} while (true);
	}
	
	public static String[] leggiStringhe(String messaggio, String separator) {
		return leggiStringaNonVuota(messaggio).split(separator);
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
	
	public static char leggiUpperChar(String[] messaggio) {
		if (messaggio.length < 2) throw new IllegalArgumentException();
		return leggiUpperChar(messaggio[0], messaggio[1]);
	}
	
	public static int leggiIntero(String messaggio) {
		do {
			System.out.print(messaggio + FINE);
			try {
				return lettore.nextInt();
			} catch (InputMismatchException e) {
				System.out.println(ERRORE_FORMATO);
				lettore.next();
			}
		} while (true);
		//return leggi_catch(messaggio, lettore::nextInt, e->System.out.println(ERRORE_FORMATO) );
	}
	
	public static int leggiInteroPositivo(String messaggio) {
		return leggiInteroConMinimo(messaggio, 1);
	}
	
	public static int leggiInteroNonNegativo(String messaggio) {
		return leggiInteroConMinimo(messaggio, 0);
	}
	
	public static int leggiInteroConMinimo(String messaggio, int minimo) {
		boolean finito = false;
		int valoreLetto = 0;
		do {
			valoreLetto = leggiIntero(messaggio);
			if (valoreLetto >= minimo)
				finito = true;
			else
				System.out.println(ERRORE_MINIMO + minimo);
		} while (!finito);
		return valoreLetto;
	}
	
	/** estremi inclusi */
	public static int leggiIntero(String messaggio, int minimo, int massimo) {
		boolean finito = false;
		int valoreLetto = 0;
		do {
			valoreLetto = leggiIntero(messaggio);
			if (valoreLetto >= minimo && valoreLetto <= massimo)
				finito = true;
			else if (valoreLetto < minimo)
				System.out.println(ERRORE_MINIMO + minimo);
			else
				System.out.println(ERRORE_MASSIMO + massimo);
		} while (!finito);
		return valoreLetto;
	}
	
	public static double leggiDouble(String messaggio) {
		boolean printMessage = !messaggio.isBlank();
		do {
			if (printMessage) System.out.print(messaggio + FINE);
			try {
				return lettore.nextDouble();
			} catch (InputMismatchException e) {
				System.out.println(ERRORE_FORMATO);
				lettore.next();
			}
		} while (true);
	}
	
	public static double leggiDoubleConMinimo(String messaggio, double minimo) {
		boolean finito = false;
		double valoreLetto = 0;
		do {
			valoreLetto = leggiDouble(messaggio);
			if (valoreLetto >= minimo)
				finito = true;
			else
				System.out.println(ERRORE_MINIMO + minimo);
		} while (!finito);
		return valoreLetto;
	}
	
	public static boolean yesOrNo(String messaggio) {
		messaggio += " [" + RISPOSTA_SI + "/" + RISPOSTA_NO + "]";
		char valoreLetto = leggiUpperChar(messaggio, RISPOSTA_SI +""+ RISPOSTA_NO);
		return valoreLetto == RISPOSTA_SI;
	}
	
	
	
	@SafeVarargs
	public static <T> T scegliTra(String messaggio, T...options) {
		int i=1;
		for (T t : options) {
			System.out.println("%d) %s".formatted(i++, t));
		}
		
		int index = InputDati.leggiIntero(messaggio, 1, options.length)-1;
		return options[index];
	}
	
	public static void enterToContinue(String messaggio) {
		leggiStringa(messaggio);
	}
	
}
