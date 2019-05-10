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
				int minRate = ((EvacNode) n).getRate(); 
				for (int i = 0 ; i < route.size()-1 ; i++)
				{
					if (minRate>route.get(i).getSuccessor(route.get(i+1).getId()).getCapacity());
					{
						minRate = route.get(i).getSuccessor(route.get(i+1).getId()).getCapacity();
					}
				}
				Integer tmp = ((EvacNode) n).getPop() / minRate;
				for (int i = 0 ; i < route.size()-1 ; i++)
				{
					/*
					if (((EvacNode) n).getRate() > route.get(i).getSuccessor(route.get(i+1).getId()).capacity){
						return -1;
					}
					*/
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
				min += ((EvacNode) n).getPop() / ((EvacNode) n).getRate();
				ArrayList<Node> route = ((EvacNode) n).buildRoute(g);
				
				for (int i = 0 ; i < route.size()-1 ; i++)
				{
					/*
					if (((EvacNode) n).getRate() > route.get(i).getSuccessor(route.get(i+1).getId()).capacity){
						return -1;
					}
					*/
					min += route.get(i).getSuccessor(route.get(i+1).getId()).getLength();
				}

			}
		}
		
		return min;
	}
}
