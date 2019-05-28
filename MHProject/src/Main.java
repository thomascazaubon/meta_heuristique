import java.io.File;
import java.io.IOException;

public class Main {
	
	public static void main(String[] args) {
		Graph g;
		Solution s;
		try {
			String n = "sparse_10_30_3_5_I";
			g = Reader.readGraph(n);
			s = Reader.readSolution(SolutionBuilder.buildSolutionSup(g, n));
			//Graph, Solution, Capacity, Duedate
			Checker.check(g, s, true, false);
			//RechercheLocale.compacter(g,s);
		} catch (IOException e) {
			System.out.println("Invalid path !");
		}
		
	}
}
