import java.util.ArrayList;
import java.util.HashMap;

public class Checker {
	
	public static boolean check(Graph g, Solution s) {
		boolean valid = true;
		//Tous les groupes actuellement entrain d'évacuer
		ArrayList<EvacuatingGroup> groups = new ArrayList<EvacuatingGroup>();
		//Pour donner un id à chaque nouveau groupe (pour le debug)
		int gId = 0;
		//On vérifie d'abord que les rates appliquées au noeuds à évacuer n'excèdent pas leur capacité max
		for (SolNode sn : s.getNodes()) {
			if (sn.getRate() > ((EvacNode)g.getNode(sn.getId())).getRate()) {
				valid = false;
				break;
			}
		}
		if (valid) {
			for (int t = 0 ; t < s.getCost() ; t++) {
				//Pour vérifier qu'aucune capacité n'est dépassée
				HashMap<Edge,Integer> capacities = new HashMap<Edge,Integer>();
				//La liste des groupes qui sont arrivés et qui doivent être retirés de groups
				ArrayList<EvacuatingGroup> toBeRemoved = new ArrayList<EvacuatingGroup>();
				System.out.println("************************************************************************");
				System.out.println("Checker, t = " + t);
				System.out.println("\n[EVACUATING]\n");
				//On parcourt chaque noeud à évacuer
				for (SolNode sn : s.getNodes()) {
					//On récupère son homologue dans le graphe
					EvacNode n = (EvacNode) g.getNode(sn.getId());
					//Si le noeud est entrain d'évacuer
					if (sn.getStartDate() <= t && n.getPop() > 0) {
						//Pour chaque noeud de la solution qui a commencé à évacuer, on retire à son homologue dans le graphe 
						//la valeur de rate à sa population à chaque itération, et on émet un nouveau groupe d'évacuation
						//Dans le cas où il reste moins de gens à évacuer que la valeur de rate
						if (n.getPop() - sn.getRate() < 0) {
							//On reconstruit la route en récupérant les noeuds à partir de leurs ids
							//Obligatoire car la route ne contient pas les références vers les vrais noeuds dans le graphe
							//(La route ne contient pas les successeurs de chaque noeud qu'elle contient ce qui pose problème) 
							//Vient de la façon dont elle est construite au momement de lire le graphe
							//A CHANGER !!!
							ArrayList<Node> route = new ArrayList<Node>();
							for (Node nr : n.getRoute()) {
								route.add(g.getNode(nr.getId()));
							}
							//On émet un groupe d'évacuation
							groups.add(new EvacuatingGroup(n.getPop(), route, gId));
							System.out.println(n.getPop() + " people have left node " + n.getId() + " at t = " + t + " [ID : " + gId + "]. 0 remaining.");
							n.setPop(0);
						} else {
							//S'il reste plus de personnes que la valeur de rate
							n.setPop(n.getPop() - sn.getRate());
							//On reconstruit la route en récupérant les noeuds à partir de leurs ids
							//Obligatoire car la route ne contient pas les références vers les vrais noeuds dans le graphe
							//(La route ne contient pas les successeurs de chaque noeud qu'elle contient ce qui pose problème) 
							//Vient de la façon dont elle est construite au momement de lire le graphe
							//A CHANGER !!!
							ArrayList<Node> route = new ArrayList<Node>();
							for (Node nr : n.getRoute()) {
								route.add(g.getNode(nr.getId()));
							}
							//On émet un groupe d'évacuation
							groups.add(new EvacuatingGroup(sn.getRate(), route, gId));
							System.out.println(sn.getRate() + " people have left node " + n.getId() + " at t = " + t + " [ID : " + gId + "]. " + n.getPop() + " remaining.");
						}
						gId++;
					}
				}
				//On fait avancer tous les groupes et on retire ceux qui sont arrivés au fur et à mesure
				for (EvacuatingGroup eg : groups) {
					eg.forward();
					//Si le groupe est arrivé on l'ajoute à la liste des groupes à supprimer de groups
					if (eg.hasArrived()) {
						toBeRemoved.add(eg);
					}
				}
				System.out.println("\n[CONSTRAINT CHECKING]\n");
				//On va lister les edges qui doivent être vérifiées
				for (EvacuatingGroup eg : groups) {
					if (!eg.hasArrived()) {
						//On regarde chaque noeud qui vient de voir arriver un nouveau groupe
						//Si edgelocation vaut 0, c'est que le groupe vient d'arriver sur un noeud (une intersection) on ne va
						//vérifier que les capacités sont respectées qu'en entrée de chaque edge
						if (eg.edgeLocation == 0) {
							System.out.println("Group " + eg.getId() + " containing " + eg.getSize() +  " people is entering node " + eg.getCurrentEdge().getN1().getId() + ".");
							//Si l'edge en cours à déjà au moins un groupe à l'entrée, on incrémente la somme des personnes présentes
							if (capacities.containsKey(eg.getCurrentEdge())) {
								capacities.put(eg.getCurrentEdge(), capacities.get(eg.getCurrentEdge()) + eg.getSize());
							} else {
								//Sinon on ajoute l'edge à la liste des edges dont il faut vérifer que les capacités ne sont pas excédées
								capacities.put(eg.getCurrentEdge(), eg.getSize());
							}
						}
						//On vérifie également que la duedate de l'arc sur lequel ils sont n'est pas expirée
						if (eg.getCurrentEdge().getDuedate() < t) {
							valid = false;
							System.out.println("[DUEDATE EXPIRED : group " + eg.getId() + " in on edge from node " + eg.getCurrentEdge().getN1().getId() + " to node " + eg.getCurrentEdge().getN2().getId() + " which duedate was " + eg.getCurrentEdge().getDuedate() + " !]");
							break;
						}
					}
				}
				if(valid) {
					//On vérifie maintenant que les capacités ne sont pas excédées
					for(HashMap.Entry<Edge, Integer> pair : capacities.entrySet()) {
						System.out.println(pair.getValue() + " people entering on edge from node " + pair.getKey().getN1().getId() + " to node " + pair.getKey().getN2().getId() + ", capacity is " + pair.getKey().getCapacity() + ".");

						if (pair.getKey().getCapacity() < pair.getValue()) {
							valid = false;
							System.out.println("[CAPACITY EXCEEDED : " + pair.getValue() + "/" + pair.getKey().getCapacity() + " !]");
							//On n'essaye pas de vérifier les autres arcs
							break;
						} else {
							System.out.println("\n[OK]");
						}
					}
					//On enlève les groupes qui sont arrivés
					for (EvacuatingGroup eg : toBeRemoved) {
						groups.remove(eg);
					}
				}
				//S'il reste des groupes qui ne sont pas arrivés alors qu'on est arrivé à la fin des itérations
				//la solution n'est pas valide
				if (t == s.getCost() -1 && !groups.isEmpty()) {
					valid = false;
				}
				if(!valid) {
					break;
				}
			}
		}
		
		if (!valid) {
			System.out.println("Solution is not valid");
		} else {
			System.out.println("Solution is valid");
		}
		return valid;
	}
}