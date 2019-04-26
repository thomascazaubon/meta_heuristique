import java.util.ArrayList;
import java.util.HashMap;

public class Checker {
	
	public static boolean check(Graph g, Solution s) {
		boolean valid = true;
		ArrayList<EvacuatingGroup> groups = new ArrayList<EvacuatingGroup>();
		int gId = 0;
		int t;
		for (t = 0 ; t < s.getCost() ; t++) {
			HashMap<Edge,Integer> capacities = new HashMap<Edge,Integer>();
			System.out.println("************************************************************************");
			System.out.println("Checker, t = " + t);
			System.out.println("\n[EVACUATING]\n");
			for (SolNode sn : s.getNodes()) {
				EvacNode n = (EvacNode) g.getNode(sn.getId());
				if (sn.getStartDate() <= t && n.getPop() > 0) {
					//Pour chaque noeud de la solution qui a commencé à évacuer, on retire à son homologue dans le graphe 
					//la valeur de rate à sa population à chaque itération, et on émet un nouveau groupe d'évacuation
					if (n.getPop() - sn.getRate() < 0) {
						ArrayList<Node> route = new ArrayList<Node>();
						for (Node nr : n.getRoute()) {
							route.add(g.getNode(nr.getId()));
						}
						groups.add(new EvacuatingGroup(n.getPop(), route, gId));
						System.out.println(n.getPop() + " people have left node " + n.getId() + " at t = " + t + " [ID : " + gId + "]. 0 remaining.");
						n.setPop(0);
					} else {
						n.setPop(n.getPop() - sn.getRate());
						ArrayList<Node> route = new ArrayList<Node>();
						for (Node nr : n.getRoute()) {
							route.add(g.getNode(nr.getId()));
						}
						groups.add(new EvacuatingGroup(sn.getRate(), route, gId));
						System.out.println(sn.getRate() + " people have left node " + n.getId() + " at t = " + t + " [ID : " + gId + "]. " + n.getPop() + " remaining.");
					}
					gId++;
				}
			}
			//On fait avancer tous les groupes et on retire ceux qui sont arrivés au fur et à mesure
			ArrayList<EvacuatingGroup> toBeRemoved = new ArrayList<EvacuatingGroup>();
			for (EvacuatingGroup eg : groups) {
				eg.forward();
				if (eg.hasArrived()) {
					toBeRemoved.add(eg);
				}
			}
			System.out.println("\n[CONSTRAINT CHECKING]\n");
			//On vérifie qu'on ne dépasse pas les capacités d'un arc
			for (EvacuatingGroup eg : groups) {
				if (!eg.hasArrived()) {
					if (eg.edgeLocation == 0) {
						System.out.println("Group " + eg.getId() + " containing " + eg.getSize() +  " people is entering node " + eg.getCurrentEdge().getN1().getId() + ".");
						if (capacities.containsKey(eg.getCurrentEdge())) {
							capacities.put(eg.getCurrentEdge(), capacities.get(eg.getCurrentEdge()) + eg.getSize());
						} else {
							capacities.put(eg.getCurrentEdge(), eg.getSize());
						}
					}
				}
			}
			for(HashMap.Entry<Edge, Integer> pair : capacities.entrySet()) {
				System.out.println(pair.getValue() + " people entering on edge from node " + pair.getKey().getN1().getId() + " to node " + pair.getKey().getN2().getId() + ", capacity is " + pair.getKey().getCapacity() + ".");

				if (pair.getKey().getCapacity() < pair.getValue()) {
					valid = false;
					System.out.println("Capacity exceeded ! " + pair.getValue() + " people entering on edge from node " + pair.getKey().getN1().getId() + " to node " + pair.getKey().getN2().getId() + ", capacity is only " + pair.getKey().getCapacity() + " !");
					break;
				}
			}
			if(!valid) {
				break;
			}
			System.out.println("\n[OK]");
			for (EvacuatingGroup eg : toBeRemoved) {
				groups.remove(eg);
			}
		}
		//S'il reste des groupes qui ne sont pas arrivés, la solution n'est pas valide
		if (!groups.isEmpty() || !valid) {
			valid = false;
			System.out.println("Solution is not valid");
		} else {
			System.out.println("Solution is valid");
		}
		return valid;
	}
}
