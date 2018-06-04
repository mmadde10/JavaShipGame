package application;

import java.awt.Point;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
//Monster class that uses threading 

public class Monster implements Runnable {
	Boolean isRunning = true;
    int radius;
    Random random = new Random();
    int scalingFactor;
    Point ship;
    boolean isGameOver = false;
    int oceanMapDimension;
    int monster_count = 12;
    
    public List<Point> monsterList = new LinkedList<>();

    MonsterSprite[] monsterSprites = new MonsterSprite[monster_count];

    public Monster(int scalingFactor){
        oceanMapDimension = OceanMap.getInstance().getDimension();
        for(int j = 0; j < monster_count; j++){
            int x = random.nextInt(oceanMapDimension);
            int y = random.nextInt(oceanMapDimension);
            monsterSprites[j] = new MonsterSprite(x,y,scalingFactor);
        }
        this.radius = 10;
        this.scalingFactor = scalingFactor;
    }
    //Mosters are drawn and added to the pane

    public void addToPane(ObservableList<Node> sceneGraph){
        for(MonsterSprite monsterSprite: monsterSprites){

            Circle circle = monsterSprite.getCircle();
            System.out.println("Adding circle to pane: " + circle.getCenterX() + " " + circle.getCenterY() + " " + radius);
            sceneGraph.add(circle);
        }
    }

    public void getShipPoint(Point ship){
        this.ship = ship;
        for(Point monsterPoint : monsterList) {
            if (monsterPoint.x == ship.x && monsterPoint.y == ship.y) {
                isGameOver = true;
            }
        }
    }

    private synchronized boolean shouldRun() {
        return !Main.stop;
    }
    //Run thread
    @Override
    public void run() {
        while (shouldRun()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            monsterList = new LinkedList<>();
            for (MonsterSprite monsterSprite : monsterSprites) {
                // Move X
                int xMove = monsterSprite.getX() + random.nextInt(3) - 1;
                if (xMove >= 0 && xMove <= oceanMapDimension)
                    monsterSprite.setX(xMove);
                // Move Y
                int yMove = monsterSprite.getY() + random.nextInt(3) - 1;
                if (yMove >= 0 && yMove <= oceanMapDimension)
                    monsterSprite.setY(yMove);

                monsterList.add(new Point(monsterSprite.getX(), monsterSprite.getY()));
                getShipPoint(ship);
            }
        }

    }
}
