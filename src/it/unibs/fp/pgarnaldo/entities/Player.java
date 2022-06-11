package it.unibs.fp.pgarnaldo.entities;

import java.util.ArrayList;

import it.unibs.fp.pgarnaldo.oggetti.Oggetto;
import it.unibs.fp.pgarnaldo.tiles.CellData;

public class Player extends EssereVivente {
	
	public final ArrayList<Oggetto> inventario = new ArrayList<>();
	
	
	public Player(String mapIcon, String nome) {
		super(nome, 20, 5, 5);
	}
	
	
	public CellData getData() {
		return new CellData("P", true, false);
	}
	
	public void mostraInventario() {
		inventario.forEach(System.out::println);
	}
	
}
