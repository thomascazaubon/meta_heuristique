import java.util.ArrayList;

public class EvacuatingGroup {
	int id;
	//Le nombre de personnes que contient le groupe
	int size;
	//Index du noeud sur lequel se situe le groupe dans l'arraylist
	int nodeLocation;
	//Distance parcourue sur l'edge courant
	int edgeLocation;
	//La route à suivre
	ArrayList<Node> route;
	//Le groupe est-il arrivé ?
	boolean arrived;
	
	public EvacuatingGroup(int size, ArrayList<Node> route, int id) {
		this.size = size;
		this.route = route;
		this.id = id;
		arrived = false;
		nodeLocation = 0;
		edgeLocation = 0;
	}
	
	public void forward() {
		//Edge courant
		Edge currentEdge = route.get(nodeLocation).getSuccessor(route.get(nodeLocation + 1).getId());
		//On avance sur l'edge
		edgeLocation++; 
		//Si on vient de finir de parcourir l'edge, on avance d'un noeud
		if (edgeLocation >= currentEdge.getLength() && currentEdge.getLength() > 0) {
			nodeLocation++;
			//On reset la position sur l'edge
			edgeLocation = 0;
			System.out.println("Group " + id + " from node " + route.get(0).getId() + " has reached node " + route.get(nodeLocation).getId() + ".");
		} else {
			System.out.println(currentEdge.getLength());
			System.out.println("Group " + id + " from node " + route.get(0).getId() + " is at position " + edgeLocation + "/" + currentEdge.getLength() + " on edge from node " + route.get(nodeLocation).getId() + " to node " + route.get(nodeLocation + 1).getId() + ".");
		}
		while (currentEdge.getLength() == 0 && nodeLocation != route.size()-1) {
			nodeLocation++;
			edgeLocation = 1;
			currentEdge = route.get(nodeLocation).getSuccessor(route.get(nodeLocation + 1).getId());
		}
		//Si on est arrivé au dernier noeud, le paquet est évacué
		if (nodeLocation == route.size()-1) {
			arrived = true;
			System.out.println("Evacuating group " + id + " containing " + size + " people from node " + route.get(0).getId() + " is now safe.");
		}
	}
	
	public boolean hasArrived() {
		return arrived;
	}
	
	public Edge getCurrentEdge() {
		return route.get(nodeLocation).getSuccessor(route.get(nodeLocation + 1).getId());
	}
	
	public int getSize() {
		return size;
	}
	
	public int getId() {
		return id;
	}
}
