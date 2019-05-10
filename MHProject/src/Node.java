import java.util.ArrayList;

public class Node {

	private int id;
	ArrayList<Edge> successors;

	
	public Node(int id){
		this.id = id;
		successors = new ArrayList<Edge>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public ArrayList<Edge> getSuccessors()
	{
		return successors;
	}
	
	public Edge getSuccessor(int id) {
		Edge e = null;
		for (Edge s : successors) {
			if (s.getN2().getId() == id) {
				e = s;
				break;
			}
		}
		return e;
	}
	
	public void addSuccessor(Edge successor) {
		successors.add(successor);
	}
}
