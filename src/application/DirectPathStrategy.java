package application;

import java.awt.Point;

public class DirectPathStrategy implements PursuitStrategy {
	OceanMap oceanMap;
	
	public DirectPathStrategy() {
		oceanMap = OceanMap.getInstance();
    }
	
	
	@Override
	public Point getNextPosition(Point currentLocation, Point targetLocation) {
        int dx = currentLocation.x - targetLocation.x;
        int dy = currentLocation.y - targetLocation.y;
        Point nextPosition = new Point(currentLocation.x, currentLocation.y);

        int xDirection = (dx > 0) ? -1 : 1;
        int yDirection = (dy > 0) ? -1 : 1;
        // If the difference in x positions is greater, prefer to move horizontally
        if (Math.abs(dx) > Math.abs(dy)) {
            if (oceanMap.canEnter(currentLocation.x + xDirection, currentLocation.y)) {
                nextPosition.x = nextPosition.x + xDirection;
            }
            //if the horizontal move was impossible, try to move vertically.
            else if (oceanMap.canEnter(currentLocation.x, currentLocation.y + yDirection)) {
                nextPosition.y = nextPosition.y + yDirection;
            }
        }
        // Try to move vertically if the y distance is greater.
        else {
            if (oceanMap.canEnter(currentLocation.x, currentLocation.y + yDirection)) {
                nextPosition.y = nextPosition.y + yDirection;
            }
            else if (oceanMap.canEnter(currentLocation.x + xDirection, currentLocation.y)) {
                nextPosition.x = nextPosition.x + xDirection;
            }
        }
        return nextPosition;
	}

}
