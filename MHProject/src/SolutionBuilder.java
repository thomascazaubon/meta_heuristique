import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SolutionBuilder {

	public static Integer getBorneInf(Graph g)
	{
		int min = 0;
		for (Node n : g.getNodes())
		{
			if (n instanceof EvacNode)
			{
				ArrayList<Node> route = ((EvacNode) n).buildRoute(g);
				int minRate = ((EvacNode) n).getMinRate(g);
				Integer tmp = (((EvacNode) n).getPop() / minRate)-1;
				for (int i = 0 ; i < route.size()-1 ; i++)
				{
					tmp += route.get(i).getSuccessor(route.get(i+1).getId()).getLength();
				}
				if (tmp>min)
				{
					min = tmp;
				}
			}
		}
		return min;
	}
	
	public static Integer getBorneSup(Graph g)
	{
		int min = 0;
		for (Node n : g.getNodes())
		{
			if (n instanceof EvacNode)
			{
				min += (((EvacNode) n).getPop() / ((EvacNode) n).getMinRate(g))-1;
				ArrayList<Node> route = ((EvacNode) n).buildRoute(g);
				
				for (int i = 0 ; i < route.size()-1 ; i++)
				{
					min += route.get(i).getSuccessor(route.get(i+1).getId()).getLength();
				}
				
			//	System.out.println("Length from id " + n.getId() + " = " + );

			}
		}
		System.out.println("MIN = " + min);
		return min + 1;
	}
	public static String buildSolutionSup(Graph g, String name) {
		String path = null;
		try {
			path = "instances/" + name + ".sol";
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			String content = "";
			content += g.getName() + "\n";
			content += Integer.toString(EvacNode.getNbEvacNode()) + "\n";
			int cpt = 0;
			for (Node n : g.getNodes())
			{
				if (n instanceof EvacNode)
				{
					ArrayList<Node> route = ((EvacNode) n).buildRoute(g);
					content += Integer.toString(n.getId()) + " " + Integer.toString(((EvacNode) n).getMinRate(g)) + " " + Integer.toString(cpt) + "\n";
					cpt += (((EvacNode) n).getPop() / ((EvacNode) n).getMinRate(g))-1;
					
					for (int i = 0 ; i < route.size()-1 ; i++)
					{
						cpt += route.get(i).getSuccessor(route.get(i+1).getId()).getLength();
					}

				}
			}
			content += "valid\n";
			content += SolutionBuilder.getBorneSup(g);
			writer.write(content);
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
	
	public static String buildSolutionInf(Graph g, String name) {
		String path = null;
		try {
			path = "instances/" + name + ".sol";
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			String content = "";
			content += g.getName() + "\n";
			content += Integer.toString(EvacNode.getNbEvacNode()) + "\n";
			for (Node n : g.getNodes())
			{
				if (n instanceof EvacNode)
				{
					content += Integer.toString(n.getId()) + " " + Integer.toString(((EvacNode) n).getMinRate(g)) + " 0" + "\n";

				}
			}
			content += "not valid\n";
			content += SolutionBuilder.getBorneInf(g);
			writer.write(content);
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
	
	
}
