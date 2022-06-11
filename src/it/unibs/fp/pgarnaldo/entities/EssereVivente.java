package it.unibs.fp.pgarnaldo.entities;

import it.unibs.fp.mylib.EstrazioniCasuali;
import it.unibs.fp.pgarnaldo.oggetti.Arma;
import it.unibs.fp.pgarnaldo.tiles.Mappable;

public abstract class EssereVivente implements Mappable {
	
	public final String nome;
	protected double vita;
	public final double attaccoBase, difesaBase;
	public int x,y;
	protected Arma arma = Arma.empty();
	
	public EssereVivente(String nome, double vita, double attaccoBase, double difesaBase) {
		this.nome = nome;
		this.vita = vita;
		this.attaccoBase = attaccoBase;
		this.difesaBase = difesaBase;
	}
	public EssereVivente(String nome, double vita, double attaccoBase, double difesaBase, Arma arma) {
		this(nome, vita, attaccoBase, difesaBase);
		this.arma = arma;
	}
	
	public void move(int dx, int dy) {
		x += dx;
		y += dy;
	}
	
	public boolean isAlive() {
		return vita > 0;
	}
	
	public double calcolaDanno() {
		double modificatore = EstrazioniCasuali.estraiDouble()<0.075 ? 1.5 : 1;
		double danno = ((2*arma.potenza*attaccoBase)/(25*difesaBase)+2) * modificatore;
		return danno;
	}
	
	public void prendiDanno(double danno) {
		vita -= danno;
	}
	
}
