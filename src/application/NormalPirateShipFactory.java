package application;

import javafx.scene.image.ImageView;

//factory for normal pirate ships

public class NormalPirateShipFactory extends PirateShipFactory {

	public NormalPirateShipFactory() {
		super();
	}
	@Override
	public PirateShip createPirateShip(Ship columbus, int x, int y) {
		 OceanMap oceanMap = OceanMap.getInstance();
	     PirateShip ps = new NormalPirateShip(columbus, oceanMap.getMap());
	     ps.setLocation(x, y);
	     ps.setImageView(new ImageView(image));
	     System.out.println("Normal PirateShip added.");
	     return ps;
	}

}
