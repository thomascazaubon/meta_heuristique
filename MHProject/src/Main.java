import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		Graph g;
		Solution s;
		try {
			String n = "sparse_10_30_3_10_I";
			g = Reader.readGraph(n);
			s = Reader.readSolution(SolutionBuilder.buildSolutionSup(g, n));
			//Graph, Solution, Capacity, Duedate
			System.out.println(Checker.check(g, s, true, false,false,false));
			s.display();
			//Solution s2 = RechercheLocale.compacter(g,s);
			//s2 = RechercheLocale.increaseRate(g,s);
			//System.out.println(Checker.check(g, s2, true, false,true,false));
			//System.out.println(s2.toStringSol(g));
			Solution s3 = RechercheLocale.modifierOrdre(g, s,n);
			System.out.println(s3.toStringSol(g));
		} catch (IOException e) {
			System.out.println("Invalid path !");
		}
		/*
		boolean multiple = false;
		if (multiple) {
			ArrayList<String> notValid = new ArrayList<String>();
			File file = new File("instances/");
			File[] files = file.listFiles();
			for(File f: files){
				if (f.getName().split("\\.")[1].equals("full")) {
					String n = f.getName().split("\\.")[0];
					try {
						//System.out.println("Name = " + n);
						Graph g = Reader.readGraph(n);
						Solution s = Reader.readSolution(SolutionBuilder.buildSolutionSup(g, n));
						//Graph, Solution, Capacity, Duedate
						if (!Checker.check(g, s, true, false, false))
							notValid.add(n);
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
				String n = "simple_exemple_2";
				g = Reader.readGraph(n);
				s = Reader.readSolution(SolutionBuilder.buildSolutionSup(g, n));
				//Graph, Solution, Capacity, Duedate
				Checker.check(g, s, true, false, true);
				//Checker.check(g, s, true, false);
			} catch (IOException e) {
				System.out.println("Invalid path !");
			}
		}
		*/
	}
}
