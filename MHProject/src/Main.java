import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Main {

	public static void main(String[] args) {
		/*
		Graph g;
		Solution s;
		try {
			String n = "dense_10_30_3_1_I";
			g = Reader.readGraph(n);
			s = Reader.readSolution(SolutionBuilder.buildSolutionSup(g, n));
			s.display();
			//Graph, Solution, Capacity, Duedate
			Checker.check(g, s, true, false,false);
			RechercheLocale.increaseRate(g,s);
			s.display();
		} catch (IOException e) {
			System.out.println("Invalid path !");
		}
		*/
		
		boolean multiple = false;
		if (multiple) {
			ArrayList<String> notValid = new ArrayList<String>();
			File file = new File("instances/");
			File[] files = file.listFiles();
			Date time = new Date();
			Date time2;
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
			time2 = new Date();
			System.out.println(time2.getTime() - time.getTime());
			for (int i = 0 ; i < notValid.size() ; i++)
				System.out.println(notValid.get(i));
		} else {
			Graph g;
			Solution s;
			try {
				String n = "dense_10_30_3_5_I";
				g = Reader.readGraph(n);
				s = Reader.readSolution(SolutionBuilder.buildSolutionSup(g, n));
				//Graph, Solution, Capacity, Duedate
				Checker.check(g, s, true, false, true);
				//Checker.check(g, s, true, false);
			} catch (IOException e) {
				System.out.println("Invalid path !");
			}
		}
		
	}
}
