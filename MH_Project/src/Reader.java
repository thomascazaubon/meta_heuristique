import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
					int cpt = 3;
					while (cpt<tabString.length)
					{
						n.addRoute(new Node(Integer.parseInt(tabString[cpt])));
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

}
