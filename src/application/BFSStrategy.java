package application;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class BFSStrategy implements PursuitStrategy {
	 private OceanMap oceanMap;
 
	 public BFSStrategy() {
	        oceanMap = OceanMap.getInstance();
	 }
	 
	@Override
	public Point getNextPosition(Point location, Point targetLocation) {
		final int[][] ocean_map = oceanMap.getMap();
		List<Point> shortestPath = performBFS(ocean_map, location, targetLocation);
		
		 Point next = location;
	      if (shortestPath != null && !shortestPath.isEmpty()) {
	          if (shortestPath.size() > 1) {
	              next = shortestPath.get(1);
	           }
	           else {
	              next = shortestPath.get(0);
	           }
	        }
	        if (oceanMap.canEnter(next.x, next.y)) {
	            return next;
	        }
	        else {
	        	return location;
	        }       
	}
	//Breath first Search
	private List<Point> performBFS(int[][] ocean_map, Point startLocation, Point targetLocation) {
		 HashMap<Point, Point> searchPathMap = new HashMap<>(); 
	     Set<Point> visited = new HashSet<>(); 
	     Queue<Point> marked = new LinkedList<>();
	     
	     searchPathMap.put(startLocation, null);
	        marked.add(startLocation);
	        while (!marked.isEmpty()) {
	            Point parent = marked.remove();

	            if (parent.equals(targetLocation)) {
	                return constructPath(searchPathMap, startLocation, parent);
	            }

	            List<Point> neighbors = getSurroundingPoints(parent, ocean_map);
	            for (Point child : neighbors) {
	                if (visited.contains(child))
	                    continue;

	                if (!marked.contains(child)) {
	                    searchPathMap.put(child, parent);
	                    marked.add(child);
	                }
	            }

	            visited.add(parent);
	        }
	     
		return null; 
	}

	private List<Point> constructPath(HashMap<Point, Point> searchPathMap, Point startLocation, Point targetLocation) {
		List<Point> shortestPath = new ArrayList<>();
        shortestPath.add(targetLocation);

        Point parent = searchPathMap.get(targetLocation);
        while(parent != null) {
            shortestPath.add(parent);
            parent = searchPathMap.get(parent);
        }
        Collections.reverse(shortestPath);
        return shortestPath;
	}

	private List<Point> getSurroundingPoints(Point parent, int[][] ocean_map) {
		int x = parent.x;
        int y = parent.y;
        int width = ocean_map[0].length;
        int height = ocean_map.length;
        LinkedList<Point> neighbors = new LinkedList<>();

        //Add the neighbors that are valid array indices
        if (y - 1 >= 0) {
            neighbors.add(new Point(x, y - 1));
        }
        if (x + 1 < width) {
            neighbors.add(new Point(x + 1, y));
        }
        if (y + 1 < height) {
            neighbors.add(new Point(x, y + 1));
        }
        if (x - 1 >= 0) {
            neighbors.add(new Point(x - 1, y));
        }
        return neighbors;
	}
}






