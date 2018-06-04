package application;

import javafx.scene.image.ImageView;
//Pirate ship factory for extreme pirate who uses bfs as a strategy
public class ExtremePirateShipFactory extends PirateShipFactory {

	 public ExtremePirateShipFactory() {
	        super();
	   }
	 public PirateShip createPirateShip(Ship columbus, int x, int y) {
	        OceanMap oceanMap = OceanMap.getInstance();
	        PirateShip ps = new ExtremePirateShip(columbus, oceanMap.getMap());
	        ps.setLocation(x, y);
	        ps.setImageView(new ImageView(image));
	        System.out.println("ExtremePirateShip added.");
	        return ps;
	    }


}
