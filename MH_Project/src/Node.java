import java.util.ArrayList;

public class Node {

	private int id;
	static ArrayList<Node> listOfNodes = new ArrayList<Node>();
	
	
	public Node(int id)
	{
		this.id = id;
		Node.listOfNodes.add(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public static ArrayList<Node> getListOfNodes()
	{
		return Node.listOfNodes;
	}
	
	public static Node createIfNotExists(int id)
	{
		Node ret = null;
		for (Node n : Node.listOfNodes)
		{
			if (n.getId() == id)
			{
				ret = n;
			}
		}
		if (ret == null)
		{
			ret = new Node(id);
		}
		return ret;
	}
}
