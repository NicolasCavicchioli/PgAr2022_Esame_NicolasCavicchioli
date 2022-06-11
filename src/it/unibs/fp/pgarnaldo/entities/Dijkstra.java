package it.unibs.fp.pgarnaldo.entities;

import it.unibs.fp.pgarnaldo.tiles.CellData;

public class Dijkstra extends EssereVivente {

	public Dijkstra() {
		super("Dijkstra", 40, 10, 10);
	}

	@Override
	public CellData getData() {
		return new CellData("D", true, false);
	}
	
	
}
