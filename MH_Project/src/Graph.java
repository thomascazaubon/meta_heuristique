import java.util.ArrayList;

public class Graph {

	ArrayList<Node> nodes;
	int safe = -1;
	
	public Graph()
	{
		this.nodes = new ArrayList<Node>();
	}

	public void setSafe(String string) {
		this.safe = Integer.parseInt(string);
		
	}

	public void addNode(Node n) {
		nodes.add(n);
		
	}
	
	public Node getNode(int id) {
		Node n = null;
		for (int i = 0 ; i < nodes.size() ; i++) {
			if (nodes.get(i).getId() == id) {
				n = nodes.get(i);
				break;
			}
		}
		return n;
	}
	
	public void display() {
		System.out.println("************************   GRAPH   ************************");
		System.out.println("Number of nodes : " + nodes.size());
		System.out.println("Safe node is number " + safe);
		for (Node n : nodes) {
			if (n instanceof EvacNode) {
				System.out.println("   - Node " + n.getId() + " !EVAC! [pop : " + ((EvacNode)n).getPop() + " | rate : " + ((EvacNode)n).getRate() + "]");
				System.out.print("     Escape route is : " + n.getId());
				ArrayList<Node> route = ((EvacNode)n).getRoute();
				for (int i = 0 ; i < route.size() ; i++) {
					System.out.print(" -> " + route.get(i).getId());
				}
				System.out.println("\n     Has " + n.getSuccessors().size() + " successor(s) :");
			} else {
				System.out.println("   - Node " + n.getId() + " has " + n.getSuccessors().size() + " successor(s) :");
			}
			for (Edge e : n.getSuccessors()) {
				System.out.println("      => "+ e.getN2().getId());
			}
		}
		System.out.println("**********************   END OF GRAPH   ********************");
	}
}
