import java.util.ArrayList;

public class Graph {

	ArrayList<Edge> listOfEdges;
	ArrayList<Node> listOfEvacNode;
	int safe = -1;
	
	public Graph()
	{
		this.listOfEdges = new ArrayList<Edge>();
		this.listOfEvacNode = new ArrayList<Node>();
	}
	
	public void addEdge(Edge e)
	{
		this.listOfEdges.add(e);
	}

	public void setSafe(String string) {
		this.safe = Integer.parseInt(string);
		
	}

	public void add_evacuation_node() {
		// TODO Auto-generated method stub
		
	}
}
