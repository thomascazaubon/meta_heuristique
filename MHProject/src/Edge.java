
public class Edge {

	private Node n1;
	private Node n2;
	private int duedate;
	private int length;
	private int capacity;
	
	public Edge(Node n1, Node n2, int dd, int l, int c)
	{
		this.n1 = n1;
		this.n2 = n2;
		this.duedate = dd;
		this.length = l;
		this.capacity = c;
	}
	
	public Edge(Node n1, Node n2)
	{
		this.n1 = n1;
		this.n2 = n2;
		this.duedate = -1;
		this.length = -1;
		this.capacity = -1;
	}
	

	public Node getN1() {
		return n1;
	}

	public void setN1(Node n1) {
		this.n1 = n1;
	}

	public Node getN2() {
		return n2;
	}

	public void setN2(Node n2) {
		this.n2 = n2;
	}

	public int getDuedate() {
		return duedate;
	}

	public void setDuedate(int duedate) {
		this.duedate = duedate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}


}
