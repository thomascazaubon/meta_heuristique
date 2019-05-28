import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		boolean multiple = true;
		if (multiple) {
			ArrayList<String> notValid = new ArrayList<String>();
			File file = new File("instances/");
			File[] files = file.listFiles();
			for(File f: files){
				if (f.getName().split("\\.")[1].equals("full") && f.getName().split("\\.")[0].equals("dense_10_30_3_3_I")) {
					String n = f.getName().split("\\.")[0];
					try {
						System.out.println("Name = " + n);
						Graph g = Reader.readGraph(n);
						Solution s = Reader.readSolution(SolutionBuilder.buildSolutionSup(g, n));
						//Graph, Solution, Capacity, Duedate
						if (!Checker.check(g, s, true, false))
							notValid.add(f.getName());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						System.out.println("Error with " + f.getName());
					}
				}
			}
			for (int i = 0 ; i < notValid.size() ; i++)
				System.out.println(notValid.get(i));
		} else {
			Graph g;
			Solution s;
			try {
				String n = "dense_10_30_3_3_I";
				g = Reader.readGraph(n);
				s = Reader.readSolution(SolutionBuilder.buildSolutionSup(g, n));
				//Graph, Solution, Capacity, Duedate
				Checker.check(g, s, true, false);
				//Checker.check(g, s, true, false);
			} catch (IOException e) {
				System.out.println("Invalid path !");
			}
		}
	}
}
