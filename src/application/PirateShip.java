package application;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javafx.scene.image.ImageView;

//the pirate ship class implements the obsever interface and observes the ship object

public class PirateShip implements Observer{
	private Point location;
	Random rand = new Random();
	private int[][] oceanMap;
	private PursuitStrategy strategy;
	private ImageView pirateImageView;
	private final int scalingFactor = Main.scalingFactor;
	private Ship columbus;
	
	public PirateShip(Ship Columbus, int[][] oceanMap) {
		this.columbus = columbus;
		location = new Point(0,0);
		this.oceanMap = oceanMap;
		setStrategy(new DirectPathStrategy());
	}
	
    public void setStrategy(PursuitStrategy strategy) { 
    	this.strategy = strategy; 
    }
    public void setLocation(int x, int y) {
    	location = new Point(x,y);
    }

    private void movePirate(int x, int y) {
        oceanMap[location.y][location.x] = CellTypes.OCEAN;

        location.move(x, y);

        oceanMap[y][x] = CellTypes.PIRATE;
    }
    
    public Point getLocation() {
        return location;
      }

      public void setColumbus(Ship ship) {
          columbus = ship;
      }

      public void setImageView(ImageView imageV) {
          pirateImageView = imageV;
          updateImageView();
      }
      public ImageView getImageView() { 
    	  return pirateImageView; 
      }
	
	@Override
	public void update(Observable o, Object arg) {
		 Point cLocation = columbus.getLocation();
	        if (location != cLocation) {
	            Point nextPosition = strategy.getNextPosition(location, cLocation);
	            movePirate((int) nextPosition.getX(), (int) nextPosition.getY());
	        }
	        if(getLocation().equals(columbus.getLocation())) {
	            columbus.hitPirate = true;
	            System.out.println("Columbus captured at " + columbus.getLocation().x + ", " + columbus.getLocation().y +
	                                    " by pirate at " + location.x + ", " + location.y);
	        }
	        updateImageView();
		
	}
	
	 private void updateImageView() {
	        pirateImageView.setX(getLocation().x * scalingFactor);
	        pirateImageView.setY(getLocation().y * scalingFactor);
	    }
	
	
}
