package it.unibs.fp.pgarnaldo.entities;

import java.util.ArrayList;
import java.util.Collections;
import it.unibs.fp.mylib.EstrazioniCasuali;
import it.unibs.fp.pgarnaldo.tiles.CellData;

public class Monster extends EssereVivente {
	
	public Monster() {
		super(
			permuta("Dijkstra"),
			EstrazioniCasuali.estraiIntero(15,  25),
			5,
			5
		);
	}
	
	private static String permuta(String str) {
		ArrayList<Character> list = new ArrayList<Character>();
		for (char c : str.toCharArray()) list.add(c);
		Collections.shuffle(list);
		return String.valueOf(list.toArray(new Character[0]));
	}
	
	@Override
	public CellData getData() {
		return new CellData("M", true, false);
	}
	
}
