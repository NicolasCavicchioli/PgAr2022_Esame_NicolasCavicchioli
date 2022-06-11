package it.unibs.fp.pgarnaldo.tiles;

import it.unibs.fp.pgarnaldo.DungeonManager;

public interface Mappable {
	
	CellData getData();
	
	public default String getMapIcon() {
		return getData().mapSymbol;
	}
	
	
	/**
	 * Event called when the player is stepping on this object.
	 */
	public default void onPlayerSteppingOver(DungeonManager dm) {
	}
	/**
	 * Event called when the player is interacting with this object.
	 */
	public default void onInteractionWithPlayer(DungeonManager dm) {
	}
	
	
	public static Mappable of (String s, boolean w, boolean i) {
		return () -> new CellData(s, w, i);
	}
	
	
	public class Empty implements Mappable {
		
		@Override
		public CellData getData() {
			return new CellData(".", true, false);
		}
		
	}
	
}
