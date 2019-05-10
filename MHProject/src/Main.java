import java.io.File;
import java.io.IOException;

public class Main {
	
	public static void main(String[] args) {
		Graph g;
		Solution s;
		try {
			g = Reader.readGraph("instances/exemple1.full");
			s = Reader.readSolution("instances/solution.txt");
			//g.display();
			Checker.check(g, s);
		} catch (IOException e) {
			System.out.println("Invalid path !");
		}
		
	}
}
