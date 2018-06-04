package application;

public class ExtremePirateShip extends PirateShip {
	 public ExtremePirateShip(Ship columbus, int[][] oceanMap) {
	        super(columbus, oceanMap);
	        setStrategy(new BFSStrategy());
	    }
}
