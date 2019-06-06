import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class RechercheLocale {

	public static Solution compacter(Graph g, Solution sn) {
		boolean continuer = true;
		int cpt = 0;
		
		for (int i = 0 ; i < EvacNode.nbEvacNode ; i++)
		{
			continuer = true;
			while (continuer)
			{
				if (sn.getNodes().get(i).startDate>0)
				{
					sn.getNodes().get(i).setStartDate(sn.getNodes().get(i).getStartDate()-1);
					if (Checker.check(g, sn, true, false,false,true))
					{
						//System.out.println("MODIF : " + cpt);
						cpt++;
					}
					else 
					{
						sn.getNodes().get(i).setStartDate(sn.getNodes().get(i).getStartDate()+1);
						continuer = false;
					}
				}
				else
				{
					continuer = false;
				}
				
			}
			//System.out.println("i : " + i);
			//System.out.println(Checker.check(g, sn, true, false,false,false));
		}
		//sn.display();
		//System.out.println(cpt);
		
		return sn;
	}
	
	public static Solution increaseRate(Graph g, Solution s1) {
		Solution s = s1;
		boolean continuer = true;
		Solution sn;
		int cpt = 0;
		
		for (int i = 0 ; i < EvacNode.nbEvacNode ; i++)
		{
			continuer = true;
			sn = s;
			while (continuer)
			{
				sn.getNodes().get(i).setRate(sn.getNodes().get(i).getRate()+1000000);
				if (Checker.check(g, sn, true, false,false,true))
				{
					s.getNodes().get(i).setRate(s.getNodes().get(i).getRate()+1);
					System.out.println("MODIF : " + cpt);
					cpt++;
				}
				else 
				{
					s.getNodes().get(i).setRate(s.getNodes().get(i).getRate()-2);
					continuer = false;
				}		
			}
			System.out.println("i : " + i);
		}
		s.display();
		System.out.println(cpt);
		return s;
		
		
	}
	
	public static Solution modifierOrdre(Graph g, Solution s1, String n)
	{
		int cpt = 3;
		while (cpt!= 0)
		{
			ArrayList<ArrayList<Integer>> array = new ArrayList<ArrayList<Integer>>();
			
			int nbEvacN = g.getNbEvacNodes();
			//System.out.println(nbEvacN);
			int bestSc = s1.getCost();
			int bestI = -1;
			int bestJ = -1;
			
			
			for (int i = 0; i<nbEvacN;i++) {
				for (int j = i+1; j<nbEvacN;j++)
				{
					System.out.println("i : "+i+ " " + "j : "+j);
					Collections.swap(s1.nodes, i, j);
					RechercheLocale.compacter(g, s1);
					if (s1.getCost()<bestSc && Checker.check(g, s1, true, false, false, false))
					{
						bestSc = s1.getCost();
						bestI = i;
						bestJ = j;
					}
					Collections.swap(s1.nodes, i, j);
					try {
						s1 = Reader.readSolution(SolutionBuilder.buildSolutionSup(g, n));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if (bestI != -1 && bestJ != -1)
			{
				Collections.swap(s1.nodes, bestI, bestJ);
			}
			else
			{
				break;
			}
			RechercheLocale.compacter(g, s1);
			cpt-=1;
		}
		
		return s1;
	}

}
