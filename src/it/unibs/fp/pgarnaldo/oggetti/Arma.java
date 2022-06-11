package it.unibs.fp.pgarnaldo.oggetti;

public class Arma extends Oggetto {
	
	public final int potenza;
	
	
	public Arma(String nome, int potenza) {
		super(nome, TipoOggetto.ARMA);
		this.potenza = potenza;
	}
	
	
	public static Arma empty() {
		return new Arma("Pugno", 1);
	}
	
}
