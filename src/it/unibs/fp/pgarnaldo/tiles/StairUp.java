package it.unibs.fp.pgarnaldo.tiles;

import it.unibs.fp.pgarnaldo.DungeonManager;

public class StairUp implements Mappable {
	
	@Override
	public CellData getData() {
		return new CellData("T", true, false);
	}
	
	@Override
	public void onPlayerSteppingOver(DungeonManager dm) {
		dm.sendPlayerUpstairs();
	}
	
}
