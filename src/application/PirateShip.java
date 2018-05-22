package application;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class PirateShip implements Observer{
	int dimensions;
	OceanMap oceanMap;
	Point currentLocation;
	Point pirateLocation;
	Point shipLocation;
	Random rand = new Random();
	
	
	public PirateShip(OceanMap oceanMap) {
		this.oceanMap = oceanMap;
		while(true){
			int x = rand.nextInt(oceanMap.dimensions);
			int y = rand.nextInt(oceanMap.dimensions);
			if(oceanMap.getMap()[x][y] != true){
				pirateLocation = new Point(x,y);
				break;
			}
		}
	}
	public Point getPirateLocation(){
		return this.pirateLocation;
	}
	
	public void movePirate(){
		if(pirateLocation.x - shipLocation.x  == 0){}
		else if(pirateLocation.x - shipLocation.x < 0){
			if(pirateLocation.x<oceanMap.getDimensions()-1 && oceanMap.isOcean(pirateLocation.x+1, pirateLocation.y))
				pirateLocation.x++;
		}
		else if(pirateLocation.x>0 && oceanMap.isOcean(pirateLocation.x-1,pirateLocation.y))
			pirateLocation.x--;
		
		if(pirateLocation.y - shipLocation.y  == 0){}
		else if(pirateLocation.y - shipLocation.y < 0){
			if(pirateLocation.y<oceanMap.getDimensions()-1 && oceanMap.isOcean(pirateLocation.x, pirateLocation.y+1))
				pirateLocation.y++;
		}
		else if(pirateLocation.y>0 && oceanMap.isOcean(pirateLocation.x, pirateLocation.y-1))
			pirateLocation.y--;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Ship){
			shipLocation = ((Ship)o).getShipLocation();
			movePirate();
		}
		
	}
	
	
}
