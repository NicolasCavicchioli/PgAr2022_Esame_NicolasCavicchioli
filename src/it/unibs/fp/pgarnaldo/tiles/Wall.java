package it.unibs.fp.pgarnaldo.tiles;

public class Wall implements Mappable {
	
	private CellData data;
	
	public Wall(String icon) {
		data = new CellData(icon, false, false);
	}
	
	@Override
	public CellData getData() {
		return data;
	}
	
	
}
