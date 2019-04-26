import java.util.ArrayList;

public class EvacuatingGroup {
	int id;
	int size;
	int nodeLocation;
	int edgeLocation;
	ArrayList<Node> route;
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
		//On avance sur l'edge
		edgeLocation++; 
		Edge currentEdge = route.get(nodeLocation).getSuccessor(route.get(nodeLocation + 1).getId());
		//Si on vient de finir de parcourir l'edge, on avance d'un noeud
		if (edgeLocation == currentEdge.getLength()) {
			nodeLocation++;
			edgeLocation = 0;
			System.out.println("Group " + id + " from node " + route.get(0).getId() + " has reached node " + route.get(nodeLocation).getId() + ".");
		} else {
			System.out.println("Group " + id + " from node " + route.get(0).getId() + " is at position " + edgeLocation + "/" + currentEdge.getLength() + " on edge from node " + route.get(nodeLocation).getId() + " to node " + route.get(nodeLocation + 1).getId() + ".");
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
