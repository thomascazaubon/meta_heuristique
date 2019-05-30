
public class RechercheLocale {

	public static void compacter(Graph g, Solution s1) {
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
				if (sn.getNodes().get(i).startDate>0)
				{
					sn.getNodes().get(i).setStartDate(sn.getNodes().get(i).getStartDate()-1);
					if (Checker.check(g, sn, true, false,false))
					{
						s.getNodes().get(i).setStartDate(s.getNodes().get(i).getStartDate()-1);
						System.out.println("MODIF : " + cpt);
						cpt++;
					}
					else 
					{
						s.getNodes().get(i).setStartDate(s.getNodes().get(i).getStartDate()+2);
						continuer = false;
					}
				}
				else
				{
					continuer = false;
				}
				
			}
			System.out.println("i : " + i);
		}
		s.display();
		System.out.println(cpt);
		
		
	}
	
	public static void increaseRate(Graph g, Solution s1) {
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
				if (Checker.check(g, sn, true, false,false))
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
		
		
	}

}
