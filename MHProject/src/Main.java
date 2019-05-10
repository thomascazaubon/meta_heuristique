import java.io.File;
import java.io.IOException;

public class Main {
	
	public static void main(String[] args) {
		Graph g;
		Solution s;
		try {
			g = Reader.readGraph("instances/exemple1.full");
			s = Reader.readSolution("instances/solution.txt");
			//Checker.check(g, s);
			//g.display();
			System.out.println(SolutionBuilder.getBorneInf(g));
			System.out.println(SolutionBuilder.getBorneSup(g));
		} catch (IOException e) {
			System.out.println("Invalid path !");
		}
		
	}
}
