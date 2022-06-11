package it.unibs.fp.pgarnaldo.tiles;

import it.unibs.fp.pgarnaldo.DungeonManager;

public class StairDown implements Mappable {
	
	@Override
	public CellData getData() {
		return new CellData("t", true, false);
	}
	
	@Override
	public void onPlayerSteppingOver(DungeonManager dm) {
		dm.sendPlayerDownstairs();
	}
	
}
