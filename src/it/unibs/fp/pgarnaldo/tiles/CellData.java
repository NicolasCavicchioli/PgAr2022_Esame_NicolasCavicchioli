package it.unibs.fp.pgarnaldo.tiles;

public class CellData {
	public final String mapSymbol;
	public final boolean canWalk, canInteract;
	
	public CellData(String s, boolean w, boolean i) {
		mapSymbol = s;
		canWalk = w;
		canInteract = i;
	}
	
}
