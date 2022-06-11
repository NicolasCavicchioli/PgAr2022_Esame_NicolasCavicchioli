package it.unibs.fp.pgarnaldo.tiles;

import it.unibs.fp.pgarnaldo.DungeonManager;
import it.unibs.fp.pgarnaldo.oggetti.TipoOggetto;

public class Chest implements Mappable {
	
	private CellData data;
	
	public Chest(String icon) {
		data = new CellData(icon, true, true);
	}
	
	@Override
	public CellData getData() {
		return data;
	}
	
	
	public void onInteractionWithPlayer(DungeonManager dm) {
		dm.playerFoundObject( dm.estraiOggettoDiTipo(TipoOggetto.estraiRand()) );
		dm.clearAtPlayerPos();
	}
	
}
