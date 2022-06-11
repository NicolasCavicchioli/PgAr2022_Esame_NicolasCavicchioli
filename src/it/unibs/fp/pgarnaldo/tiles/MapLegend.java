package it.unibs.fp.pgarnaldo.tiles;

import java.util.function.Function;
import java.util.function.Supplier;

import it.unibs.fp.pgarnaldo.entities.Dijkstra;
import it.unibs.fp.pgarnaldo.entities.Monster;

public enum MapLegend {
	MURO     ("#", Wall::new),
	VUOTO    (".", Mappable.Empty::new),
	MOSTRO   ("M", Monster::new),
	CHEST    ("C", Chest::new),
	STAIRUP  ("T", StairUp::new),
	STAIRDOWN("t", StairDown::new),
	DIJKSTRA ("D", Dijkstra::new);
	
	
	public final String icon;
	private Function<String,Mappable> func;
	
	private MapLegend(String icon, Supplier<Mappable> gen) {
		this.icon = icon;
		func = s -> gen.get();
	}
	private MapLegend(String icon, Function<String,Mappable> func) {
		this.icon = icon;
		this.func = func;
	}
	
	public static Mappable fromIcon(String s) {
		for (MapLegend ml : values()) {
			if (ml.icon.equals(s)) return ml.func.apply(ml.icon);
		}
		return null;
	}
	
}
