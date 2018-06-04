package application;

import java.awt.Point;

public interface PursuitStrategy {

	public Point getNextPosition(Point currentLocation, Point targetLocation);

}
