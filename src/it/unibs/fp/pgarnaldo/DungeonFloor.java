package it.unibs.fp.pgarnaldo;

import it.unibs.fp.pgarnaldo.tiles.Mappable;

public class DungeonFloor {
	
	Mappable[][] grid;
	
	public DungeonFloor(int height, int width) {
		grid = new Mappable[height][width];
	}
	
	
	public void set(int i, int j, Mappable m) {
		grid[i][j] = m;
		
	}
	public void setItem(int i, int j, Mappable m) {
		set(grid.length-1-i,j,m);
	}
	public Mappable get(int i, int j) {
		return grid[i][j];
	}
	
	public String toString(int px, int py) {
		StringBuffer sb = new StringBuffer();
		for (int i = grid.length-1; i>=0; i--) {
			for (int j = 0; j < grid[0].length; j++) {
				if (j==px && i==py) {
					sb.append("O");
					continue;
				}
				if (grid[i][j]==null) sb.append(".");
				else sb.append(grid[i][j].getMapIcon());
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	
}
