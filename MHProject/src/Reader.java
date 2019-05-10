import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {

	
	public static Graph readGraph(String file) throws IOException
	{
		Graph g = new Graph();
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		boolean firstPart = true;
		String[] tabString;
		int index = 0;
		while ((line = br.readLine()) != null)
		{
			tabString = line.split(" ");
			
			if (!firstPart)
			{
				if (tabString.length != 2)
				{
					int id1 = Integer.parseInt(tabString[0]);
					int id2 = Integer.parseInt(tabString[1]);
					Node n1 = g.getNode(id1);
					Node n2 = g.getNode(id2);
					if (n1 == null) {
						n1 = new Node(Integer.parseInt(tabString[0]));
						g.addNode(n1);
					}
					if (n2 == null) {
						n2 = new Node(Integer.parseInt(tabString[1]));
						g.addNode(n2);
					}
					Integer duedate;
					if (tabString[2].equals("9223372036854775807"))
					{
						duedate = Integer.MAX_VALUE;
					}
					else
					{
						duedate = Integer.parseInt(tabString[2]);
					}
					Edge e = new Edge(n1,n2,duedate,Integer.parseInt(tabString[3]),Integer.parseInt(tabString[4]));
					g.getNode(n1.getId()).addSuccessor(e);
					Edge e2 = new Edge(n2,n1,duedate,Integer.parseInt(tabString[3]),Integer.parseInt(tabString[4]));
					g.getNode(n2.getId()).addSuccessor(e2);

				}
			}
			else if (tabString[0].equals("c") && index != 0)
			{
				firstPart = false;
			}
			else if (firstPart && !tabString[0].equals("c"))
			{
				if (tabString.length == 2)
				{
					g.setSafe(tabString[1]);
				}
				else
				{
					EvacNode n = new EvacNode(Integer.parseInt(tabString[0]),Integer.parseInt(tabString[1]),Integer.parseInt(tabString[2]));
					int cpt = 4;
					n.addNodeRoute(n.getId());
					while (cpt<tabString.length)
					{
						n.addNodeRoute(Integer.parseInt(tabString[cpt]));
						cpt++;
					}
					g.addNode(n);
				}
			}
			index++;
		}
		br.close();
		return g;
	}
	
	public static Solution readSolution(String file) throws IOException {
		Solution s;
		ArrayList<SolNode> nodes = new ArrayList<SolNode>();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		String[] tabString;
		int numberOfNodes = 0;
		int index = 0;
		int cost = 0;
		while ((line = br.readLine()) != null)
		{
			tabString = line.split(" ");
			//La deuxième ligne contient le nombre de noeuds à évacuer
			if (index == 1) {
				numberOfNodes = Integer.parseInt(tabString[0]);
			}
			//A partir de la ligne 3 sont listés les noeuds à évacuer
			if (index > 1 && index <= numberOfNodes + 1) {
				SolNode n = new SolNode(Integer.parseInt(tabString[0]),Integer.parseInt(tabString[1]),Integer.parseInt(tabString[2]));
				nodes.add(n);
			}
			//La deuxième ligne suivant les noeuds à évacuer contient la valeur de la fonction objectif
			if(index == numberOfNodes + 3) {
				cost = Integer.parseInt(tabString[0]);
			}
			index++;
		}
		s = new Solution(nodes, cost);
		br.close();
		return s;
	}

}
