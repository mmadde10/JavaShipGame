package application;

import java.awt.Point;

import java.util.Random;

//creates the map with a set number of dimensions and island and pirate count
//each cell is an integer correspoding to a game object. Defines in cell types


public class OceanMap {
	static final int DIMENSION = 20;
	static final int ISLAND_COUNT = 40;
	static final int PIRATE_COUNT = 7;
	Random rand = new Random();
	int[][] oceanMap = new int[DIMENSION][DIMENSION];
	private static OceanMap uniqueInstance;
	

	public OceanMap(){
		populateOceanMap();
		createPirates(PIRATE_COUNT);
	}
	
	
	public static OceanMap getInstance() {
		if(uniqueInstance != null) {
			return uniqueInstance;
		}
		else {
			uniqueInstance = new OceanMap();
			return uniqueInstance;
		}
	}
	
	private void populateOceanMap() {
		for(int i = 0; i < ISLAND_COUNT; i++) {
			Point p = getRandomOceanCell();
			updateCell(p.x,p.y,CellTypes.ISLAND);
		}
		Point p = getRandomOceanCell();
		updateCell(p.x,p.y,CellTypes.TREASURE);
		
	}

	void updateCell(int x, int y, int type) {
		if (x >= 0 && y >= 0 && x < oceanMap[0].length && y < oceanMap.length) {
			oceanMap[y][x] = type;
		}	    
	}


	private void createPirates(int pirateCount) {
		Random random = new Random();
		while(pirateCount > 0) {
			int y = random.nextInt(oceanMap.length);
			int x = random.nextInt(oceanMap[0].length);
			if(oceanMap[y][x] == CellTypes.OCEAN) {
				oceanMap[y][x] = CellTypes.PIRATE;
					pirateCount --;
				}
			}
		}
	
	private Point getRandomOceanCell() {
		int x;
		int y;
		do {
			x = rand.nextInt(DIMENSION);
			y = rand.nextInt(DIMENSION);
		}
		while(!isOcean(x,y));
		return new Point(x,y);	
	}


	private boolean isOcean(int x, int y) {
		boolean validIndex = false;
		if(x >= 0 && y>= 0 && x < oceanMap.length && y < oceanMap.length) {
			validIndex = true;	
		}
		return (validIndex && oceanMap[x][y] == CellTypes.OCEAN);
	}
	
	public boolean canEnter(int x, int y) {
	    return isOcean(x, y);
    }

	public int getDimension() { return DIMENSION; }
	
	public int[][] getMap() {
		return oceanMap;
	}
		
}








