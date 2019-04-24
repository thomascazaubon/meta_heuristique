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
			System.out.println(line);
			
			if (!firstPart)
			{
				if (tabString.length != 2)
				{
					System.out.println(tabString[0]);
					Node n1 = Node.createIfNotExists(Integer.parseInt(tabString[0]));
					Node n2 = Node.createIfNotExists(Integer.parseInt(tabString[1]));
					
					Integer duedate;
					if (tabString[2].equals("9223372036854775807"))
					{
						duedate = Integer.MAX_VALUE;
					}
					else
					{
						duedate = Integer.parseInt(tabString[2]);
					}
					
					g.addEdge(new Edge(n1,n2,duedate,Integer.parseInt(tabString[3]),Integer.parseInt(tabString[4])));
					
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
						n.addRoute(Node.createIfNotExists(Integer.parseInt(tabString[cpt])));
						cpt++;
					}
				}
			}
			
			index++;
		}
		br.close();
		
		System.out.println(g.listOfEdges.size());
		return g;
	}

}
