package application;

import javafx.scene.image.Image;

public abstract class PirateShipFactory {
	Image image;
	String imagePath = "file:src/images/pirateShip.png";
	int scalingFactor = Main.scalingFactor;
	
	//Sets the image for each pirateship made
	public PirateShipFactory() {
		setImageFromPath(imagePath);
	}
	//starting factory
	public abstract PirateShip createPirateShip(Ship columbus, int x, int y);


	private void setImageFromPath(String path) {
		imagePath = path;
		image = new Image(path,scalingFactor,scalingFactor,true,true);
		
	}
}
