package application;

import java.awt.Point;
import java.util.Random;

public class OceanMap {
	boolean[][] islands;
	int dimensions;
	int islandCount;
	Random rand = new Random();
	Point shipLocation;
	
	// Constructor
	// Not adding validation code so make sure islandCount is much less than dimension^2
	public OceanMap(int dimensions, int islandCount){
		this.dimensions = dimensions;
		this.islandCount = islandCount;
		createGrid();
		placeIslands();
		shipLocation = placeShip();
	}
	
	// Create an empty map
	private void createGrid(){
		 islands = new boolean[dimensions][dimensions];
		 for(int x = 0; x < dimensions; x++)
			 for(int y = 0; y < dimensions; y++)
				 islands[x][y] = false;
	}
	
	// Place islands onto map
	private void placeIslands(){
		int islandsToPlace = islandCount;
		while(islandsToPlace >0){
			int x = rand.nextInt(dimensions);
			int y = rand.nextInt(dimensions);
			if(islands[x][y] == false){
				islands[x][y] = true;
				islandsToPlace--;
			}
		}
	}
	
	private Point placeShip(){
		boolean placedShip = false;
		int x=0,y=0;
		while(!placedShip){
			x = rand.nextInt(dimensions);
			y = rand.nextInt(dimensions);
			if(islands[x][y] == false){
				placedShip = true;
			}
		}
		return new Point(x,y);
	}

	public Point getShipLocation(){
		return shipLocation;
	}
	
	// Return generated map
	public boolean[][] getMap(){
		return islands;
	}
	
	public int getDimensions(){
		return dimensions;
	}
	
	public boolean isOcean(int x, int y){
		if (!islands[x][y])
			return true;
		else
			return false;
	}
}
