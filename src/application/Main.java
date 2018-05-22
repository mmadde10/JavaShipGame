package application;
	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Main extends Application {
	
	boolean[][] islandMap;  
	Scene scene;
	AnchorPane root;
	final int dimensions = 15;
	final int islandCount = 15;
	final int scalingFactor = 40;
	Image shipImage;
	PirateShip pirateShip;
	ImageView shipImageView;
	ImageView pImageView;
	OceanMap oceanMap;
	Ship ship;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			oceanMap = new OceanMap(dimensions, islandCount);
			islandMap = oceanMap.getMap();
			
			root = new AnchorPane();
			drawMap();
			
			ship = new Ship(oceanMap);	
			pirateShip = new PirateShip(oceanMap);
			ship.addObserver(pirateShip);
			loadShipImage();
			loadPirateImage();
			
			
			
			scene = new Scene(root,600,600);
			primaryStage.setTitle("Marks Ship Game");
			primaryStage.setScene(scene);
			primaryStage.show();
			startSailing();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void loadShipImage(){
		shipImage = new Image("images/ship.png",scalingFactor, scalingFactor, true, true);
		shipImageView = new ImageView(shipImage);
		shipImageView.setX(oceanMap.getShipLocation().x*scalingFactor);
		shipImageView.setY(oceanMap.getShipLocation().y*scalingFactor);
		root.getChildren().add(shipImageView);
	}
	public void loadPirateImage(){
		Image shipImage = new Image("images/pirateShip.png",scalingFactor,scalingFactor,true,true); 
		pImageView = new ImageView(shipImage);
		pImageView.setX(pirateShip.getPirateLocation().x * scalingFactor);
		pImageView.setY(pirateShip.getPirateLocation().y * scalingFactor);
		root.getChildren().add(pImageView);
	}
	
	private void startSailing(){
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent ke) {
				switch(ke.getCode()){
				case RIGHT:
					ship.move("EAST");
				break;
				case LEFT:
					ship.move("WEST");
				break;
				case UP:
					ship.move("NORTH");
				break;
				case DOWN:
					ship.move("SOUTH");
				break;
				default:
				break;
				}
				shipImageView.setX(ship.getShipLocation().x*scalingFactor);
				shipImageView.setY(ship.getShipLocation().y*scalingFactor);
				pImageView.setX(pirateShip.getPirateLocation().x*scalingFactor);
				pImageView.setY(pirateShip.getPirateLocation().y*scalingFactor);
			}
		});
	}
	
	// Draw ocean and islands
	public void drawMap(){
		for(int x = 0; x < dimensions; x++){
			for(int y = 0; y < dimensions; y++){
				Rectangle rect = new Rectangle(x*scalingFactor,y*scalingFactor,scalingFactor,scalingFactor);
				rect.setStroke(Color.BLACK);
				if(islandMap[x][y])
				    rect.setFill(Color.GREEN);
				else
					rect.setFill(Color.PALETURQUOISE);
				root.getChildren().add(rect);
			}
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
