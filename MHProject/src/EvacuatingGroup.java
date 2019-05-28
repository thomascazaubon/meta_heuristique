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
	Edge currentEdge;
	//Le groupe est-il arrivé ?
	boolean arrived;
	boolean evacuating;
	
	public EvacuatingGroup(int size, ArrayList<Node> route, int id) {
		this.size = size;
		this.route = route;
		this.id = id;
		arrived = false;
		evacuating = false;
		nodeLocation = 0;
		edgeLocation = 0;
		currentEdge = route.get(nodeLocation).getSuccessor(route.get(nodeLocation + 1).getId());
	}
	
	public void forward() {
		while (currentEdge.getLength() == 0 && nodeLocation < route.size() - 2) {
			edgeLocation = 0;
			nodeLocation++;
			currentEdge = route.get(nodeLocation).getSuccessor(route.get(nodeLocation + 1).getId());
			
		}
		edgeLocation++;
		//Si on vient de finir de parcourir l'edge, on avance d'un noeud
		if (edgeLocation >= currentEdge.getLength() && !evacuating) {
			nodeLocation++;
			//On reset la position sur l'edge
			edgeLocation = 0;
			if (nodeLocation < route.size()-1) {
				currentEdge = route.get(nodeLocation).getSuccessor(route.get(nodeLocation + 1).getId());
				if(currentEdge.getLength() > 0)
					System.out.println("Group " + id + " from node " + route.get(0).getId() + " has reached node " + route.get(nodeLocation).getId() + ".");
			}
		} else {
			//System.out.println(currentEdge.getLength());
			if (!evacuating)
			System.out.println("Group " + id + " from node " + route.get(0).getId() + " is at position " + edgeLocation + "/" + currentEdge.getLength() + " on edge from node " + route.get(nodeLocation).getId() + " to node " + route.get(nodeLocation + 1).getId() + ".");
		}

		if (evacuating) {
			arrived = true;
			System.out.println("Evacuating group " + id + " containing " + size + " people from node " + route.get(0).getId() + " is now safe.");
		}
			
		//Si on est arrivé au dernier noeud, le paquet est évacué
		if (nodeLocation == route.size()-1 && !arrived) {
			evacuating = true;
			System.out.println("Group " + id + " from node " + route.get(0).getId() + " has reached destination.");
		}
	}
	
	public boolean hasArrived() {
		return arrived;
	}
	
	public Edge getCurrentEdge() {
		return currentEdge;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getId() {
		return id;
	}
}
