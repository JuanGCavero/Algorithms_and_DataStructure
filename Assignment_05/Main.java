import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("Juan Gonzalez Cavero, 118928, Digital Engineering.");
		System.out.println("Assignment 5: Graham‘s scan\n");
		
		Scanner scan = new Scanner(System.in);
		int points = 0;
		System.out.println("Type the number of points:");
		try {
			points = Integer.parseInt(scan.nextLine());
		}catch(Exception e) {System.out.println("Bye bye! Type an integer next time =)");};
		
		
		ArrayList<int[]> poligon = PointList(points);
		
//		for (int[] number : poligon) {
//			System.out.println(number[0]+", "+number[1]+";");
//		}
//		System.out.println();

		int lowestPoint = LowestPoint(poligon);
		
		ArrayList<int[]> closedPath = ClosedPath(poligon,lowestPoint, points);
		
//		for (int[] number : sortedPoints) {
//			System.out.println(number[0]+", "+number[1]+";");
//		}
//		System.out.println();
		
		ArrayList<int[]> convexHull = GrahamConvex(closedPath);
		
		System.out.println("Convex Hull point coordinates");
		for (int[] number : convexHull) {
			System.out.println(number[0]+", "+number[1]+";");
		}
		
		System.out.println("Do you want to plot the solution? Type: 'y'(Yes) or 'other'(No)");
		boolean show;
		if(scan.nextLine().equals("y"))
			show = true;
		else
			show = false;
		scan.close();
		
		if(show) {
			PlotSolution(convexHull);
		}
	}

	public static ArrayList<int[]> PointList(int points){		
		// Random points are stored in an ArrayList
		Random rnd = new Random();
		
		ArrayList<int[]> poligon = new ArrayList<int[]>();
		for (int i = 0; i < points; i++) {
			int[] point = new int[2];
			// Limited square plane from (0,0) to (50,50)
			point[0]= rnd.nextInt(50);
			point[1]= rnd.nextInt(50);
			poligon.add(point);
		}
		return poligon;
	}
			
	public static int LowestPoint(ArrayList<int[]> poligon) {		
		int yCoord=50;
		int index = 0; // Store index with lowest y Coordinate
		for (int i = 0; i < poligon.size(); i++) {
			if(poligon.get(i)[1] < yCoord){
				index = i;
				yCoord = poligon.get(i)[1];
			}
		}
		return index;
	}
	
	public static ArrayList<int[]> ClosedPath(ArrayList<int[]> poligon, int indexLowest, int points) {
		// Closed Path (Slide 7, lecture 7)
		// Angles of points respect the lowest point
		int index=0;
		ArrayList<int[]> closedPath = new ArrayList<int[]>();
		closedPath.add(poligon.get(indexLowest));
		poligon.remove(indexLowest);
		while(closedPath.size() < points) {
			double minAngle = 180;
			for (int i = 0; i < poligon.size(); i++) {
				double slope = ((double)poligon.get(i)[1] - (double)closedPath.get(0)[1])/(double)(poligon.get(i)[0] - (double)closedPath.get(0)[0]);
				double polarAngle = Math.atan(slope)*180/Math.PI;				
				if(polarAngle < 0) { // If it is in the 2º cuadrant (+180)
					polarAngle += 180;
				}
				if(polarAngle < minAngle) {
						minAngle = polarAngle;
						index = i;
				}								
			}
			// We add the points into a new list sorted by its angle
			closedPath.add(poligon.get(index));
			// We remove the inserted point from the list, so we cannot select it again
			poligon.remove(index);
		}	
		return closedPath;
	}
	
	public static ArrayList<int[]> GrahamConvex(ArrayList<int[]> sPoli) {
		
		for (int i = 1; i < sPoli.size(); i++) {
			
			// Comments in normal case (scroll down)
			// Case last point
			if(i==sPoli.size()-1) {
				double dx1 = (double)sPoli.get(i)[0] - (double)sPoli.get(i-1)[0];
				double dy1 = (double)sPoli.get(i)[1] - (double)sPoli.get(i-1)[1];
				double dx2 = (double)sPoli.get(0)[0] - (double)sPoli.get(i)[0];
				double dy2 = (double)sPoli.get(0)[1] - (double)sPoli.get(i)[1];
				
				double refAngle = Math.atan(dy1/dx1)*180/Math.PI;
				if(dx1 < 0) {
					refAngle += 180;
				}
				if(dx1 == 0 && dy1 > 0) {
					refAngle = 90;
				}
				if(dx1 > 0 && dy1 < 0) {
					refAngle += 360;
				}
				if(dx1 == 0 && dy1 < 0) {
					refAngle = 270;
				}
				double nextAngle = Math.atan(dy2/dx2)*180/Math.PI;
				if(dx2 < 0) {
					nextAngle += 180;
				}
				if(dx2 == 0 && dy2 > 0) {
					nextAngle = 90;
				}
				if(dx2 > 0 && dy2 < 0) {
					nextAngle += 360;
				}
				if(dx2 == 0 && dy2 < 0) {
					nextAngle = 270;
				}
				
				if(nextAngle < refAngle) {
					sPoli.remove(i);
					i=i-2;
				}
			}
			// Any other point
			else {
				double dx1 = (double)sPoli.get(i)[0] - (double)sPoli.get(i-1)[0];
				double dy1 = (double)sPoli.get(i)[1] - (double)sPoli.get(i-1)[1];
				double dx2 = (double)sPoli.get(i+1)[0] - (double)sPoli.get(i)[0];
				double dy2 = (double)sPoli.get(i+1)[1] - (double)sPoli.get(i)[1];
				
				// Transform from cartesian to polar coodinates
				double refAngle = Math.atan(dy1/dx1)*180/Math.PI;
				// Case 2º or 3º cuadrant
				if(dx1 < 0) {
					refAngle += 180;
				}
				// Case vertical (up)
				if(dx1 == 0 && dy1 > 0) {
					refAngle = 90;
				}
				// Case 4º cuadrant
				if(dx1 > 0 && dy1 < 0) {
					refAngle += 360;
				}
				// Case vertical (down)
				if(dx1 == 0 && dy1 < 0) {
					refAngle = 270;
				}
				
				double nextAngle = Math.atan(dy2/dx2)*180/Math.PI;
				if(dx2 < 0) {
					nextAngle += 180;
				}
				if(dx2 == 0 && dy2 > 0) {
					nextAngle = 90;
				}
				if(dx2 > 0 && dy2 < 0) {
					nextAngle += 360;
				}
				if(dx2 == 0 && dy2 < 0) {
					nextAngle = 270;
				}

				// If next angle is lower than the reference, means it turns
				// right, so we have want to remove that point
				if(nextAngle < refAngle) {
					sPoli.remove(i);
					if(i>2) {i=i-2;}
					else {i--;}
				}
			}
		}
		return sPoli;		
	}
	
	public static void PlotSolution(ArrayList<int[]> convexHull) {
		for (int i = 0; i < 52; i++) {
			System.out.print("x");
		}
		System.out.println();
		for (int i = 0; i < 50; i++) {
			System.out.print("x");
			for (int j = 0; j < 50; j++) {
				boolean noPoint=true;
				for (int[] point : convexHull) {
					if(i == point[0] && j == point [1]) {
						System.out.print("o");
						noPoint = false;
					}
				}
				if(noPoint) {
					System.out.print(" ");
				}
			}
			System.out.println("x");

		}
		System.out.println();			
		
		for (int i = 0; i < 52; i++) {
			System.out.print("x");
		}
	}
}
