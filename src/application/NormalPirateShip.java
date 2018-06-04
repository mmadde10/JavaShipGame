package application;
//creates normal pirate ship that uses direct path strategy
public class NormalPirateShip extends PirateShip{

	public NormalPirateShip(Ship columbus, int[][] oceanMap) {
		super(columbus, oceanMap);
		setStrategy(new DirectPathStrategy());
	}

}
