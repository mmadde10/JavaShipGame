package application;
	
import java.util.LinkedList;
import java.util.List;

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
	
	private final int dimension = OceanMap.getInstance().getDimension();
	static final int scalingFactor = 35; 
	protected static OceanMap oceanMap = OceanMap.getInstance();
	private Ship ship = new Ship(oceanMap);
	private ImageView shipImageView;
	//image paths
	private Image islandImage = new Image("file:src/images/island.jpg",scalingFactor, scalingFactor, true, true);
	private Image treasureImage = new Image("file:src/images/treasure.png",scalingFactor,scalingFactor,true,true);
    private Image win = new Image("file:src/images/win.png",dimension*scalingFactor,dimension*scalingFactor,true,true);
    private Image lose = new Image("file:src/images/lose.png",dimension*scalingFactor,dimension*scalingFactor,true,true);
    private Image lose1 = new Image("file:src/images/lose1.png",dimension*scalingFactor,dimension*scalingFactor,true,true);
	//list for pirate ships
    private List<PirateShip> pirates = new LinkedList<PirateShip>();
	private PirateShipFactoryGenerator factoryGenerator = new PirateShipFactoryGenerator();
	private PirateShipFactory pirateFactory = new NormalPirateShipFactory();
	protected static boolean stop = false;
	private AnchorPane root;
	Monster monster;
	Thread monstersThread;
	private Scene scene;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			root = new AnchorPane();
			scene = new Scene(root, dimension*scalingFactor, dimension*scalingFactor);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Marks Ship Game");
			drawMap(oceanMap.getMap(), root); 
            createPirates();
            addPirates(root); 
			loadShipImage(root); 
			//adding monsters to the pane
			monster = new Monster(scalingFactor);
			monster.addToPane(root.getChildren());
			//init monster thread
			monstersThread = new Thread(monster);
			monstersThread.start();
			monster.getShipPoint(ship.getLocation());
			primaryStage.show();
			startSailing(scene);
			setObservers();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void startSailing(Scene scene) {
		scene.setOnKeyPressed((e) -> {
			if (stop == false){
				switch(e.getCode()) {
					case RIGHT:
						ship.goEast();
						break;
					case LEFT:
						ship.goWest();
						break;
					case DOWN:
						ship.goSouth();
						break;
					case UP:
						ship.goNorth();
						break;
					default:
						break;
				}
				checkTreasure();
				checkPirate();
				monster.getShipPoint(ship.getLocation());
				if (monster.isGameOver) { 
					//if hit monster => game over
					checkMonster();
				}
			}
		});
	}
	
	private void checkMonster() {
		//Tells game to stop and add image
		stop = true;
		addMonsterLoseImage(root);
		System.out.println("Ahh monster! You lose!");
	}

	private void checkPirate() {
		//tells game to stop and add lose image
		if (ship.hitPirate == true){
			stop = true;
			addLoseImage(root);
			System.out.println("Ahh pirate! You lose!");
		}
		
	}


	private void checkTreasure() {
		//tells game to stop and add win image
		if (ship.hasTreasure == true){ 
			stop = true;
			addWinImage(root);
			System.out.println("You found the treasure! You win!");
		}
	}
//Methods for adding images to pane
	private void addWinImage(AnchorPane root) {
		ImageView winImageView = new ImageView(win);
		winImageView.setX(0);
		winImageView.setY(0);
		root.getChildren().add(winImageView);
	}
	private void addLoseImage(AnchorPane root) {
		ImageView loseImageView = new ImageView(lose);
		loseImageView.setX(0);
		loseImageView.setY(0);
		root.getChildren().add(loseImageView);
	}
	private void addMonsterLoseImage(AnchorPane root) {
		ImageView loseImageView = new ImageView(lose1);
		loseImageView.setX(0);
		loseImageView.setY(0);
		root.getChildren().add(loseImageView);	
	}
	
	private void addShipImageView(ImageView iv) {
    	root.getChildren().add(iv);
	}
	//Setting ships with image
	private void createNewShip() {
		ship.getImageView().setImage(null);
		ship.deleteObservers();
		for(PirateShip pirate : pirates) {
			pirate.setColumbus(ship);
		}
		root.getChildren().add(ship.getImageView());
		setObservers();
		startSailing(scene);
	}
	private void setObservers() {
		for(PirateShip pirate : pirates) {
			ship.addObserver(pirate);
		}
	}
	private void loadShipImage(AnchorPane root) {
		root.getChildren().add(ship.getImageView());
	}
	
	private void addIslandImage(AnchorPane root, int x, int y) {
	      ImageView islandImageView = new ImageView(islandImage);
	      islandImageView.setX(x * scalingFactor);
	      islandImageView.setY(y * scalingFactor);
	      root.getChildren().add(islandImageView);
	   }
	  
	 private void addTreasureImage(AnchorPane root, int x, int y) {
	      ImageView treasureImageView = new ImageView(treasureImage);
	      treasureImageView.setX(x*scalingFactor);
	      treasureImageView.setY(y*scalingFactor);
	      root.getChildren().add(treasureImageView);
	   }
	 
	 //Start of pirate factory
	private void createPirates() {
	        for(int y = 0; y < dimension; y++) {
	            for (int x = 0; x < dimension; x++) {
	                if (oceanMap.getMap()[y][x] == CellTypes.PIRATE) {
	                    pirateFactory = factoryGenerator.getRandomFactory();
	                    PirateShip pirate = pirateFactory.createPirateShip(ship, x, y);
	                    pirates.add(pirate);
	                }
	            }
	        }
		}
	private void addPirates(AnchorPane root) {
		for(PirateShip pirate : pirates) {
			root.getChildren().add(pirate.getImageView());
		}
	}
	//Drawing map, switch case for what images goes in what cell
	private void drawMap(int[][] map, AnchorPane root) {
		for(int y = 0; y < dimension; y++) {
			for(int x = 0; x < dimension; x++) {
				int X = x * scalingFactor;
				int Y = y * scalingFactor;
				int W = scalingFactor;
				int H = W;
				Rectangle rect = new Rectangle(X, Y, W, H);
				rect.setStroke(Color.BLACK);

				int cell = map[y][x];
				switch (cell) {
                    case CellTypes.ISLAND:
                        addIslandImage(root, x, y);
                        break;
                    case CellTypes.PIRATE:            
                        rect.setFill(Color.PALETURQUOISE);
						root.getChildren().add(rect);
                        break;
                    case CellTypes.TREASURE:
                        addTreasureImage(root,x,y);
                        break;
                    default:  //Default case is ocean
                    case CellTypes.OCEAN:
                        rect.setFill(Color.PALETURQUOISE);
                        root.getChildren().add(rect);
                        break;
                }
			}
		}
	}
	public void setFactory(PirateShipFactory factory) {
		pirateFactory = factory;
	}
	public static void main(String[] args) {
		launch(args);
	}
}
