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
	
	public void addSuccessor(Edge successor) {
		successors.add(successor);
	}
}
