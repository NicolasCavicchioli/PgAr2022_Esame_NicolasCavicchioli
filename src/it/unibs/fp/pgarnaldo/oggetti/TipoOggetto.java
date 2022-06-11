package it.unibs.fp.pgarnaldo.oggetti;

import it.unibs.fp.mylib.EstrazioniCasuali;

public enum TipoOggetto {
	ARMA   ("Arma",    0.40),
	SCUDO  ("Scuto",   0.35),
	POZIONE("Pozione", 0.25)
	;
	
	public final String tipo;
	public final double chestProb;
	
	private TipoOggetto(String tipo, double prob) {
		this.tipo = tipo;
		chestProb = prob;
	}
	
	public static TipoOggetto estraiRand() {
		double r = EstrazioniCasuali.estraiDouble();
		for (TipoOggetto to : values()) {
			r -= to.chestProb;
			if (r<0) return to; 
		}
		return null;
	}
	
	
}
