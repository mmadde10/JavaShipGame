package application;

import java.awt.Point;
import java.util.Observable;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ship extends Observable{
	protected static Point position;
	protected static OceanMap oceanMap;
	private ImageView imageView;
	private int scalingFactor = 35;
	public boolean hasTreasure = false;
	public boolean hitPirate = false;
	public boolean hitMonster = false;
	
	public Ship() {
		Image image = new Image("file:src/images/ship.png", scalingFactor, scalingFactor, true, true);
		imageView = new ImageView(image);
	}

	public Ship(OceanMap oceanMap) {
		Random random = new Random();
		while(true) {
			int x = random.nextInt(oceanMap.getMap()[0].length);
			int y = random.nextInt(oceanMap.getMap().length);
			if(oceanMap.getMap()[y][x] == CellTypes.OCEAN) {
				position = new Point(x, y);
				break;
			}
		}
		this.oceanMap = oceanMap;

		Image image = new Image("file:src/images/ship.png", scalingFactor, scalingFactor, true, true);
		imageView = new ImageView(image);
		updateImageView();
	}
	public void updateImageView() {
		imageView.setX(position.getX() * scalingFactor);
		imageView.setY(position.getY() * scalingFactor);
	}

	public ImageView getImageView() {
		return imageView;
	}

	public static Point getLocation() {
		return position;
	}
	public void move(int x, int y) {
		position = new Point(x, y);
		setChanged();
		notifyObservers();
		updateImageView();
	}
	//Directions to move ship and checks to see if it hits a wall or a game object
	public void goEast() {
		final int[][] grid  = oceanMap.getMap();
		final int bounds = grid[0].length;

		if(position.x + 1 < bounds) {
			int cell = grid[position.y][position.x + 1];
			if(cell == CellTypes.OCEAN) {
				move(position.x + 1, position.y);
                oceanMap.updateCell(position.x, position.y, CellTypes.OCEAN);
			}
			else if(cell == CellTypes.TREASURE) {
				move(position.x + 1, position.y);
				hasTreasure = true;
			}

			else if(cell == CellTypes.PIRATE){
				hitPirate = true;
			}

			else if(cell == CellTypes.MONSTER){
				hitMonster = true;
			}
		}
	}

	public void goWest() {

		final int[][] grid = oceanMap.getMap();

		if(position.x - 1 >= 0){
		    int cell = grid[position.y][position.x - 1];
            if(cell == CellTypes.OCEAN) {
				move(position.x - 1, position.y);
                oceanMap.updateCell(position.x, position.y, CellTypes.OCEAN);
			}
			else if(cell == CellTypes.TREASURE) {

				move(position.x - 1, position.y);
				hasTreasure = true;
			}
			else if(cell == CellTypes.PIRATE) {
				hitPirate = true;
			}

			else if(cell == CellTypes.MONSTER){
				hitMonster = true;
			}

		}
	}

	public void goNorth() {

		final int[][] grid = oceanMap.getMap();

		if(position.y - 1 >= 0) {
		    int cell = grid[position.y - 1][position.x];
            if(cell == CellTypes.OCEAN) {
				move(position.x, position.y - 1);
                oceanMap.updateCell(position.x, position.y, CellTypes.OCEAN);
			}
			else if(cell == CellTypes.TREASURE) {
				move(position.x, position.y - 1);
				hasTreasure = true;
			}

			else if(cell == CellTypes.PIRATE) {
				hitPirate = true;
			}

			else if(cell == CellTypes.MONSTER){
				hitMonster = true;
			}

		}
	}

	public void goSouth() {

		final int[][]grid = oceanMap.getMap();
		final int bounds = grid.length;

		if(position.y + 1 < bounds) {
		    int cell = grid[position.y + 1][position.x];
            if(cell == CellTypes.OCEAN) {
				move(position.x, position.y + 1);
				oceanMap.updateCell(position.x, position.y, CellTypes.OCEAN);
			}
			else if(cell == CellTypes.TREASURE) {
				move(position.x, position.y + 1);
				hasTreasure = true;
			}

			else if(cell == CellTypes.PIRATE) {
				hitPirate = true;
			}

			else if(cell == CellTypes.MONSTER){
				hitMonster = true;
			}
		}
	}
	   
}
