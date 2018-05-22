package application;

import java.awt.Point;
import java.util.Observable;

public class Ship extends Observable{
	 Point currentLocation;
	    OceanMap oceanMap;
	    
	    public Ship(OceanMap oceanMap){    	
	    	this.oceanMap = oceanMap;
	    	currentLocation = oceanMap.getShipLocation();
	    }
	    
	    public Point getShipLocation(){
	    	return currentLocation;
	    }
	    
	    public void move(String s){
			if(s.equals("EAST")){
				if(currentLocation.x<oceanMap.getDimensions()-1 && oceanMap.isOcean(currentLocation.x+1, currentLocation.y)){
		    		currentLocation.x++;
		    	} 
			}
			else if(s.equals("WEST")){
				if(currentLocation.x >0 && oceanMap.isOcean(currentLocation.x-1, currentLocation.y)){
		    		currentLocation.x--;
		    	}  
			}
			else if(s.equals("NORTH")){
				if(currentLocation.y>0 && oceanMap.isOcean(currentLocation.x, currentLocation.y-1)){
		    		currentLocation.y--;
		    	}  
			}
			else{
				if(currentLocation.y<oceanMap.getDimensions()-1 && oceanMap.isOcean(currentLocation.x, currentLocation.y+1)){
		    		currentLocation.y++;
		    	}  
			}
			setChanged();
			notifyObservers();
		}
}
